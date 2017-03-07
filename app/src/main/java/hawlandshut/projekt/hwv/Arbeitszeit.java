package hawlandshut.projekt.hwv;

import java.util.Calendar;

/**
 * Created by matthiash on 07.03.2017.
 */

public class Arbeitszeit {

    Calendar startTime;
    Calendar endTime;
    Mitarbeiter worker;

    public Arbeitszeit(Mitarbeiter _worker) {
       start(_worker);
    }

    public Mitarbeiter getWorker() {
        return worker;
    }

    public void setWorker(Mitarbeiter worker) {
        this.worker = worker;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void start(Mitarbeiter _worker)
    {
        if(startTime == null){
            worker = _worker;
            startTime = Calendar.getInstance();
        }
    }

    public void endWork(){
        if(endTime == null)
            endTime = Calendar.getInstance();

    }

    public long secondsWorked()
    {
        long worked;
        if(endTime != null)
        {
            worked = (endTime.getTimeInMillis() - startTime.getTimeInMillis())/1000;
        }else
        {
            worked = (Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis())/1000;
        }

        return worked;
    }

    public boolean isEnd()
    {
        return(endTime != null);
    }
}
