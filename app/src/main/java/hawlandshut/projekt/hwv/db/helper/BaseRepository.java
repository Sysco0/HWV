package hawlandshut.projekt.hwv.db.helper;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository<DB extends DBBase> {

    private final Class<DB> clazz;
    private Map<String, String[]> columns = null;
    protected String tableName = null;
    private String pkName = null;


    public BaseRepository(Class<DB> clazz) throws RuntimeException {
        this.clazz = clazz;
        init();
    }

    /**
     * Init the given columns in db class
     */
    private final void init() {
        if (null != DBHelper.instance) {
            columns = DBHelper.instance.resources.get(this.clazz);
            if (null != columns) {
                this.tableName = columns.get(DBHelper.TABLE_NAME_KEY)[0];
                this.pkName = columns.get(DBHelper.PRIMARY_KEY_KEY)[0];
            }
        }
    }

    /**
     * Create an new instance of the class
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private DB getNewGenericsObject() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Error creating instance", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error creating instance", e);
        }
    }

    public final DB create(DB entity) {
        init();
        ContentValues initialValues = new ContentValues();

        Iterator<String> columnsIterator = columns.keySet().iterator();
        while (columnsIterator.hasNext()) {
            String columnName = columnsIterator.next();
            if (columnName.equals(DBHelper.TABLE_NAME_KEY) || columnName.equals(DBHelper.PRIMARY_KEY_KEY)) {
                continue;
            }
            if (columnName.equals(this.pkName) && Boolean.parseBoolean(columns.get(DBHelper.PRIMARY_KEY_KEY)[1])) {
                continue;
            }
            String[] columnData = columns.get(columnName);

            String fieldName = columnData[0];
            try {
                Field tmpField = clazz.getDeclaredField(fieldName);
                boolean isAccessible = tmpField.isAccessible();
                tmpField.setAccessible(true);
                initialValues.put(columnName, tmpField.get(entity).toString());
                tmpField.setAccessible(isAccessible);
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }
        long insertId = DBHelper.instance.getWritableDatabase().insert(this.tableName, null, initialValues);
        Log.d("create entity", "insertId=" + insertId + " json=" + new Gson().toJson(entity));
        return get(insertId);
    }

    /**
     * Saves an entity by primary key
     *
     * @param entity
     * @return
     */
    public final DB save(DB entity) {
        init();
        ContentValues initialValues = new ContentValues();
        Long pk = null;
        Iterator<String> columnsIterator = columns.keySet().iterator();
        while (columnsIterator.hasNext()) {
            String columnName = columnsIterator.next();
            if (columnName.equals(DBHelper.TABLE_NAME_KEY) || columnName.equals(DBHelper.PRIMARY_KEY_KEY)) {
                continue;
            }
            String[] columnData = columns.get(columnName);

            String fieldName = columnData[0];
            try {
                if (columnName.equals(this.pkName)) {
                    pk = (Long) clazz.getDeclaredField(fieldName).get(entity);
                }
                initialValues.put(columnName, clazz.getDeclaredField(fieldName).get(entity).toString());
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }
        DBHelper.instance.getWritableDatabase().update(this.tableName, initialValues, this.pkName + "=?", new String[]{Long.toString(pk)});
        return get(pk);
    }

    /**
     * Deletes an entity by primary key
     *
     * @param pk
     * @return
     */
    public final boolean delete(Long pk) {
        init();

        int deleted = DBHelper.instance.getWritableDatabase().delete(this.tableName, this.pkName + "=?", new String[]{Long.toString(pk)});
        return deleted == 1;
    }

    /**
     * Get readable database
     *
     * @return
     */
    public final SQLiteDatabase getReadableDatabase() {
        return DBHelper.instance.getReadableDatabase();
    }

    /**
     * Get readable database
     *
     * @return
     */
    public final SQLiteDatabase getWriteableDatabase() {
        return DBHelper.instance.getWritableDatabase();
    }

    /**
     * Get an entity by primary key
     *
     * @param pk
     * @return
     */
    public final DB get(Long pk) {
        if (null == pk || pk < 1) {
            return null;
        }

        Cursor cursor = DBHelper.instance.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE " + pkName + "=?", new String[]{Long.toString(pk)});
        List<DB> result = transform(cursor);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    /**
     * This function transform an cursor to an DB List
     *
     * @param cursor the cursor with data
     * @return
     */
    public final List<DB> transform(Cursor cursor) {
        init();

        List<DB> list = new ArrayList<>();
        DB element;
        cursor.moveToFirst();
        for (Cursor tmpCursor : new IterableCursor(cursor)) {
            element = getNewGenericsObject();
            Iterator<String> columnsIterator = columns.keySet().iterator();
            boolean hasData = false;
            while (columnsIterator.hasNext()) {
                String columnName = columnsIterator.next();
                String[] columnData = columns.get(columnName);

                if (columnName.equals(DBHelper.TABLE_NAME_KEY) || columnName.equals(DBHelper.PRIMARY_KEY_KEY)) {
                    continue;
                }
                try {
                    Field columnField = clazz.getDeclaredField(columnData[0]);
                    columnField.setAccessible(true);
                    columnField.set(element, Type.getFieldValue(columnField, tmpCursor.getString(tmpCursor.getColumnIndex(columnName))));
                    hasData = true;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            if (hasData) {
                list.add(element);
            }
        }
        cursor.close();

        return list;
    }

    /**
     * List all elements in Table
     *
     * @return
     */
    public final List<DB> list() {
        init();
        Cursor cursor = DBHelper.instance.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName, null);
        return transform(cursor);
    }

    public final String getTableName() {
        return this.tableName;
    }

    public final String getPkName() {
        return this.pkName;
    }

    public boolean deleteAll(){
        init();
        int deleted = DBHelper.instance.getWritableDatabase().delete(this.tableName, null, null);
        return deleted == 1;

    }



}

