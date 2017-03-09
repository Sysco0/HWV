package hawlandshut.projekt.hwv.response.convert;

import hawlandshut.projekt.hwv.db.resource.enitiy.DBCustomer;
import hawlandshut.projekt.hwv.enums.Gender;
import hawlandshut.projekt.hwv.response.pojo.CustomerElement;

/**
 * Created by Mansi on 08.03.2017.
 */
public class CustomerConverter {

    public static DBCustomer convert(CustomerElement customer) {
        if(null == customer){return null;}
        DBCustomer dbCustomer = new DBCustomer();
        dbCustomer.setCustomerId(customer.getId());
        dbCustomer.setFirstName(customer.getFirstName());
        dbCustomer.setLastName(customer.getLastName());
        dbCustomer.setGender(Gender.convert(customer.getGender()));
        dbCustomer.setAddressId(customer.getAddress().getId());
return dbCustomer;    }
}
