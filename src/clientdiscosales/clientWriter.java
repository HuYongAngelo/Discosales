package clientdiscosales;

import static clientdiscosales.clientReader.risposta;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clientWriter implements Runnable {
    private Socket s;
    private PrintWriter out;
    private Scanner input;
    private boolean fermo = false;
    private Login l;
    private String message;
    
    public clientWriter(Socket s) {
        this.s = s;
        input = new Scanner(System.in);
        
        l = new Login();
        try {
            out = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(clientReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        out.print(l.controllo());
        do {
            writeMessage();
            
            out.println(message);
            if (message.endsWith("stop")) {
                stop();
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(clientWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!fermo);
    }
    
    public void writeMessage() {
        String message = input.nextLine();
        
        this.message = "write;"+message;
    }
    
    public void stop() {
        fermo = true;
    }
}