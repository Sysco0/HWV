package hawlandshut.projekt.hwv.enums;

/**
 * Created by Mansi on 08.03.2017.
 */
public enum Unit {
    UNIT("Stk"),
    KILOGRAM("kg"),
    SECOND("s"),
    HOUR("h"),
    MINUTE("m"),
    METER("m"),
    METER_2("m²"),
    METER_3("m³");

    private String name;

    Unit(String name) {
        this.name = name;
    }

    public static Unit convert(hawlandshut.projekt.hwv.response.enums.Unit unit) {
        if(null == unit){
            return null;
        }
        switch (unit){
            case HOUR:
                return HOUR;
            case KILOGRAM:
                return KILOGRAM;
            case METER:
                return METER;
            case METER_2:
                return METER_2;
            case METER_3:
                return METER_3;
            case MINUTE:
                return MINUTE;
            case SECOND:
                return SECOND;
            case UNIT:
                default:
                return UNIT;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
