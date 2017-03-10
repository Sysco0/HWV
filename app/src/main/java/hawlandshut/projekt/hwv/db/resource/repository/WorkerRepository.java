package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBWorker;

/**
 * Created by Mansi on 07.03.2017.
 */

public class WorkerRepository extends BaseRepository<DBWorker> {
    private static WorkerRepository instance = null;

    public static WorkerRepository getInstance() {
        if (null == instance) {
            instance = new WorkerRepository();

        }
        return instance;
    }

    private WorkerRepository() {
        super(DBWorker.class);
    }


    public DBWorker getByWorkerId(Long workerId) {
        List<DBWorker> workers = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE worker_id=?", new String[]{Long.toString(workerId)}));
        if (workers.isEmpty()) {
            return null;
        }

        return workers.get(0);
    }
}
