/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experts.Entities;

/**
 *
 * @author owner
 */

public class Answer {
    int id = -1;
    String answer = "";
    
    public Answer(){
        
    }
    
    public Answer(int _id, String _answer){
        id = _id;
        answer = _answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
