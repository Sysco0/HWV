package hawlandshut.projekt.hwv;

import java.util.Date;

/**
 * Created by Matthias on 23.06.2015.
 */
public class Auftrag {
    private long id;
    private String name;
    private String vorname;
    private String beschreibung;
    private Date empfang;
    private Date start;


    public Auftrag(long _id, String _name, String _vorname, String _beschreibung, Date _empfang, Date _start){
        id = _id;
        name = _name;
        vorname = _vorname;
        beschreibung = _beschreibung;
        empfang = _empfang;
        start = _start;
    }

    public String getVorname() {
        return vorname;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}
