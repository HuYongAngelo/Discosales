package clientdiscosales;

import static clientdiscosales.clientReader.risposta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDiscosales {
    public static boolean registrazione = false;
    
    public static void main(String[] args) {
        Socket server;
        try {
            server = new Socket("127.0.0.1", 5500);
            clientReader cr = new clientReader(server);
            clientWriter cw = new clientWriter(server);
            Thread tcr = new Thread(cr);
            Thread tcw = new Thread(cw);
            
            tcr.start();
            tcw.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientDiscosales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
