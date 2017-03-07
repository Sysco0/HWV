package hawlandshut.projekt.hwv.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;
import hawlandshut.projekt.hwv.db.helper.annotation.Table;


public class DBHelper extends SQLiteOpenHelper {

    protected static DBHelper instance = null;

    /**
     * Unique table name key
     */
    public static final String TABLE_NAME_KEY = "aeec80da-327c-4335-9f83-1e8e850374d2_TABLE_NAME_49d0a54a-2531-42dc-a217-95a04a6a8142";
    /**
     * Unique primary key key
     */
    public static final String PRIMARY_KEY_KEY = "f9f9d5dd-9bb8-4607-aa37-aaa8e36829b8_PRIMARYKEY_da0dd950-d584-4deb-8641-9be0b9889100";

    protected final Map<Class<?>, Map<String, String[]>> resources = new HashMap<>();

    private DBHelper(Context context, String databaseName) {
        super(context, databaseName, null, 1);
    }

    /**
     * This function add an resource to DBHelper instance to map and create the Database
     * @param dbTable
     * @param <DB>
     * @return
     */
    public <DB extends DBBase> DBHelper addResources(Class<DB> dbTable) {
        Map<String, String[]> columns = new HashMap<>();
        boolean hasPrimaryKey = false;

        Table tableAnnotation = dbTable.getAnnotation(Table.class);
        if (null == tableAnnotation) {
            return this;
        }

        columns.put(TABLE_NAME_KEY, new String[]{tableAnnotation.name()});
        Field[] fields = dbTable.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {

            for (Field field : fields) {
                field.setAccessible(true);
                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                Column column = field.getAnnotation(Column.class);

                if (null != column) {
                    columns.put(column.name(), new String[]{field.getName(),
                            Type.getSqliteType(field),
                            Boolean.toString(column.nullable()),
                            Boolean.toString(column.unique())});
                    if (!hasPrimaryKey && null != primaryKey) {
                        columns.put(PRIMARY_KEY_KEY, new String[]{column.name(), Boolean.toString(primaryKey.autoincremet())});
                        hasPrimaryKey = true;
                    }
                }
            }
            if (!hasPrimaryKey) {
                return this;
            }
            resources.put(dbTable, columns);
        }
        return this;
    }

    /**
     * Get instance
     * @param context context of activity
     * @param databaseName databaseName
     * @return
     */
    public final static  DBHelper getInstance(Context context, String databaseName) {
        if (null == instance || null == databaseName || !databaseName.equals(instance.getDatabaseName())) {
            instance = new DBHelper(context, databaseName);
        }
        return instance;
    }

    /**
     * On Create create Table and database
     * @param db
     */
    public final void onCreate(SQLiteDatabase db) {
        Set<String> tableToCreate = this.createTableQuery();
        Iterator<String> tableIterator = tableToCreate.iterator();
        while (tableIterator.hasNext()) {
            db.execSQL(tableIterator.next());
        }
    }

    /**
     * On Upgrade Drop everything and build new
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public final void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Iterator<Class<?>> tableIterator = resources.keySet().iterator();

        while (tableIterator.hasNext()) {
            Class<?> tableClazz = tableIterator.next();
            Map<String, String[]> columns = resources.get(tableClazz);
            String tableName = columns.get(TABLE_NAME_KEY)[0];
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", tableName));
        }

        this.onCreate(db);
    }

    /**
     * Create table query to create database
     * @return
     */
    protected final Set<String> createTableQuery() {
        Set<String> response = new HashSet<>();
        Iterator<Class<?>> tableIterator = resources.keySet().iterator();
        StringBuilder builder;

        while (tableIterator.hasNext()) {
            Class<?> tableClazz = tableIterator.next();
            Map<String, String[]> columns = resources.get(tableClazz);
            String tableName = columns.get(TABLE_NAME_KEY)[0];
            String[] primaryKey = columns.get(PRIMARY_KEY_KEY);

            Iterator<String> columnsIterator = columns.keySet().iterator();

            builder = new StringBuilder().append("CREATE TABLE ").append(tableName).append(" (");

            while (columnsIterator.hasNext()) {
                String columnName = columnsIterator.next();
                if (columnName.equals(TABLE_NAME_KEY) || columnName.equals(PRIMARY_KEY_KEY)) {
                    continue;
                }
                String[] columnValues = columns.get(columnName);
                builder.append(" ").append(columnName).append(" ").append(columnValues[1]);

                if (columnName.equals(primaryKey[0])) {
                    builder.append(" PRIMARY KEY");
                    if (Boolean.parseBoolean(primaryKey[1])) {
                        builder.append(" AUTOINCREMENT");
                    }
                } else {
                    if (!Boolean.parseBoolean(columnValues[2])) {
                        builder.append(" NOT NULL");
                    }
                    if (Boolean.parseBoolean(columnValues[3])) {
                        builder.append(" UNIQUE");
                    }
                }

                builder.append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append(")");
            response.add(builder.toString());
        }

        return response;
    }


}