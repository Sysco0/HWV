package hawlandshut.projekt.hwv.response.convert;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskWorker;
import hawlandshut.projekt.hwv.response.pojo.WorkTimeElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class TaskWorkerConverter {


    public static DBTaskWorker convert(WorkTimeElement workTimeElement,long taskId, boolean sync) {
        DBTaskWorker dbTaskWorker = new DBTaskWorker();
        dbTaskWorker.setTaskWorkerId(workTimeElement.getId());
        dbTaskWorker.setWorkerId(workTimeElement.getWorkerId());
        dbTaskWorker.setEndTime(workTimeElement.getEndTime());
        dbTaskWorker.setStartTime(workTimeElement.getStartTime());
        dbTaskWorker.setTaskId(taskId);
        dbTaskWorker.setSync(sync);
        return dbTaskWorker;
    }
}
