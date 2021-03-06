package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskWorker;

/**
 * Created by Mansi on 07.03.2017.
 */

public class TaskWorkerRepository extends BaseRepository<DBTaskWorker> {
    private static TaskWorkerRepository instance = null;

    private TaskWorkerRepository() {
        super(DBTaskWorker.class);
    }

    public static TaskWorkerRepository getInstance() {
        if (null == instance) {
            instance = new TaskWorkerRepository();

        }
        return instance;
    }

    public void deleteAllSync() {
        this.getWriteableDatabase().delete(this.tableName, "sync=?", new String[]{"true"});
    }

    public List<DBTaskWorker> getByTaskId(Long taskId) {
        return this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE task_id=?", new String[]{Long.toString(taskId)}));

    }

    public List<DBTaskWorker> getByWorkerId(Long workerId) {
        return this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE worker_id=?", new String[]{Long.toString(workerId)}));

    }

    public List<DBTaskWorker> getByWorkerIdAndTaskId(Long workerId, long taskId) {
        return this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE worker_id=? AND task_id=?", new String[]{Long.toString(workerId), Long.toString(taskId)}));
    }

    public DBTaskWorker getByWorkerIdAndTaskIdTime(Long workerId, Long taskId) {
        List<DBTaskWorker> dbworkers = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE worker_id=? AND task_id=? AND start_time = end_time", new String[]{Long.toString(workerId), Long.toString(taskId)}));
        if (dbworkers == null) {
            return null;
        }
        return dbworkers.get(0);
    }
}
