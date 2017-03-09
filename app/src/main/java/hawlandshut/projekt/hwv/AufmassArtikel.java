package hawlandshut.projekt.hwv;

import java.util.Calendar;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;

/**
 * Created by matthiash on 07.03.2017.
 */

public class AufmassArtikel {
    private DBArticle artikel;
    private Calendar buchungsZeit;
    private int anzahl;

    public AufmassArtikel(DBArticle artikel, int anzahl) {
        this.artikel = artikel;
        buchungsZeit = Calendar.getInstance();
        this.anzahl = anzahl;
    }

    public DBArticle getArtikel() {
        return artikel;
    }

    public void setArtikel(DBArticle artikel) {
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
