package hawlandshut.projekt.hwv.response.pojo;

/**
 * Created by Mansi on 26.12.2016.
 */
public class TreeElement {

    private long id;
    private String text;
    private long parent;
    private Type type;
    private State state;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public enum Type {
        user, group, company, partner, worker
    }

    public enum State {
        active, disabled, none, removed
    }


}
