package hawlandshut.projekt.hwv.response.convert;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBWorker;
import hawlandshut.projekt.hwv.response.pojo.WorkerElement;

/**
 * Created by Mansi on 10.03.2017.
 */

public class WorkerConverter {
    public static DBWorker convert(WorkerElement workerElement) {
        DBWorker worker = new DBWorker();
        worker.setFirstName(workerElement.getFirstName());
        worker.setLastName(workerElement.getLastName());
        worker.setWorkerId(workerElement.getId());
        return worker;
    }
}
