package hawlandshut.projekt.hwv.response;

/**
 * Created by Mansi on 26.12.2016.
 */
public abstract class Response {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
