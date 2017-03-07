package hawlandshut.projekt.hwv;

/**
 * Created by matthiash on 05.03.2017.
 */

public class Artikel {
    private String barcodeID = "";   //Strichcode: 4105250022003
    private String standardVPE = ""; //Verpackungseinheit: 20
    private String name = "";     //Name: Augustiner, Hell
    private String einheit = "";  //Einheit: Flasche

    public String getBarcodeID() {
        return barcodeID;
    }

    public String getStandardVPE() {
        return standardVPE;
    }

    public String getName() {
        return name;
    }

    public String getEinheit() {
        return einheit;
    }

    public Artikel(String barcodeID, String standardVPE, String name, String einheit) {
        this.barcodeID = barcodeID;
        this.standardVPE = standardVPE;
        this.name = name;
        this.einheit = einheit;
    }
}
