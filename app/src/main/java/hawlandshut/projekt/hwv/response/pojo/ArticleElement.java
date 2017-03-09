package hawlandshut.projekt.hwv.response.pojo;

import hawlandshut.projekt.hwv.response.enums.Unit;

/**
 * Created by Mansi on 08.03.2017.
 */
public class ArticleElement {
    private Long id;

    private String barcodeId;

    private Integer minOrderAmount;

    private String name;

    private Unit unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        this.barcodeId = barcodeId;
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
}
