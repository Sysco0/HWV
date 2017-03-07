package hawlandshut.projekt.hwv.enums;

/**
 * Created by Mansi on 07.03.2017.
 */

public enum Unit {
    UNIT("St.","Stück"),
    KILOGRAMM("kg","Kilogramm"),
    SECOND("s","Sekunde"),
    HOUR("h","Stunde"),
    MINIT("m","Minute"),
    METER("m","Meter"),
    METER_2("m²","Quadrat Meter"),
    METER_3("m³","Kubik Meter");

    private String shortcut;
    private String name;

    Unit(String shortcut, String name){
        this.shortcut = shortcut;
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
