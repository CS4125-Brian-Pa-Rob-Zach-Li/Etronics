/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import administration.UIAdminController;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class ControllerFactory {
    
    public Controller getSimulationController(UIAdminController ac) throws Exception{
        return new SimController(this, ac);
    }
    
    public Controller getSimUserController(ArrayList<SimUser> su, 
            SimUserStateFactory susf, ITransactionDetectionObserver o) throws Exception{
        return new SimUserController(su, susf, o);
    }
}
