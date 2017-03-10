package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTask;

/**
 * Created by Mansi on 07.03.2017.
 */

public class WorkerRepository extends BaseRepository<DBTask> {
    private static WorkerRepository instance = null;

    public static WorkerRepository getInstance() {
        if (null == instance) {
            instance = new WorkerRepository();

        }
        return instance;
    }

    private WorkerRepository() {
        super(DBTask.class);
    }

    public DBTask getByWorkerId(Long taskId) {
        List<DBTask> dbTasks = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE task_id=?", new String[]{Long.toString(taskId)}));
        if (dbTasks.isEmpty()) {
            return null;
        }

        return dbTasks.get(0);
    }

}
