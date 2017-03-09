package hawlandshut.projekt.hwv.response;

import java.util.ArrayList;
import java.util.List;

import hawlandshut.projekt.hwv.response.pojo.ArticleElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class ArticleResponse extends Response {
    private List<ArticleElement> articles = new ArrayList<>();

    public List<ArticleElement> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleElement> articles) {
        this.articles = articles;
    }
}
