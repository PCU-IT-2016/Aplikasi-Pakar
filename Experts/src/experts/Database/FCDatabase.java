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

    public ArrayList <Rule> getRules() {
        return rules;
    }
    
    public ArrayList <Rule> loadRules(int expert_id){
        
        ArrayList<Rule> result = new ArrayList<Rule>();
        String query = "SELECT * FROM RULE WHERE RULE.EXPERT_ID = " + expert_id;
        
        try{
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            Statement stmt  = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); // RETURNS PREMISE PREMISES
            while(rs.next()) {
                Rule rule = new Rule(rs.getInt(1), rs.getString(2));
                result.add(rule);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException e){
            return null;
        }
        
        return result;
    }
    
    public ArrayList <Premise> loadPremises(Premise premise) throws SQLException{
        ArrayList<Premise> result = new ArrayList<Premise>(); // SHOULD BE NULL 
        String query = "        SELECT * FROM PREMISE P\n" +
                       "        WHERE P.id IN (\n" +
                       "            SELECT PP.premise_premise_id FROM premise_premises PP\n" +
                       "            JOIN rules_premise RP ON PP.rules_premise_id = RP.id\n" +
                       "            WHERE PP.rules_premise_id = " + premise.getRules_premise_id() + 
                       "        \n)";
        String query2 = "SELECT *, PP.PREMISE_VALUE FROM PREMISE P\n" +
                        "JOIN premise_premises PP ON P.id = PP.premise_premise_id\n" +
                        "WHERE PP.rules_premise_id = " + premise.getRules_premise_id();
        try {
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            Statement stmt  = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query2); // RETURNS PREMISE PREMISES
            // INITIALIZE `result` HERE 
            while(rs.next()) {
                Premise loaded_premise = new Premise(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt("PP.PREMISE_VALUE"));
                loaded_premise.premises = loadPremises(loaded_premise);
                // loaded_premise.showPremiseOnConsole();
                loaded_premise.list_of_answer = loadAnswers(loaded_premise);
                result.add(loaded_premise);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
        return result;
    }
    
    public ArrayList <Answer> loadAnswers(Premise premise){
        
        ArrayList<Answer> result = new ArrayList<Answer>();
        String query =  "SELECT * FROM ANSWER A " + 
                        "JOIN PREMISE_ANSWER_LIST PAL ON A.id = PAL.answer_id " + 
                        "JOIN PREMISE P ON PAL.premise_id = P.id " + 
                        "WHERE P.id = " + premise.getId();
        try{
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            Statement stmt  = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); // RETURNS PREMISE PREMISES
            while(rs.next()) {
                Answer answer = new Answer(rs.getInt(1), rs.getString(2));
                result.add(answer);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException e){
            return null;
        }
        
        return result;
    }
    
    public int loadPremiseValue(Premise premise){
        return 0;
    }
    
    public void loadExperts(int experts_id){
        try {
            Connection conn = (Connection) DriverManager.getConnection(url, username, password);
            Statement stmt  = (Statement) conn.createStatement();
            
            // LOADS RULES / KNOWLEDGE BASE
            // RULES
            ResultSet rs    = stmt.executeQuery("SELECT * FROM RULE WHERE RULE.EXPERT_ID = " + experts_id);
            while(rs.next()){
                Rule rule = new Rule(rs.getInt(1), rs.getString(2));
                rules.add(rule);
            }
            
            // LOADS PREMISES DATA (ID, QUESTION, TRUE_VALUE_ID), PREMISE_VAL (PREMIS = PREMISE_VAL)
            // RULES PREMISES
            for (int i = 0; i < rules.size(); i++){
                String query = "SELECT *, RP.id, RP.PREMISE_VAL FROM PREMISE P " + 
                               "JOIN RULES_PREMISE RP ON P.id = RP.premise_id " + 
                               "JOIN RULE R ON R.id = RP.rule_id " + 
                               "WHERE R.id = " + rules.get(i).getId();
                rs = stmt.executeQuery(query);
                while (rs.next()){
                    Premise premise = new Premise(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt("RP.PREMISE_VAL"));
                    premise.setRules_premise_id(rs.getInt("RP.id"));
                    rules.get(i).premises.add(premise);
                }
            }
            
            // lOADS PREMISES ANSWER LIST
            for (int i = 0; i < rules.size(); i++){
                for (int j = 0; j < rules.get(i).premises.size(); j++){
                    String query = "SELECT * FROM ANSWER A " + 
                                   "JOIN PREMISE_ANSWER_LIST PAL ON A.id = PAL.answer_id " + 
                                   "JOIN PREMISE P ON PAL.premise_id = P.id " + 
                                   "WHERE P.id = " + rules.get(i).premises.get(j).getId();
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
            
            // LOAD PREMISE PREMISES
            for (int i = 0; i < rules.size(); i++){
                rules.get(i).showRuleOnConsole();
                for (int j = 0; j < rules.get(i).premises.size(); j++){
                    Premise p = rules.get(i).premises.get(j);
                    // p.showPremiseOnConsole();
                    rules.get(i).premises.get(j).premises = loadPremises(p);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FCDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
