package hawlandshut.projekt.hwv;

import android.provider.BaseColumns;

public final class ArtikelDatenContract {
    public ArtikelDatenContract(){}

    public static abstract class ArtikelEntry implements BaseColumns {
        public static final String TABLE_NAME = "Artikel";
        public static final String COLUMN_NAME_BARCODE_ID = "barcode";
        public static final String COLUMN_NAME_STANDARD_VPE = "std_vpe";
        public static final String COLUMN_NAME_ARTICLENAME = "art_name";
        public static final String COLUMN_NAME_EINHEIT = "einheit";


        public static final String[] ALL_KEYS = {
                _ID,
                COLUMN_NAME_STANDARD_VPE,
                COLUMN_NAME_ARTICLENAME,
                COLUMN_NAME_BARCODE_ID,
                COLUMN_NAME_EINHEIT };
    }
}
