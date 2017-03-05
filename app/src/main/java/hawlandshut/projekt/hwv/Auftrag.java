package hawlandshut.projekt.hwv;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Matthias on 23.06.2015.
 */
public class Auftrag implements Parcelable{
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

    protected Auftrag(Parcel in) {
        id = in.readLong();
        auftragNr = in.readInt();
        status = in.readInt();
        beschreibung = in.readString();
        kunde = in.readParcelable(Kunde.class.getClassLoader());
    }

    public static final Creator<Auftrag> CREATOR = new Creator<Auftrag>() {
        @Override
        public Auftrag createFromParcel(Parcel in) {
            return new Auftrag(in);
        }

        @Override
        public Auftrag[] newArray(int size) {
            return new Auftrag[size];
        }
    };

    public String getBeschreibung() {
        return beschreibung;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public int getAuftrStat() {
        return status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(auftragNr);
        dest.writeInt(status);
        dest.writeString(beschreibung);
        dest.writeParcelable(kunde, flags);
    }
}
