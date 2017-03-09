package hawlandshut.projekt.hwv.response;

import java.util.ArrayList;
import java.util.List;

import hawlandshut.projekt.hwv.response.pojo.TreeElement;

/**
 * Created by Mansi on 26.12.2016.
 */
public class TreeResponse extends Response {
    private List<TreeElement> elements = new ArrayList<>();

    public List<TreeElement> getElements() {
        return elements;
    }

    public void setElements(List<TreeElement> elements) {
        this.elements = elements;
    }
}
