

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import products.Promotion;
import java.util.Date;



public class TestPromotion {
    private int pid, disAmount;
    private String pName;
    private Date date;
    
    private boolean con = true; //What constructor is being tested
    
    @Before
    public void setUp() {
        pid = 5827123;
        pName = "Christmas Sale";
        disAmount = 12;
        date = new Date("22/12/18");
    }
    
    @After
    public void tearDown() 
    {
       
    }
    
    @Test
    public void createPromotion()
    {
       Promotion test = new Promotion(pid, pName, disAmount, date);
       con = true;
       testPromotionGetMethods(test);
       setNewValues(test);
       testPromotionGetMethods(test);
       
       Promotion newTest = new Promotion(pName, disAmount, date);
       
       con = false;
       testPromotionGetMethods(newTest);
       setNewValues(newTest);
       con=true;
       testPromotionGetMethods(newTest);
       
    }
    
    public void testPromotionGetMethods(Promotion test)
    {
       if(con){
           assertEquals(test.getPromoID(), pid);
       }else{
           assertEquals(test.getPromoID(), null);
       }
       assertEquals(test.getDiscountAmount(), disAmount);
       assertEquals(test.getEndDate(),date);
       assertEquals(test.getPromoName(), pName);
    }
    
    public void setNewValues(Promotion test){
       disAmount = 40;
       date = new Date("29/12/18");
       pName = "January Sales";
       pid = 124523786;
       test.setPromoID(pid);
       test.setDiscountAmount(disAmount);
       test.setEndDate(date);
       test.setPromoName(pName);
    }
}