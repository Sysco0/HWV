package hawlandshut.projekt.hwv;

import java.util.ArrayList;

/**
 * Created by matthiash on 07.03.2017.
 */

public class RunningWork {

    private Auftrag job;
    private ArrayList<Arbeitszeit> workedHours;
    private ArrayList<AufmassArtikel> aufmass;

    public Auftrag getJob() {
        return job;
    }

    public void setJob(Auftrag job) {
        this.job = job;
    }

    public ArrayList<Arbeitszeit> getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(ArrayList<Arbeitszeit> workedHours) {
        this.workedHours = workedHours;
    }

    public ArrayList<AufmassArtikel> getAufmass() {
        return aufmass;
    }

    public void addArtikelToAufmass(Artikel artikel, int anzahl )
    {
        if(aufmass == null)
        {
            aufmass = new ArrayList<>();
        }
        if(artikel != null)
        {
            aufmass.add(new AufmassArtikel(artikel, anzahl));
        }
    }

    public void setAufmass(ArrayList<AufmassArtikel> aufmass) {
        this.aufmass = aufmass;
    }


    public void setNewArbeitsZeit(Mitarbeiter _worker)
    {
        if(workedHours == null)
        {
            workedHours = new ArrayList<>();
        }
        for (Arbeitszeit az:workedHours)
        {
            if(az.worker.equals(_worker))
            {
                az.endWork();
            }
        }
        workedHours.add(new Arbeitszeit(_worker));
    }
}
