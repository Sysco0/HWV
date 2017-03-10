package hawlandshut.projekt.hwv.response;

import java.util.ArrayList;
import java.util.List;

import hawlandshut.projekt.hwv.response.pojo.WorkerElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class WorkerResponse extends Response {
    private List<WorkerElement> worker = new ArrayList<>();

    public List<WorkerElement> getWorker() {
        return worker;
    }

    public void setWorker(List<WorkerElement> worker) {
        this.worker = worker;
    }
}
