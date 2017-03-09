package hawlandshut.projekt.hwv.db.resource.enitiy;

import hawlandshut.projekt.hwv.db.helper.DBBase;
import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;
import hawlandshut.projekt.hwv.db.helper.annotation.Table;
import hawlandshut.projekt.hwv.enums.TaskState;

/**
 * Created by Mansi on 09.03.2017.
 */
@Table(name = "task")
public class DBTask extends DBBase {
    @PrimaryKey
    @Column(name = "id")
    private Long id;
    @Column(name = "task_id")
    private Long taskId;
    @Column(name = "state")
    private TaskState state;
    @Column(name = "description")
    private String description;
    @Column(name = "customer_id")
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
