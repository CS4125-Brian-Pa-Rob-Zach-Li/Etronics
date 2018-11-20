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
public class ControllerFactory {
    
    public Controller getSimulationController() throws Exception{
        return new SimController(this);
    }
    
    public Controller getSimUserController(ArrayList<SimUser> su, 
            SimUserStateFactory susf, ITransactionDetectionObserver o) throws Exception{
        return new SimUserController(su, susf, o);
    }
    
    public Controller getSimWarehouseController(){
        return new SimWarehouseController();
    }

}
