/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experts;

import experts.Database.FCDatabase;
import experts.Entities.Rule;
import java.util.ArrayList;

/**
 *
 * @author owner
 */
public class Experts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FCDatabase db = new FCDatabase();
        db.loadRules();
        ArrayList <Rule> list = db.getRules();
        for (Rule r : list){
            System.out.println(r.getConclusion());
        }
    }
    
}
