/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myconnector.MyConnector;
import myconnector.log.Log;

/**
 *
 * @author serieznyi
 */
public class Server extends Network{

    private ServerSocket servers;    //Сокет сервера
    private int port = 4444;

    public Server() throws UnknownHostException 
    {
        super();
        MyConnector.log.message("Start server", Log.LOG_SERVER);
        
        // create server socket
        try {
            servers = new ServerSocket(port);
          //  String ip = servers.getInetAddress().toString();
            MyConnector.main.setIP("172.27.242.201");
        } catch (IOException e) {
            MyConnector.log.message("Couldn't listen to port"+port, Log.LOG_SERVER);
            System.exit(-1);
        }
        
        /*
         in  = new BufferedReader(new 
         InputStreamReader(fromclient.getInputStream()));
         out = new PrintWriter(fromclient.getOutputStream(),true);
         String         input,output;

         System.out.println("Wait for messages");
         while ((input = in.readLine()) != null) {
         if (input.equalsIgnoreCase("exit")) break;
         out.println("S ::: "+input);
         System.out.println(input);
         }
         out.close();
         in.close();
         fromclient.close();
         servers.close();*/

    }

    @Override
    public void run() {
            
        try {
         //   System.out.println("IP:"+this.getCurrentIP());
            MyConnector.log.message("Waiting for a client...", Log.LOG_SERVER);
            MyConnector.main.setMode("Server");
            this.socket = this.servers.accept();
            MyConnector.log.message("Client connected", Log.LOG_SERVER);
            MyConnector.main.showPanel("working_panel");

            in  = new BufferedReader(new 
            InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
           // System.out.println("Wait for messages");
            
            Thread Server=new Thread(new Runnable() {
                        public synchronized  void run() {
                    try {
                        String input,output;
                        
                        while ((input = in.readLine()) != null) 
                        {
                          //  if (input.equalsIgnoreCase("exit"))
                          //          break;
                             //   out.println("S ::: "+input);
                                MyConnector.log.message(input, Log.LOG_CLIENT);
                               // System.out.println(input);
                        }
                        } 
                        catch (IOException ex) 
                        {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                    });
            
            
            this.out.close();
            this.in.close();
            this.socket.close();
            this.servers.close();
            
            
            
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

    }
    
    public String getCurrentIP() {
        String result = null;
        try {
            BufferedReader reader = null;
            try {
                URL url = new URL("http://myip.by/");
                InputStream inputStream = null;
                inputStream = url.openStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder allText = new StringBuilder();
                char[] buff = new char[1024];
 
                int count = 0;
                while ((count = reader.read(buff)) != -1) {
                    allText.append(buff, 0, count);
                }
                // Строка содержащая IP имеет следующий вид 
                // <a href="whois.php?127.0.0.1">whois 127.0.0.1</a> 
                Integer indStart = allText.indexOf("\">whois ");
                Integer indEnd = allText.indexOf("</a>", indStart);
 
                String ipAddress = new String(allText.substring(indStart + 8, indEnd));
                if (ipAddress.split("\\.").length == 4) { // минимальная (неполная) 
                //проверка что выбранный текст является ip адресом.
                    result = ipAddress;
                }
            } catch (MalformedURLException ex) {
                 ex.printStackTrace();
            } catch (IOException ex) {
                 ex.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public void send_message() {
        
    }
}
