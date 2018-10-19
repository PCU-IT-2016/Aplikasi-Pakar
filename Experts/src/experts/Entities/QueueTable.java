/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experts.Entities;

import java.util.ArrayList;

/**
 *
 * @author owner
 */
public class QueueTable {
    
    // CURRENT RULES PREMISES 
    public ArrayList <Premise> premises = new ArrayList <Premise>();
    
    // TRIGGERED PREMISES (NOT QUITE SURE)
    public ArrayList <Premise> triggered_premises = new ArrayList <Premise>();
    
    public int current_state = 0;
    
}
