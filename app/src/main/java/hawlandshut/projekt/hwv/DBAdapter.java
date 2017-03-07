package hawlandshut.projekt.hwv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBAdapter {
// Context of application who uses us.
    private static DBAdapter sInstance = null;
    private final Context context;
    //private ArrayList<Artikel> ArtikelDaten;

    private final String KEY_ROWID_WP = hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry._ID;
    private final String DATABASE_TABLE_WP = hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.TABLE_NAME;
    private final String KEY_ROW_BARCODE_WP = ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_BARCODE_ID;
    private final String[] ALL_KEYS_WP = hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.ALL_KEYS;

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
        deleteAllArtikelDaten();
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }
    /**************************************END General**************************************************/


    /*************************************     ArtikelDaten     *******************************************/
    // Add a new set of values to the database.
    public long insertArtikelDatenRow(String name, String barcode, String einheit, String std_vpe) {
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_ARTICLENAME, name);
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_BARCODE_ID, barcode);
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_EINHEIT, einheit);
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_STANDARD_VPE , std_vpe);

        // Insert it into the database.
        return db.insert(DATABASE_TABLE_WP, null, initialValues);
    }
    // Add a new set of values to the database.
    public long insertArtikelDatenRow(Artikel artikel) {
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_ARTICLENAME, artikel.getName());
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_BARCODE_ID, artikel.getBarcodeID());
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_EINHEIT, artikel.getEinheit());
        initialValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_STANDARD_VPE , artikel.getStandardVPE());

        // Insert it into the database.
        return db.insert(DATABASE_TABLE_WP, null, initialValues);
    }


    // Delete a row from the database, by rowId (primary key)
    public boolean deleteArtikelDatenRow(long rowId) {
        String where = KEY_ROWID_WP + "=" + rowId;
        return db.delete(DATABASE_TABLE_WP, where, null) != 0;
    }

    public void deleteAllArtikelDaten() {
        Cursor c = getAllArtikelDatenRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID_WP);
        if (c.moveToFirst()) {
            do {
                deleteArtikelDatenRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    public Cursor getAllArtikelDatenRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE_WP, ALL_KEYS_WP,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getArtikelDatenRow(long rowId) {
        String where = KEY_ROWID_WP + "=" + rowId;
        Cursor c = 	db.query(true, DATABASE_TABLE_WP, ALL_KEYS_WP,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by barcode)
    public Cursor getArtikelDatenRow(String barcode) {
        String where = KEY_ROW_BARCODE_WP + "=" + barcode;
        Cursor c = 	db.query(true, DATABASE_TABLE_WP, ALL_KEYS_WP,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateArtikelDatenRow(long rowId, String name, int barcode, String einheit, int std_vpe) {
        String where = KEY_ROWID_WP + "=" + rowId;

        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_ARTICLENAME, name);
        newValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_BARCODE_ID, barcode);
        newValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_EINHEIT, einheit);
        newValues.put(hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_STANDARD_VPE , std_vpe);

        // Insert it into the database.
        return db.update(DATABASE_TABLE_WP, newValues, where, null) != 0;
    }

}
