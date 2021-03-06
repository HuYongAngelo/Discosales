package clientdiscosales;

import static clientdiscosales.ClientDiscosales.registrazione;
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
                } else if (a[0].equalsIgnoreCase("write")) {
                    System.out.println(a[1]);
                } else if (a[0].equalsIgnoreCase("register")) {
                    if (a[1].equalsIgnoreCase("successo")) {
                        registrazione = true;
                    } else if (a[1].equalsIgnoreCase("errore")) {
                        System.out.println("ERRORE: EMAIL, USERNAME o PASSWORD già presenti");
                        registrazione = false;
                    }
                } else if (a[0].equalsIgnoreCase("login")) {
                    if (a[1].equalsIgnoreCase("successo")) {
                        registrazione = true;
                    } else if (a[1].equalsIgnoreCase("errore")) {
                        System.out.println("ERRORE: USERNAME o PASSWORD errata");
                        registrazione = false;
                    }
                }
                Thread.sleep(50);
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