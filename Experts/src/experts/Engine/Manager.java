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
    
    private boolean conclusion_obtained = false;
    private boolean unknown_conclusion = false;
    
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
        queue_table.current_rule_conclusion = database.getRules().get(rule_pointer++).getConclusion();
        
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
                Premise next_premise = getNextPremise(premise_target);
                if (next_premise != null)
                    return next_premise;
            }
            if (!working_memory.memory.containsKey(premise_target.getId())){
                return premise_target;
            }
        }
        return premise;
    }
    
    public Premise getNextPremise(){
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
    
    private boolean getPremiseValue(Premise target){
        for (int i = 0; i < target.premises.size(); i++){
            Premise next_target = target.premises.get(i);
            if (next_target.premises.size() > 0){
                return getPremiseValue(next_target);
            }
            boolean answered = working_memory.memory.containsKey(next_target.getId());
            if (answered){
                int answered_value = (int) working_memory.memory.get(next_target.getId());
                System.out.println(next_target.getRules_premise_val() + " : " + answered_value);
                if (next_target.getRules_premise_val() != answered_value) {
                    return false;
                }
            } else {
                // System.out.println("belum terjawab " + next_target.getQuestion() + next_target.getId());
                System.out.println(working_memory.memory.toString());
                // ASUMSI JIKA KETEMU 1 YANG BELUM TERJAWAB,
                // SISAHNYA PASTI BELUM TERJAWAB
                return true;
            }
        }
        return true;
    }
    
    private boolean getPremiseValue(){
        for (int i = 0; i < queue_table.premises.size(); i++){
            Premise premise_target = queue_table.premises.get(i);
            if (premise_target.premises.size() > 0) {
                if (!getPremiseValue(premise_target)){
                    working_memory.cache.put(premise_target.getId(), false);
                    return false;
                }
                if (isAllPremiseAnswered(premise_target)){
                    working_memory.cache.put(premise_target.getId(), true);
                }
            }
            else {
                boolean answered = working_memory.memory.containsKey(premise_target.getId());
                if (answered){
                    int answered_value = (int) working_memory.memory.get(premise_target.getId());
                    if (premise_target.getRules_premise_val() != answered_value){
                        working_memory.cache.put(premise_target.getId(), false);
                        return false;
                    }
                    if (isAllPremiseAnswered(premise_target)){
                        working_memory.cache.put(premise_target.getId(), true);
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isAllPremiseAnswered() {
        for (int i = 0; i < queue_table.premises.size(); i++){
            Premise sample = queue_table.premises.get(i);
            if (!working_memory.cache.containsKey(sample.getId()))
                return false;
        }
        return true;
    }
    
    private boolean isAllPremiseAnswered(Premise target) {
        for (int i = 0; i < target.premises.size(); i++){
            Premise sample = target.premises.get(i);
            if (!working_memory.memory.containsKey(sample.getId()) && !working_memory.memory.containsKey(sample.getId()))
                return false;
        }
        return true;
    }
    
    public boolean setAnswer(Premise premise, int answer_id){
        // TESTING
        working_memory.memory.put(premise.getId(), answer_id);
        
        if (getPremiseValue() == false){
            // set queue table to the next rule
            if (rule_pointer >= database.getRules().size()) {
                unknown_conclusion = true;
                return false;
            }
            System.out.println("PREMISE FALSE, CHANGE TO THE NEXT RULE");
            do {
                if (rule_pointer >= database.getRules().size()){
                    System.out.println("UNKNOWN CONCLUSION");
                    unknown_conclusion = true;
                    return false;
                }
                queue_table.premises = database.getRules().get(rule_pointer).premises;
                queue_table.current_rule_conclusion = database.getRules().get(rule_pointer++).getConclusion();
                System.out.println("CHECK! RULE: " + queue_table.current_rule_conclusion);
            } while(getPremiseValue() != true);
            // clear cache
            working_memory.cache.clear();
        } else {
            System.out.println(working_memory.cache.toString());
            if (isAllPremiseAnswered()){
                conclusion_obtained = true;
            }
        }
        
        return true;
    }
    
    public boolean conclusionObtained(){
        return conclusion_obtained;
    }
    
    public QueueTable getQueueTable(){
        return queue_table;
    }
    
    public boolean getUnknownConclusion(){
        return unknown_conclusion;
    }
    
}
