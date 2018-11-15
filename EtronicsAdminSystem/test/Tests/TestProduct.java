package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import products.BasicProduct;
import static org.junit.Assert.*;

public class TestProduct {
    private int pid, price;
    private String name, type, desc;
    
    @Before
    public void setUp()
    {
        pid = 2353442;
        price = 322;
        
        name = "Dyson ProV18";
        type = "Vacuum";
        desc = "Dysons' newsest vacuum";
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createProduct()
    {
        BasicProduct testProd = new BasicProduct(pid, name, price, type, desc);    
        testProductAttr(testProd);
        
        pid = 6356225;
        name = "Dyson Ex18";
        type = "Power Cleaner";
        desc = "New power cleaner by dyson";
        price = 299;
       
        testProd.setID(pid);
        testProd.setName(name);
        testProd.setType(type);
        testProd.setDescription(desc);
        testProd.setPrice(price);
        
        testProductAttr(testProd);
    }
    
    public void testProductAttr(BasicProduct testProd)
    {
        assertEquals(testProd.getID(), pid);
        assertEquals(testProd.getName(), name);
        assertEquals(testProd.getType(), type);
        assertEquals(testProd.getDescription(), desc);
        assertEquals(testProd.getPrice(), price);
    }
}