package hawlandshut.projekt.hwv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HWVerwaltungDbHelper extends SQLiteOpenHelper{

    private static HWVerwaltungDbHelper sInstance = null;

    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";


    private static final String SQL_CREATE_ENTRIES_ARTICLES =
            "CREATE TABLE " + hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.TABLE_NAME + " ("
            + hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry._ID + " INTEGER PRIMARY KEY, "
            + hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_ARTICLENAME + TEXT_TYPE + COMMA_SEP
            + hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_BARCODE_ID + TEXT_TYPE + COMMA_SEP
            + hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_EINHEIT + TEXT_TYPE + COMMA_SEP
            + hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.COLUMN_NAME_STANDARD_VPE + TEXT_TYPE + ");";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + hawlandshut.projekt.hwv.ArtikelDatenContract.ArtikelEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HWVerwaltung_Artikel.db";

    public static synchronized HWVerwaltungDbHelper getsInstance(Context context){
        if(sInstance == null){
            sInstance = new HWVerwaltungDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private HWVerwaltungDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_ARTICLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteAllEntries(db);
        onCreate(db);
    }

    private void deleteAllEntries(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    //onDowngrade?
}