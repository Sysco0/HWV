package hawlandshut.projekt.hwv;

/**
 * Created by matthiash on 05.03.2017.
 */

public class Artikel {
    private int barcodeID;   //Strichcode: 4105250022003
    private int standardVPE; //Verpackungseinheit: 20
    private String name;     //Name: Augustiner, Hell
    private String einheit;  //Einheit: Flasche

    public int getBarcodeID() {
        return barcodeID;
    }

    public int getStandardVPE() {
        return standardVPE;
    }

    public String getName() {
        return name;
    }

    public String getEinheit() {
        return einheit;
    }

    public Artikel(int barcodeID, int standardVPE, String name, String einheit) {
        this.barcodeID = barcodeID;
        this.standardVPE = standardVPE;
        this.name = name;
        this.einheit = einheit;
    }
}
