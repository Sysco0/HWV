package hawlandshut.projekt.hwv.db.resource.repository;

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
}
