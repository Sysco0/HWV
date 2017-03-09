package hawlandshut.projekt.hwv.db.resource.repository;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;

/**
 * Created by Mansi on 07.03.2017.
 */

public class ArticleRepository extends BaseRepository<DBArticle> {
    private static ArticleRepository instance = null;

    public static ArticleRepository getInstance() {
        if (null == instance) {
            instance = new ArticleRepository();

        }
        return instance;
    }

    private ArticleRepository() {
        super(DBArticle.class);
    }
}
