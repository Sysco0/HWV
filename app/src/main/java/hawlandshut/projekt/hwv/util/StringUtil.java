package hawlandshut.projekt.hwv.util;

/**
 * Created by Mansi on 09.03.2017.
 */

public class StringUtil {
    private StringUtil(){}

    /**
     * This function ensures an string
     * if null return empty string
     * @param str
     * @return
     */
    public static  String ensure(String str) {
        if (null == str) {
            return "";
        }
        return str;
    }
}
