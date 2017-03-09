package hawlandshut.projekt.hwv.response;

import java.util.ArrayList;
import java.util.List;

import hawlandshut.projekt.hwv.response.pojo.TaskElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class TaskResponse extends Response {
    private List<TaskElement> tasks = new ArrayList<>();

    public List<TaskElement> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskElement> tasks) {
        this.tasks = tasks;
    }
}
