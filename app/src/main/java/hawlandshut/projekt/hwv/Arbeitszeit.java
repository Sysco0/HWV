package hawlandshut.projekt.hwv;

import java.util.Calendar;

/**
 * Created by matthiash on 07.03.2017.
 */

public class Arbeitszeit {

    Calendar startTime;
    Calendar endTime;

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

    public void start(){
        startTime = Calendar.getInstance();
    }

    public void end(){
        endTime = Calendar.getInstance();
    }

    public long secondsWorked()
    {
        long worked;
        if(endTime != null)
        {
            worked = endTime.compareTo(startTime);
        }else
        {
            worked = Calendar.getInstance().compareTo(startTime);
        }

        return worked;
    }

    public boolean isEnd()
    {
        return(endTime != null);
    }
}
