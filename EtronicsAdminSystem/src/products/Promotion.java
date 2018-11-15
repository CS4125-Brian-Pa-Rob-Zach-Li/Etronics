
package products;

import java.util.Date;

public class Promotion {
    private int promoID;
    private String promoName;
    private int discountAmount;
    private Date endDate;

    public Promotion(){
        promoID = 0;
        promoName = "";
        discountAmount = 0;
        endDate = null;
    }
    
    // Used when creating a new promotion in the GUI
    public Promotion(String name, int disAmount, Date eDate){
        promoName = name;
        discountAmount = disAmount;
        endDate = eDate;
    }
    
    // Used when created after a database read
    public Promotion(int pID, String name, int disAmount, Date eDate)
    {
        promoID = pID;
        promoName = name;
        discountAmount = disAmount;
        endDate = eDate;
    }

    public int getPromoID() {
        return promoID;
    }

    public void setPromoID(int productID) {
        this.promoID = productID;
    }
    
    public String getPromoName(){
        return promoName;
    }
    
    public void setPromoName(String pN){
        promoName = pN;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
