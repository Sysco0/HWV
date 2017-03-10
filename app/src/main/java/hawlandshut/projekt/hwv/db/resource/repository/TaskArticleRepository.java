package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskArticle;

/**
 * Created by Mansi on 07.03.2017.
 */

public class TaskArticleRepository extends BaseRepository<DBTaskArticle> {
    private static TaskArticleRepository instance = null;

    public static TaskArticleRepository getInstance() {
        if (null == instance) {
            instance = new TaskArticleRepository();

        }
        return instance;
    }

    private TaskArticleRepository() {
        super(DBTaskArticle.class);
    }

    public void deleteAllSync() {
        this.getWriteableDatabase().delete(this.tableName,"sync=?",new String[]{"true"});
    }

    public List<DBTaskArticle> getByTaskId(Long taskId) {
        return this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE task_id=?", new String[]{Long.toString(taskId)}));

    }
}
