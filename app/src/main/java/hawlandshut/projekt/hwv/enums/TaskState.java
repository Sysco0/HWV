package hawlandshut.projekt.hwv.enums;

/**
 * Created by Mansi on 08.03.2017.
 */
public enum TaskState {
    OPEN,
    IN_PROCESS,
    CLOSED;

    public static TaskState convert(hawlandshut.projekt.hwv.response.enums.TaskState state) {
        if(null == state){
            return null;
        }
        switch (state){
            case OPEN:
                return OPEN;
            case IN_PROCESS:
                return IN_PROCESS;
            case CLOSED:
            default:
                return CLOSED;
        }
    }
}
