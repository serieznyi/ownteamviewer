/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

/**
 *
 * @author serieznyi
 */
public class Server extends Thread {

    private BufferedReader in = null;       // Поток для чтения
    private PrintWriter out = null;       // Поток для записи
    private ServerSocket servers = null;    //Сокет сервера
    private Socket fromclient = null;       // Сокет подключенного клиента

    public Server() {

        super();

        System.out.println("Welcome to Server side");
        // create server socket
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
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
            System.out.print("Waiting for a client...");
            this.fromclient = this.servers.accept();
            System.out.println("Client connected");
            
            in  = new BufferedReader(new 
            InputStreamReader(fromclient.getInputStream()));
            out = new PrintWriter(fromclient.getOutputStream(),true);
            String         input,output;

            System.out.println("Wait for messages");
            while ((input = in.readLine()) != null) 
            {
            if (input.equalsIgnoreCase("exit"))
                break;
            this.out.println("S ::: "+input);
            System.out.println(input);
            }
            this.out.close();
            this.in.close();
            this.fromclient.close();
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
}
