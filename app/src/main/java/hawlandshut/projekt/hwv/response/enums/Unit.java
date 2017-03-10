package hawlandshut.projekt.hwv.response.enums;

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
