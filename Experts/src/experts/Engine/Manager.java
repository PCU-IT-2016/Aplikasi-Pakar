/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experts.Engine;

import experts.Entities.*;
import experts.Database.FCDatabase;
import java.util.HashSet;
/**
 *
 * @author owner
 */
public class Manager {
    
    private FCDatabase database;
    private QueueTable queue_table;
    private WorkingMemory working_memory;
    private int rule_pointer = 0;
    
    public Manager(){
        database = new FCDatabase();
        // database.loadExperts();
        queue_table   = new QueueTable();
        working_memory = new WorkingMemory();
    }
    
    public Manager(int experts_id) {
        // INITIALIZE RULES / PREMISE CLAUSE
        database = new FCDatabase();
        database.loadExperts(experts_id);
        
        // INITIALIZE QUEUETABLE
        queue_table = new QueueTable();
        // FIRST RULE TO TRIGGER
        queue_table.premises = database.getRules().get(rule_pointer).premises;
        
        // INITIALIZE WORKING MEMORY
        working_memory = new WorkingMemory();
    }
    
    public boolean loadExperts(int experts_id){
        try{
            database.loadExperts(experts_id);
        } catch(Exception e){ 
            return false;
        }
        return true;
    }
    
    public void showKnowledgeBase(){
        System.out.println();
        for (Rule r : database.getRules()){
            System.out.println(r.getConclusion());
            for (Premise p : r.premises){
                // System.out.println(p.getQuestion() + " " +  p.getRules_premise_val() /*+ " " + p.getTrue_val()*/ + " " + p.operator.getOperator() );
                p.showPremiseOnConsole();
                for (Answer a : p.list_of_answer) {
                    System.out.print(a.getAnswer() + "\t , ");
                }
                System.out.println("\n");
            }
        }        
    }
    
    private Premise getNextPremise(Premise p){
        Premise premise = null;
        for (int i = 0; i < p.premises.size(); i++) {
            Premise premise_target = p.premises.get(i);
            if (premise_target.premises.size() > 0) {
                // ADA PREMISE LAGI DALAM PREMISENYA PREMISE
                return getNextPremise(premise_target);
            }
            if (!working_memory.memory.containsKey(premise_target.getId())){
                return premise_target;
            }
        }
        return premise;
    }
    
    public Premise getNextPremise(){
        // CHECK IF NEXT PREMISE WAS ALREADY ANSWERED OR NOT, LOOP THROUGH, 
        // IF NOT FOUND ANY UNMARKED/UNANSWERED PREMISE
        // GO TO THE NEXT RULE
        Premise premise = null;
        for (int i = 0; i < queue_table.premises.size(); i++){
            Premise premise_target = queue_table.premises.get(i);
            if (premise_target.premises.size() > 0){
                // PREMISE DALAM PREMISE
                Premise next_premise = getNextPremise(premise_target);
                if (next_premise != null)
                    return next_premise;
            }
            else if (!working_memory.memory.containsKey(premise_target.getId())){
                return premise_target;
            }
        }
        return premise;
    }
    
    public boolean setAnswer(Premise premise, int answer_id){
        // TESTING
        working_memory.memory.put(premise.getId(), answer_id);
        // IDE 1
        // LOOP PREMISE ACTIVE RULE (QUEUE_TABLE), 
        // CHECK PREMISE YANG SUDAH TERJAWAB DI WORKING MEMORY
        // BANDINGKAN PREMISE YANG BERADA PADA QUEUE TABLE DENGAN WORKING MEMORY
        // JIKA KETEMU YANG BUKAN ACTUAL PREMISE VALUE 
        // CLEAR CURRENT QUEUE, SET QUEUE TO THE NEXT RULE
        // CHECK PREMISE PADA `NEXT RULE`, 
        // BANDINGKAN DENGAN PREMISE YG TELAH TERJAWAB PADA WORKING MEMORY
        // JIKA KETEMU YANG BUKAN ACTUAL PREMISE VALUE
        // GO TO THE NEXT RULE, ULANG SAMPAI KETEMU NEXT RULE YANG MASIH VALID
        // ELSE SET CURRENT QUEUE TO THE `NEXT RULE`
        // JIKA TIDAK KETEMU RULE YANG VALID, RETURN.
        return true;
    }
    
}
