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
    private Long taskWorkerId;
    @Column(name = "worker_id", nullable = false)
    private Long workerId;
    @Column(name = "task_id", nullable = false)
    private Long taskId;
    @Column(name = "start_time", nullable = false)
    private Long startTime;
    @Column(name = "end_time", nullable = false)
    private Long endTime;
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

    public Long getTaskWorkerId() {
        return taskWorkerId;
    }

    public void setTaskWorkerId(Long taskWorkerId) {
        this.taskWorkerId = taskWorkerId;
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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
