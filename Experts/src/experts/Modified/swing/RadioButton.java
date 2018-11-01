/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experts.Modified.swing;

import javax.swing.JRadioButton;

/**
 *
 * @author owner
 */
public class RadioButton {
    JRadioButton button = new JRadioButton("text");
    Object value = new Object();
    
    public JRadioButton getButton() {
        return button;
    }

    public void setButton(JRadioButton button) {
        this.button = button;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public void setText(String text){
        button.setText(text);
    }
    
    public String getText(){
        return button.getText();
    }
    
}
