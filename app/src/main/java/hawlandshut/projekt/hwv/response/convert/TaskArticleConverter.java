package hawlandshut.projekt.hwv.response.convert;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBTaskArticle;
import hawlandshut.projekt.hwv.response.pojo.TaskArticleElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class TaskArticleConverter {


    public static DBTaskArticle convert(TaskArticleElement taskArticleElement, long taskId, boolean sync) {
        DBTaskArticle dbTaskArticle = new DBTaskArticle();
        dbTaskArticle.setArticleId(taskArticleElement.getArticleId());
        dbTaskArticle.setAmount(taskArticleElement.getAmount());
        dbTaskArticle.setTaskArticleId(taskArticleElement.getId());
        dbTaskArticle.setTaskId(taskId);
        dbTaskArticle.setSync(sync);
        return dbTaskArticle;
    }
}
