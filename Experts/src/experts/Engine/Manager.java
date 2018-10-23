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
    
    public Manager(){
        database = new FCDatabase();
        database.loadExperts();
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
        queue_table.premises = database.getRules().get(0).premises;
        
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
                System.out.println(p.getQuestion() + " " +  p.getRules_premise_val() /*+ " " + p.getTrue_val()*/ + " " + p.operator.getOperator() );
                for (Answer a : p.list_of_answer) {
                    System.out.print(a.getAnswer() + "\t , ");
                }
                System.out.println("\n");
            }
        }        
    }
    
    public Premise getNextPremise(){
        
        // CHECK IF NEXT PREMISE WAS ALREADY ANSWERED OR NOT, LOOP THROUGH, 
        if (working_memory.memory.containsKey(queue_table.premises.get(queue_table.current_state).getId())){
            
        }
        // IF NOT FOUND ANY UNMARKED/UNANSWERED PREMISE
        // GO TO THE NEXT RULE
        
        return queue_table.premises.get(queue_table.current_state++);
    }
    
    public void setAnswer(Premise premise, int answer_id){
        // TESTING
        if (premise.getTrue_val() != answer_id){
            // CLEAR CURRENT QUEUE_TABLE, SET QUEUE_TABLE TO THE NEXT RULE
            System.out.println("next rule!");
        }
        working_memory.memory.put(premise.getId(), premise);
        
    }
    
}
