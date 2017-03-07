package hawlandshut.projekt.hwv.db.helper;

import java.lang.reflect.Field;

/**
 * Created by mad2man on 5/12/16.
 */
public class Type {
    /**
     * Maps an Field value to an specific object
     *
     * @param columnField the field
     * @param value       the value
     * @return
     */
    protected static Object getFieldValue(Field columnField, String value) {
        Class type = columnField.getType();
        if (null == value) {
            return null;
        }
        if (type == Integer.class) {
            return Integer.parseInt(value);
        }
        if (type == Long.class) {
            return Long.parseLong(value);
        }
        if (type == Short.class) {
            return Short.parseShort(value);
        }
        if (type == Float.class) {
            return Float.parseFloat(value);
        }
        if (type == Double.class) {
            return Double.parseDouble(value);
        }
        if (type == String.class) {
            return value;
        }
        if (type == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (type.isEnum()) {
            return Enum.valueOf((Class<Enum>) type, value);
        }
        return null;
    }

    /**
     * Get from an field the sql type
     *
     * @param field member field
     * @return
     */
    protected static String getSqliteType(Field field) {

        Class typeClass = field.getType();

        if (typeClass == Integer.class || typeClass == Long.class || typeClass == Short.class) {
            return "INTEGER";
        }
        if (typeClass == Float.class || typeClass == Double.class) {
            return "REAL";
        }
        if (typeClass.isEnum() || typeClass == String.class || typeClass == Boolean.class) {
            return "TEXT";
        }

        return "NULL";
    }
}
