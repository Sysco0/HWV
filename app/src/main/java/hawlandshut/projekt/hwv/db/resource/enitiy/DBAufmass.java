package hawlandshut.projekt.hwv.db.resource.enitiy;

import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;

/**
 * Created by Mansi on 08.03.2017.
 */

public class DBAufmass {
    @PrimaryKey
    @Column(name = "id")
    private Long aufmassId;

    private Long auftragId;

}
