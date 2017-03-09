package hawlandshut.projekt.hwv.db.resource.enitiy;

import hawlandshut.projekt.hwv.db.helper.DBBase;
import hawlandshut.projekt.hwv.db.helper.annotation.Column;
import hawlandshut.projekt.hwv.db.helper.annotation.PrimaryKey;
import hawlandshut.projekt.hwv.db.helper.annotation.Table;
import hawlandshut.projekt.hwv.enums.Unit;

/**
 * Created by Mansi on 07.03.2017.
 */
@Table(name = "article")
public class DBArticle extends DBBase {

    @PrimaryKey
    @Column(name = "id")
    private Long articleId;
    @Column(name = "barcode_id", unique = true)
    private String barcodeId;
    @Column(name = "min_order_amount",nullable = false)
    private Integer minOrderAmount;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "unit",nullable = false)
    private Unit unit;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeID) {
        this.barcodeId = barcodeID;
    }

    public Integer getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(Integer minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String toString(){
        return String.format("id=%d, name=%s, min order amount=%d, unit=%s, barcode=%s", articleId,name,minOrderAmount,unit,barcodeId);
    }
}
