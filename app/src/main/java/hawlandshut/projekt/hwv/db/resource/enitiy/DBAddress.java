package hawlandshut.projekt.hwv.db.resource.enitiy;

import hawlandshut.projekt.hwv.db.helper.DBBase;
import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;
import hawlandshut.projekt.hwv.db.helper.annotation.Table;

/**
 * Created by Mansi on 09.03.2017.
 */
@Table(name = "address")
public class DBAddress extends DBBase {
    @PrimaryKey
    @Column(name = "id")
    private Long id;
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "street")
    private String street;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "place")
    private String place;
    @Column(name = "extension")
    private String extension;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
