package hawlandshut.projekt.hwv;

import java.util.Date;
import java.util.Set;

/**
 * Created by Matthias on 23.06.2015.
 */
public class Auftrag {
    private long id;
    private int auftragNr;

    private int status;
    private String beschreibung;

    private Kunde kunde;

    //TODO Mitarbeiter arbeitet an
    private Mitarbeiter erfasser;
    private Set<Mitarbeiter> arbeiter;

    private Date empfang;
    private Date start;


    public Auftrag(long _id, int _auftrNr, int _auftrStat, String _beschreibung, Kunde _kunde, Date _empfang){
        id = _id;
        auftragNr = _auftrNr;
        status = _auftrStat;
        beschreibung = _beschreibung;
        kunde = _kunde;
        empfang = _empfang;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public int getAuftrStat() {
        return status;
    }
}
