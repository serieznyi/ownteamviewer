/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 *
 * @author serieznyi
 */
public class Network extends Thread {

    public static final String NET_CLOSE_CONNECTION = ":close";
    public static final String NET_ERROR_CLOSE = ":error";
    protected Socket socketViewer;
    protected Socket socketLog;
    protected Thread isAlive;
    final public int portViewer = 4444;
    final public int portLog = 4445;

    public Network() {
    }

    public void keep_alive() {
        final Socket temp_socket = this.socketViewer;
        this.isAlive = new Thread(new Runnable() {
            public void run() {
                System.out.println("start check");
                  System.out.println(temp_socket.isClosed());
            }
        });
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

    public void stopConnection() { }
}
