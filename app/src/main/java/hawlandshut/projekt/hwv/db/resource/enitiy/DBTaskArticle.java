package hawlandshut.projekt.hwv.db.resource.enitiy;

import hawlandshut.projekt.hwv.db.helper.DBBase;
import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;
import hawlandshut.projekt.hwv.db.helper.annotation.Table;

/**
 * Created by Mansi on 09.03.2017.
 */

@Table(name = "task_article")

public class DBTaskArticle extends DBBase {
    @PrimaryKey
    @Column(name = "id")
    private Long id;
    @Column(name = "task_article_id")
    private Long taskArticleId;
    @Column(name = "article_id", nullable = false)
    private Long articleId;
    @Column(name = "task_id", nullable = false)
    private Long taskId;
    @Column(name = "amoount", nullable = false)
    private Long amount;
    @Column(name = "sync", nullable = false)
    private Boolean sync;

    public Boolean isSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskArticleId() {
        return taskArticleId;
    }

    public void setTaskArticleId(Long taskArticleId) {
        this.taskArticleId = taskArticleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
