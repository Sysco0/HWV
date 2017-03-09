package hawlandshut.projekt.hwv.response.convert;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBTask;
import hawlandshut.projekt.hwv.enums.TaskState;
import hawlandshut.projekt.hwv.response.pojo.TaskElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class TaskConverter {
    public static DBTask convert(TaskElement task) {
        DBTask dbTask = new DBTask();
        dbTask.setTaskId(task.getTaskId());
        dbTask.setState(TaskState.convert(task.getState()));
        dbTask.setDescription(task.getDescription());
        dbTask.setCustomerId(task.getCustomer().getId());
        return dbTask;
    }
}
