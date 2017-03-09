package hawlandshut.projekt.hwv.response;

import hawlandshut.projekt.hwv.response.pojo.User;

/**
 * Created by Mansi on 04.11.2016.
 */
public class LoginResponse extends Response {
    private String apiKey;
    private User user;


    public String getApiKey() {
        return apiKey;
    }

    public LoginResponse setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
