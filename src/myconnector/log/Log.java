/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import myconnector.MyConnector;
import myconnector.network.Network;

/**
 *
 * @author serieznyi
 */
public class Log extends Thread{
    
    public static final byte LOG_CLIENT = 1;
    public static final byte LOG_SERVER = 2;
    public static final byte LOG_SYSTEM = 3;
    protected PrintWriter out;
    protected BufferedReader in;
    private ArrayList<Message> messages_list;
    private static JTextArea text_area;
    private Socket socketLog;
        
    public Log(JTextArea area, Socket socket) {
        try {
            MyConnector.log = this;
            this.socketLog = socket;
            
            this.in = new BufferedReader(new InputStreamReader(this.socketLog.getInputStream()));
            this.out = new PrintWriter(this.socketLog.getOutputStream(), true);
            
            this.text_area = area;
            messages_list = new ArrayList<>();
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void show_message(String text, byte type) 
    {   
        messages_list.add(new Message(text, type));
        this.text_area.append(text+"\n");
        System.out.println(text+"\n");
    }
    
    public void show_message(String text) 
    {   
        byte type = (MyConnector.main.getMode().equals("Client")
                ? Log.LOG_CLIENT
                : Log.LOG_SERVER);
        
        show_message(text, type);
    }
    
    public void pull_message(String message, byte type) 
    {
        this.show_message(message, type);
        
        this.out.println(message);
        this.out.flush();
    }
    
    public void pull_message(String message) 
    {
        byte type = (MyConnector.main.getMode().equals("Client")
                ? Log.LOG_CLIENT
                : Log.LOG_SERVER);
        
        this.pull_message(message, type);
    }

    public void read_message() {
        try {
            String input, out;

            while ((input = this.in.readLine()) != null) {

                if (input.equalsIgnoreCase(Network.NET_CLOSE_CONNECTION)) {
                    String message = MyConnector.main.getMode().equals("Client")
                            ? "Соединение прервано сервером!"
                            : "Соединение прервано клиентом!";
                    
                   // this.show_message(message, Log.LOG_SERVER);
                    
                    JOptionPane.showMessageDialog(MyConnector.main,
                        message);
                    
                    break;
                }
                else if (input.equalsIgnoreCase(Network.NET_ERROR_CLOSE)) {
                    this.show_message("Неожиданное завершение!", Log.LOG_SERVER);
                    break;
                }
                else
                {
                    this.show_message(input, Log.LOG_CLIENT);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    @Override
    public void run() 
    {
        this.read_message();
    }
}
