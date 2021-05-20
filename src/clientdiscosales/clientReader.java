package clientdiscosales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clientReader implements Runnable {
    private Socket s;
    private BufferedReader in;
    public static String risposta;
    private boolean fermo = false;
    
    public clientReader(Socket s) {
        this.s = s;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(clientReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        String[] a;
        try {
            do {
                risposta = in.readLine();
                a = risposta.split(";");
                if (a[0].equalsIgnoreCase("stop")) {
                    stop();
                } else if (a[0].equalsIgnoreCase("read")) {
                    System.out.println(a[1]);
                }
                Thread.sleep(250);
            } while(!fermo);
        } catch (IOException ex) {
            Logger.getLogger(clientReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(clientWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop() {
        fermo = true;
    }
}