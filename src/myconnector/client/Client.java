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
    
    public Client(String ip) {
        try {
            System.out.println("Welcome to Client side");
            
            fromserver = new Socket("",4444);
            BufferedReader in  = new
             BufferedReader(new 
              InputStreamReader(fromserver.getInputStream()));
            PrintWriter    out = new 
             PrintWriter(fromserver.getOutputStream(),true);
            BufferedReader inu = new 
             BufferedReader(new InputStreamReader(System.in));    
            
            /*
             String fuser,fserver;

            while ((fuser = inu.readLine())!=null) {
              out.println(fuser);
              fserver = in.readLine();
              System.out.println(fserver);
              if (fuser.equalsIgnoreCase("close")) break;
              if (fuser.equalsIgnoreCase("exit")) break;
            }

            out.close();
            in.close();
            inu.close();
            fromserver.close();
              
             */
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {   
        
        
    }
    
}
