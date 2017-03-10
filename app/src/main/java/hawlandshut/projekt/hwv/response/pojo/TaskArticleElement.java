package hawlandshut.projekt.hwv.response.pojo;

/**
 * Created by Mansi on 08.03.2017.
 */
public class TaskArticleElement {
    private Long id;
    private Long articleId;
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
