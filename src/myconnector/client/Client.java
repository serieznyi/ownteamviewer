/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serieznyi
 */
public class Client extends Thread {
    
    Socket fromserver = null;
    BufferedReader in = null;
    PrintWriter    out = null;
    BufferedReader inu = null;
    
    public Client(String ip) {
        try {
            System.out.println("Welcome to Client side");
            
            fromserver = new Socket(ip,4444);
            
            System.out.println("We connect to server");
            
            this.in  = new
             BufferedReader(new 
              InputStreamReader(fromserver.getInputStream()));
            this.out = new 
             PrintWriter(fromserver.getOutputStream(),true);
            this.inu = new 
             BufferedReader(new InputStreamReader(System.in));    
            
                      
              
             
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {   
        try {
            String fuser,fserver;

                while ((fuser = this.inu.readLine())!=null) {
                  out.println(fuser);
                  fserver = this.in.readLine();
                  System.out.println(fserver);
                  if (fuser.equalsIgnoreCase("close")) break;
                  if (fuser.equalsIgnoreCase("exit")) break;
                }

                this.out.close();
                this.in.close();
                this.inu.close();
                this.fromserver.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
