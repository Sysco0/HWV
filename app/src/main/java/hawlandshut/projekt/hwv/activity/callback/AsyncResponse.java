package hawlandshut.projekt.hwv.activity.callback;

/**
 * Created by matth on 12.01.2016.
 */
public interface AsyncResponse<T> {
    void processFinish(T output);
}
