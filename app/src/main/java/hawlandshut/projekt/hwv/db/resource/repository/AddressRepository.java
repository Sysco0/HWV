package hawlandshut.projekt.hwv.db.resource.repository;

import java.util.List;

import hawlandshut.projekt.hwv.db.helper.BaseRepository;
import hawlandshut.projekt.hwv.db.resource.enitiy.DBAddress;

/**
 * Created by Mansi on 07.03.2017.
 */

public class AddressRepository extends BaseRepository<DBAddress> {
    private static AddressRepository instance = null;

    public static AddressRepository getInstance() {
        if (null == instance) {
            instance = new AddressRepository();

        }
        return instance;
    }

    private AddressRepository() {
        super(DBAddress.class);
    }

    public DBAddress getByAddressId(Long addressId) {
        List<DBAddress> dbAddresses = this.transform(this.getReadableDatabase().rawQuery("SELECT * FROM " + this.tableName + " WHERE address_id=?", new String[]{Long.toString(addressId)}));
        if (dbAddresses.isEmpty()) {
            return null;
        }

        return dbAddresses.get(0);
    }
}
