

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author serieznyi
 */
public class Log extends JScrollPane{
    
    private JTextArea text_area;
    
    public Log() {
        text_area = new JTextArea();     
        
        this.add(this.text_area);
    }

    
    
}