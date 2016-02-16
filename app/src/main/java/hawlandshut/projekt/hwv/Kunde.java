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

    public Kunde(int _kdNr, String _anrede, String _name,
                 String _vorname, String _adresse, int _plz, String _ort, int _zone){
        kdNr = _kdNr;
        anrede = _anrede;
        name = _name;
        vorname = _vorname;
        adresse = _adresse;
        plz = _plz;
        ort = _ort;
        zone = _zone;
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
