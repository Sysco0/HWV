package hawlandshut.projekt.hwv.response.pojo;


import hawlandshut.projekt.hwv.response.enums.Gender;

/**
 * Created by Mansi on 08.03.2017.
 */
public class CustomerElement {
    private Long id;
    private Gender gender;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressElement getAddress() {
        return address;
    }

    public void setAddress(AddressElement address) {
        this.address = address;
    }

    private AddressElement address;
}
