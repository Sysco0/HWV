package hawlandshut.projekt.hwv;

import android.provider.BaseColumns;

public final class AuftragDatenContract {
    public AuftragDatenContract(){}

    public static abstract class AuftragEntry implements BaseColumns {
        public static final String TABLE_NAME = "Auftraege";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_NAME_QUALI = "quali";
        public static final String COLUMN_NAME_POSITION = "posi";

        public static final String[] ALL_KEYS = {
                _ID,
                COLUMN_NAME_TITLE,
                COLUMN_NAME_QUALI,
                COLUMN_NAME_POSITION };
    }
}