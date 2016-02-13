package hawlandshut.projekt.hwv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
public class HWVerwaltungDbHelper extends SQLiteOpenHelper{

    private static HWVerwaltungDbHelper sInstance = null;

    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";


    private static final String SQL_CREATE_ENTRIES_WACHPLAN =
            "CREATE TABLE " + AuftragDatenContract.AuftragEntry.TABLE_NAME + " ("
            + AuftragDatenContract.AuftragEntry._ID + " INTEGER PRIMARY KEY, "
            + AuftragDatenContract.AuftragEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP
            + AuftragDatenContract.AuftragEntry.COLUMN_NAME_POSITION + TEXT_TYPE + ");";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + AuftragDatenContract.AuftragEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 0;
    public static final String DATABASE_NAME = "HWVerwaltung.db";

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
        db.execSQL(SQL_CREATE_ENTRIES_WACHPLAN);
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
*/