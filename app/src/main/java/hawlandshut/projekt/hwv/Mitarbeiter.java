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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mitarbeiter that = (Mitarbeiter) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!vorname.equals(that.vorname)) return false;
        return kuerzel.equals(that.kuerzel);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + vorname.hashCode();
        result = 31 * result + kuerzel.hashCode();
        return result;
    }
}
