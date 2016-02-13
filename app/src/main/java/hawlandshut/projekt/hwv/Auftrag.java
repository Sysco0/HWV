package hawlandshut.projekt.hwv;

import java.util.Date;
import java.util.Set;

/**
 * Created by Matthias on 23.06.2015.
 */
public class Auftrag {
    private long id;
    private int auftrNr;
    private Kunde kunde;
    private Mitarbeiter erfasser;
    private Set<Mitarbeiter> arbeiter;
    private int auftrStat;

    //TODO Mitarbeiter arbeitet an
    private String beschreibung;
    private Date empfang;
    private Date start;


    public Auftrag(long _id, Kunde _kunde, String _beschreibung, Date _empfang, Date _start){
        id = _id;
        beschreibung = _beschreibung;
        kunde = _kunde;
        empfang = _empfang;
        start = _start;
        auftrStat = 1; //TODO: Static for now, change?
    }



    public String getBeschreibung() {
        return beschreibung;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public int getAuftrStat() {
        return auftrStat;
    }
}
