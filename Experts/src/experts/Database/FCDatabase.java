/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experts.Database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import experts.Entities.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class FCDatabase {
    
    // DATABASE CONNECTOR
    private String url      = "jdbc:mysql://localhost:3306/experts_db"; 
    private String username = "root";
    private String password = "";
    
    private ArrayList <Rule> rules = new ArrayList <Rule>();
    
    public FCDatabase(){
        
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
    
    public void loadRules(){
        try {
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            Statement stmt  = (Statement) conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM RULE");
            
            while(rs.next()){
                Rule rule = new Rule(rs.getInt(1), rs.getString(2));
                rules.add(rule);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FCDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadExperts(){
        try {
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            Statement stmt  = (Statement) conn.createStatement();
            
            // LOADS DEFAULT EXPERT, EXPERT WITH ID 1
            ResultSet rs    = stmt.executeQuery("SELECT * FROM RULE");
            while(rs.next()){
                Rule rule = new Rule(rs.getInt(1), rs.getString(2));
                rules.add(rule);
            }
            
            for (int i = 0; i < rules.size(); i++){
                String query = "SELECT * FROM PREMISE P " + 
                               "JOIN RULES_PREMISE RP ON P.id = RP.premise_id " + 
                               "JOIN RULE R ON R.id = RP.rule_id " + 
                               "WHERE R.id = " + rules.get(i).getId();
                rs = stmt.executeQuery(query);
                while (rs.next()){
                    Premise premise = new Premise(rs.getInt(1), rs.getString(2), rs.getInt(3));
                    rules.get(i).premises.add(premise);
                }
            }
            
            for (int i = 0; i < rules.size(); i++){
                for (int j = 0; j < rules.get(i).premises.size(); j++){
                    String query = "SELECT * FROM ANSWER A " + 
                                   "JOIN PREMISE_ANSWER_LIST PAL ON A.id = PAL.answer_id " + 
                                   "JOIN PREMISE P ON PAL.premise_id = P.id " + 
                                   "WHERE P.id = " + rules.get(i).premises.get(i).getId();
                    rs = stmt.executeQuery(query);
                    while(rs.next()){
                        Answer answer = new Answer(rs.getInt(1), rs.getString(2));
                        rules.get(i).premises.get(j).list_of_answer.add(answer);
                    }
                }
            }
            rs.close();
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FCDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadExperts(int experts_id){
        try {
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            Statement stmt  = (Statement) conn.createStatement();
            
            // LOADS RULES / KNOWLEDGE BASE
            ResultSet rs    = stmt.executeQuery("SELECT * FROM RULE WHERE RULE.EXPERT_ID = " + experts_id);
            while(rs.next()){
                Rule rule = new Rule(rs.getInt(1), rs.getString(2));
                rules.add(rule);
            }
            
            // LOADS PREMISES DATA (ID, QUESTION, TRUE_VALUE_ID)
            for (int i = 0; i < rules.size(); i++){
                String query = "SELECT * FROM PREMISE P " + 
                               "JOIN RULES_PREMISE RP ON P.id = RP.premise_id " + 
                               "JOIN RULE R ON R.id = RP.rule_id " + 
                               "WHERE R.id = " + rules.get(i).getId();
                rs = stmt.executeQuery(query);
                while (rs.next()){
                    Premise premise = new Premise(rs.getInt(1), rs.getString(2), rs.getInt(3));
                    rules.get(i).premises.add(premise);
                }
            }
            
            // LOADS EACH RULES PREMISE's OPERATORS
            for (int i = 0; i < rules.size(); i++){
                for (int j = 0; j < rules.get(i).premises.size(); j++){
                    String query = "SELECT * FROM LOGIC_OPERATOR LO " + 
                                   "JOIN RULES_PREMISE RP ON LO.ID = RP.OPERATOR_ID " + 
                                   "JOIN PREMISE P ON P.ID = RP.PREMISE_ID " + 
                                   "WHERE P.ID = " + rules.get(i).premises.get(j).getId();
                    rs = stmt.executeQuery(query);
                    while(rs.next()){
                        LogicOperator operator = new LogicOperator(rs.getInt(1), rs.getString(2));
                        rules.get(i).premises.get(j).operator = operator;
                    }
                }
            }
            
            // lOADS PREMISES ANSWER LIST
            for (int i = 0; i < rules.size(); i++){
                for (int j = 0; j < rules.get(i).premises.size(); j++){
                    String query = "SELECT * FROM ANSWER A " + 
                                   "JOIN PREMISE_ANSWER_LIST PAL ON A.id = PAL.answer_id " + 
                                   "JOIN PREMISE P ON PAL.premise_id = P.id " + 
                                   "WHERE P.id = " + rules.get(i).premises.get(i).getId();
                    rs = stmt.executeQuery(query);
                    while(rs.next()){
                        Answer answer = new Answer(rs.getInt(1), rs.getString(2));
                        rules.get(i).premises.get(j).list_of_answer.add(answer);
                    }
                }
            }
            rs.close();
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FCDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
