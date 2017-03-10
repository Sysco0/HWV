package hawlandshut.projekt.hwv.response.pojo;


import java.util.ArrayList;
import java.util.List;

import hawlandshut.projekt.hwv.response.enums.TaskState;

/**
 * Created by Mansi on 08.03.2017.
 */
public class TaskElement {
    private Long taskId;
    private TaskState state;
    private String description;
    private List<WorkTimeElement> workTimes = new ArrayList<>();
    private  List<TaskArticleElement> usedArticles  = new ArrayList<>();
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

    public List<WorkTimeElement> getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(List<WorkTimeElement> workTimes) {
        this.workTimes = workTimes;
    }

    public List<TaskArticleElement> getUsedArticles() {
        return usedArticles;
    }

    public void setUsedArticles(List<TaskArticleElement> usedArticles) {
        this.usedArticles = usedArticles;
    }

    public CustomerElement getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerElement customer) {
        this.customer = customer;
    }

}
