package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBCustomer;

/**
 * Created by Mansi on 07.03.2017.
 */

public class CustomerRepository extends BaseRepository<DBCustomer> {
    private static CustomerRepository instance = null;

    public static CustomerRepository getInstance() {
        if (null == instance) {
            instance = new CustomerRepository();

        }
        return instance;
    }

    private CustomerRepository() {
        super(DBCustomer.class);
    }

    public DBCustomer getByCustomerId(Long customerId) {
        List<DBCustomer> dbCustomers = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE customer_id=?", new String[]{Long.toString(customerId)}));
        if (dbCustomers.isEmpty()) {
            return null;
        }

        return dbCustomers.get(0);
    }

}
