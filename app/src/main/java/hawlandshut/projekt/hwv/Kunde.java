package hawlandshut.projekt.hwv;

/**
 * Created by matth on 10.02.2016.
 */
public class Kunde {
    private int kdNr;
    private String anrede;
    private String name;
    private String vorname;
    private String adresse;
    private int plz;
    private String ort;
    private int zone;

    public Kunde(int _kdNr){
        kdNr = _kdNr;
    }

    public Kunde(String _name){
        kdNr = 1000001;
        anrede = "Herr";
        name = _name;
        vorname = "Max";
        adresse = "Auf dem Holzweg 3";
        plz = 1337;
        ort = "Leethausen";
        zone = 2;
    }

    public String getVorname() {
        return vorname;
    }

    public String getName() {
        return name;
    }

    public String getAnrede() {
        return anrede;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public int getZone() {
        return zone;
    }
}
