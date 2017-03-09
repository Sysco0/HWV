package hawlandshut.projekt.hwv.response.pojo;


import hawlandshut.projekt.hwv.response.enums.TaskState;

/**
 * Created by Mansi on 08.03.2017.
 */
public class TaskElement {
    private Long taskId;
    private TaskState state;
    private String description;
    private CustomerElement customer;

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

    public CustomerElement getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerElement customer) {
        this.customer = customer;
    }
}
