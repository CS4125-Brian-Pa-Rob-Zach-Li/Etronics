/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class SimController extends Controller{
    
    private ArrayList<SimUser> simUsers;
    private SimUserFactory simUserFact;
    private SimUserStateFactory simUserStateFact;
    private int COMPANYUSERS = 5;
    private int INDIVIDUALUSERS = 10;
    private Controller simUserCon;
    private TransactionDetector transDet;
    
    public SimController(ControllerFactory cf) throws Exception{
        
        transDet = new TransactionDetector();
        
        simUsers = generateUsers();
        
        simUserCon = cf.getSimUserController(simUsers, simUserStateFact, transDet);
    }
    
    public ArrayList<SimUser> generateUsers(){
        // Factory objects to return users and their states
        simUserFact = new SimUserFactory();
        simUserStateFact = new SimUserStateFactory();
        
        ArrayList<SimUser> su = new ArrayList<SimUser>();

        // Generate a set amount of each type of user with their state set to shopping
        for(int x =0; x < COMPANYUSERS; x++)
            su.add(simUserFact.createUser("Company", simUserStateFact.getUserState("Shopping")));
        
        for(int x = 0; x < INDIVIDUALUSERS; x++)
            su.add(simUserFact.createUser("Person", simUserStateFact.getUserState("Shopping")));
        
        return su;
    }
    
    @Override
    public void startSim(){
        simUserCon.run();
    }
}
