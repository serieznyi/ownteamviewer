/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.log;

import java.util.ArrayList;
import javax.swing.JTextArea;
import myconnector.MyConnector;

/**
 *
 * @author serieznyi
 */
public class Log {
    
    public static final byte LOG_CLIENT = 1;
    public static final byte LOG_SERVER = 2;
    public static final byte LOG_SYSTEM = 3;
    private ArrayList<Message> messages_list;
    private static JTextArea text_area;
        
    public Log(JTextArea area) {
        MyConnector.log = this;
        this.text_area = area;
        messages_list = new ArrayList<>();
    }

    public void message(String text, byte type) 
    {   
        messages_list.add(new Message(text, type));
        this.text_area.append(text+"\n");
        System.out.println(text+"\n");
    }
    
    class Message
    {
        private String message;
        private byte type;
        
        public Message(String mess, byte type) 
        {
            this.message = mess;
            this.type = type;
        }
        
    }
}
