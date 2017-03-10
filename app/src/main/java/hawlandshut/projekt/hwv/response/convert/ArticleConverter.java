package hawlandshut.projekt.hwv.response.convert;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBArticle;
import hawlandshut.projekt.hwv.enums.Unit;
import hawlandshut.projekt.hwv.response.pojo.ArticleElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class ArticleConverter {
    public static DBArticle convert(ArticleElement article) {
        DBArticle dbArticle = new DBArticle();
        dbArticle.setArticleId(article.getId());
        dbArticle.setBarcodeId(article.getBarcodeId());
        dbArticle.setMinOrderAmount(article.getMinOrderAmount());
        dbArticle.setName(article.getName());
        dbArticle.setUnit(Unit.convert(article.getUnit()));
        return dbArticle;
    }


}
