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
public class Premise {
    
    int     id              = -1;
    String  question        = "";
    // CHANGED SOON, TO ANSWER OBJECT
    int     true_val        = -1;
    String  answer          = "";
    int answered_value = -1;
    
    // NILAI KETENTUAN PREMISE DARI RULE
    int rules_premise_val = -1; // ACTUAL VALUE
    int rules_premise_id  = -1;
    
    public LogicOperator operator = null;
    public ArrayList <Answer> list_of_answer = new ArrayList <Answer>();
    public ArrayList <Premise> premises = null;
    
    public Premise(int _id, String _question){
        id          = _id;
        question    = _question;
    }
    
    public Premise(int _id, String _question, String _true_val){
        id          = _id;
        question    = _question;
        answer      = _true_val;
    }
    
    public Premise(int _id, String _question, int _true_val){
        id          = _id;
        question    = _question;
        true_val    = _true_val;
    }
    
    public Premise(int _id, String _question, int _true_val, int _premise_val){
        id          = _id;
        question    = _question;
        true_val    = _true_val;
        rules_premise_val = _premise_val;
        answered_value = _premise_val;
    }
    
    public void showPremiseOnConsole(){
        System.out.println(id + ". " + question + " = " + rules_premise_val);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTrue_val() {
        return true_val;
    }

    public void setTrue_val(int true_val) {
        this.true_val = true_val;
    }
    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getRules_premise_val() {
        return rules_premise_val;
    }

    public int getRules_premise_id() {
        return rules_premise_id;
    }

    public void setRules_premise_id(int rules_premise_id) {
        this.rules_premise_id = rules_premise_id;
    }
    
    public Premise(){
        
    }
    
}
