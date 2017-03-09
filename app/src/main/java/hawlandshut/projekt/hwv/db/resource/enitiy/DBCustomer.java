package hawlandshut.projekt.hwv.db.resource.enitiy;

import hawlandshut.projekt.hwv.db.helper.DBBase;
import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;
import hawlandshut.projekt.hwv.db.helper.annotation.Table;
import hawlandshut.projekt.hwv.enums.Gender;

/**
 * Created by Mansi on 09.03.2017.
 */
@Table(name = "customer")
public class DBCustomer extends DBBase {
    @PrimaryKey
    @Column(name = "id")
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address_id")
    private Long addressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
