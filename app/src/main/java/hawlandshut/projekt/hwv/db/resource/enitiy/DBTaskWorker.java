package hawlandshut.projekt.hwv.db.resource.enitiy;

import hawlandshut.projekt.hwv.db.helper.DBBase;
import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;
import hawlandshut.projekt.hwv.db.helper.annotation.Table;

/**
 * Created by Mansi on 09.03.2017.
 */

@Table(name = "task_worker")
public class DBTaskWorker extends DBBase {
    @PrimaryKey
    @Column(name = "id")
    private Long id;
    @Column(name = "task_worker_id")
    private Long taskArticleId;
    @Column(name = "worker_id",nullable = false)
    private Long workerId;
    @Column(name = "task_id",nullable = false)
    private Long taskId;

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

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
