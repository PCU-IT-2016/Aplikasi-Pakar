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
public class LogicOperator {
    
    private int id          = -1;
    private String operator = "";
    
    public LogicOperator(int _id, String _operator){
        id       = _id;
        operator = _operator;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    
}
