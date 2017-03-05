package hawlandshut.projekt.hwv;

/**
 * Created by matth on 10.02.2016.
 */
public class Mitarbeiter {
    private int id;
    private String name;
    private String vorname;
    private String kuerzel;
    //private Fahrzeug kombi;

    public Mitarbeiter(int id, String name, String vorname, String kuerzel/*, Fahrzeug kombi*/) {
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.kuerzel = kuerzel;
        //this.kombi = kombi;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getKuerzel() {
        return kuerzel;
    }
}
