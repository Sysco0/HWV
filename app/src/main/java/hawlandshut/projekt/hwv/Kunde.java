package hawlandshut.projekt.hwv;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by matth on 10.02.2016.
 */
public class Kunde implements Parcelable{
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

    protected Kunde(Parcel in) {
        kdNr = in.readInt();
        anrede = in.readString();
        name = in.readString();
        vorname = in.readString();
        adresse = in.readString();
        plz = in.readInt();
        ort = in.readString();
        zone = in.readInt();
    }

    public static final Creator<Kunde> CREATOR = new Creator<Kunde>() {
        @Override
        public Kunde createFromParcel(Parcel in) {
            return new Kunde(in);
        }

        @Override
        public Kunde[] newArray(int size) {
            return new Kunde[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(kdNr);
        dest.writeString(anrede);
        dest.writeString(name);
        dest.writeString(vorname);
        dest.writeString(adresse);
        dest.writeInt(plz);
        dest.writeString(ort);
        dest.writeInt(zone);
    }
}
