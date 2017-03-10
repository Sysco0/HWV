package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

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

    public DBArticle getByArticleId(Long taskArticleId) {
        List<DBArticle> dbArticles = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE article_id=?", new String[]{Long.toString(taskArticleId)}));
        if (dbArticles.isEmpty()) {
            return null;
        }

        return dbArticles.get(0);
    }

    public DBArticle getByBarcode(String barcodeId) {
        List<DBArticle> dbArticles = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE barcode_id=?", new String[]{barcodeId}));
        if (dbArticles.isEmpty()) {
            return null;
        }

        return dbArticles.get(0);
    }
}
