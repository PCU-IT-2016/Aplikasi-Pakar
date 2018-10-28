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
public class Rule {
    
    int id              = -1;
    String conclusion   = "";
    public ArrayList <Premise> premises = new ArrayList <Premise>();
    public ArrayList <Status>  statuses = new ArrayList <Status> ();
    
    public Rule(int _id, String _conclusion){
        id          = _id;
        conclusion  = _conclusion;
    }
    
    public void showRuleOnConsole(){
        System.out.println("RULES " + getId() + ". " + conclusion);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    
}
