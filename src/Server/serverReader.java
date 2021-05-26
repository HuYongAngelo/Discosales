package Server;

import static Server.ServerManaging.cm;
import clientdiscosales.clientReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverReader implements Runnable {
    private Socket s;
    private BufferedReader in;
    public static String risposta;
    private boolean fermo = false;
    
    public serverReader(Socket s) {
        this.s = s;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(serverReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        String[] a;
        try {
            do {
                risposta = in.readLine();
                a = risposta.split(";");
                if (a[1].equalsIgnoreCase("stop")) {
                    stop();
                } else if (a[0].equalsIgnoreCase("read")) {
                    System.out.println(a[1]);
                } else if (a[0].equalsIgnoreCase("login")) {
                    
                } else if (a[0].equalsIgnoreCase("write")) {
                    cm.Comunication(risposta);
                }
                
                Thread.sleep(250);
            } while(!fermo);
        } catch (IOException ex) {
            Logger.getLogger(clientReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(serverReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop() {
        fermo = true;
    }
}