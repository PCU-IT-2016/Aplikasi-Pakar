/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experts.Entities;

import java.util.HashMap;

/**
 *
 * @author owner
 */
public class WorkingMemory {
    
    // ???
    public HashMap memory = new HashMap();
    public HashMap cache = new HashMap();
    // memory.put(key, val);
    // memory.get(key)
    
    public void showCacheOnConsole(){
        System.out.println(cache.toString());
    }
    
}
