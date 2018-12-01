/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import database.ProductsDAO;
import database.UserDAO;
import database.UserDAOImp;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class SimUserController extends Controller{
   
    private ArrayList<SimUser> simUsers;
    private ArrayList<ITransactionDetectionObserver> observers;
    private SimUserStateFactory simUserStateFact;
    private UserDAO uDAO;
    
    public SimUserController(ArrayList<SimUser> su, SimUserStateFactory susf,
            ITransactionDetectionObserver o) throws Exception{
        
        observers = new ArrayList<ITransactionDetectionObserver>();
        observers.add(o);
        simUsers = su;
        
        // register observers for each user. In our case we will only have one
        for(int x = 0; x < simUsers.size(); x++){
            for(int y = 0; y < observers.size(); y++){
                simUsers.get(x).registerObserver(observers.get(y));
            }
        }
        
        simUserStateFact = susf;
        uDAO = new UserDAOImp();
        
        add_sim_users_to_database();
    }
    
    public void add_sim_users_to_database(){
        // Add simulation users if they do not already exist
        for(int x = 0; x < simUsers.size(); x++){
            boolean exists = uDAO.checkIfExist("sim_user"+x+"@sim.com");
            
            if(!exists){
                // 'custormer' Typo here deliberate. 
                // Need to check with Xinting Li if she does checks using this spelling
                boolean result = uDAO.insertUser("simUser"+x, "testtest", "sim_user"+x+"@sim.com", "custormer");
                System.out.println("Res: "+result);
            }
        }
    }
    
    public int getRandInt100(){
        int random = (int )(Math.random() * 100 + 1);
        return random;
    }
    
    @Override
    public void run(){
        while(true){
            // 10% chance a users state gets set to Shopping
            for(int x = 0; x < simUsers.size(); x++){
                if(getRandInt100() < 11){
                    simUsers.get(x).setState(simUserStateFact.getUserState("Shopping"));
                }
            }
            // 25% chance a user adds an item to their cart
            for(int x = 0; x < simUsers.size(); x++){
                if(getRandInt100() < 26){
                    simUsers.get(x).addToCart();

                    // if they add an item to cart, there's a 25% chance they buy it
                    if(getRandInt100() < 26){
                        simUsers.get(x).makePurchase();
                    }
                }
            }
            try{
                // One second wait between cycles
                sleep(1000);
            }catch(Exception e){
                System.out.println("Exception: sleep() unsuccessful.");
            }
        }
    }
}
