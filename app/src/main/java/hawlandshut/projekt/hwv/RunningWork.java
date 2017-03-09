package hawlandshut.projekt.hwv;

import java.util.ArrayList;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;

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

    public void addArtikelToAufmass(DBArticle artikel, int anzahl )
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
        boolean foundWork = false;
        if(workedHours == null)
        {
            workedHours = new ArrayList<>();
        }
        for (Arbeitszeit az:workedHours)
        {
            if(az.worker.equals(_worker) && !az.isEnd())
            {
                az.endWork();
                foundWork = true;
            }
        }
        
        if(!foundWork)
            workedHours.add(new Arbeitszeit(_worker));
    }
}
