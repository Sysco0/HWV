package hawlandshut.projekt.hwv.response.convert;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBAddress;
import hawlandshut.projekt.hwv.response.pojo.AddressElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class AddressConverter {
    public static DBAddress convert(AddressElement address) {
        DBAddress dbAddress = new DBAddress();
        dbAddress.setExtension(address.getExtension());
        dbAddress.setAddressId(address.getId());
        dbAddress.setPlace(address.getPlace());
        dbAddress.setStreet(address.getStreet());
        dbAddress.setZipcode(address.getZipcode());
        return dbAddress;
    }

}
