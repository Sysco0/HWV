package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTask;

/**
 * Created by Mansi on 07.03.2017.
 */

public class TaskRepository extends BaseRepository<DBTask> {
    private static TaskRepository instance = null;

    public static TaskRepository getInstance() {
        if (null == instance) {
            instance = new TaskRepository();

        }
        return instance;
    }

    private TaskRepository() {
        super(DBTask.class);
    }

    public DBTask getByTaskId(Long taskId) {
        List<DBTask> dbTasks = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE task_id=?", new String[]{Long.toString(taskId)}));
        if (dbTasks.isEmpty()) {
            return null;
        }

        return dbTasks.get(0);
    }

}
