package hawlandshut.projekt.hwv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBAdapter {
// Context of application who uses us.
    private static DBAdapter sInstance = null;
    private final Context context;
    //private ArrayList<Standort> AuftragDaten;

    private final String KEY_ROWID_WP = AuftragDatenContract.AuftragEntry._ID;
    private final String DATABASE_TABLE_WP = AuftragDatenContract.AuftragEntry.TABLE_NAME;
    private final String[] ALL_KEYS_WP = AuftragDatenContract.AuftragEntry.ALL_KEYS;

    private HWVerwaltungDbHelper myDBHelper;
    private SQLiteDatabase db;

    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////
    public static synchronized DBAdapter getsInstance(Context context){
        if(sInstance == null){
            sInstance = new DBAdapter(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = HWVerwaltungDbHelper.getsInstance(context);
    }

    // Open the database connection.
    public DBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    public void deleteAll(){
        deleteAllAuftragDaten();
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }
    /**************************************END General**************************************************/


    /*************************************     AuftragDaten     *******************************************/
    // Add a new set of values to the database.
    public long insertAuftragDatenRow(String name, int quali, String posi) {
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(AuftragDatenContract.AuftragEntry.COLUMN_NAME_TITLE, name);
        initialValues.put(AuftragDatenContract.AuftragEntry.COLUMN_NAME_QUALI, quali);
        initialValues.put(AuftragDatenContract.AuftragEntry.COLUMN_NAME_POSITION, posi);

        // Insert it into the database.
        return db.insert(DATABASE_TABLE_WP, null, initialValues);
    }


    // Delete a row from the database, by rowId (primary key)
    public boolean deleteAuftragDatenRow(long rowId) {
        String where = KEY_ROWID_WP + "=" + rowId;
        return db.delete(DATABASE_TABLE_WP, where, null) != 0;
    }

    public void deleteAllAuftragDaten() {
        Cursor c = getAllAuftragDatenRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID_WP);
        if (c.moveToFirst()) {
            do {
                deleteAuftragDatenRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    public Cursor getAllAuftragDatenRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE_WP, ALL_KEYS_WP,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getAuftragDatenRow(long rowId) {
        String where = KEY_ROWID_WP + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE_WP, ALL_KEYS_WP,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateAuftragDatenRow(long rowId, String name, int quali) {
        String where = KEY_ROWID_WP + "=" + rowId;

        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(AuftragDatenContract.AuftragEntry.COLUMN_NAME_TITLE, name);
        newValues.put(AuftragDatenContract.AuftragEntry.COLUMN_NAME_QUALI, quali);

        // Insert it into the database.
        return db.update(DATABASE_TABLE_WP, newValues, where, null) != 0;
    }

}