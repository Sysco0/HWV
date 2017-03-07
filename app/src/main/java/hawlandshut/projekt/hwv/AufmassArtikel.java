package hawlandshut.projekt.hwv;

import java.util.Calendar;

/**
 * Created by matthiash on 07.03.2017.
 */

public class AufmassArtikel {
    private Artikel artikel;
    private Calendar buchungsZeit;
    private int anzahl;

    public AufmassArtikel(Artikel artikel, int anzahl) {
        this.artikel = artikel;
        buchungsZeit = Calendar.getInstance();
        this.anzahl = anzahl;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Calendar getBuchungsZeit() {
        return buchungsZeit;
    }

    public void setBuchungsZeit(Calendar buchungsZeit) {
        this.buchungsZeit = buchungsZeit;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
