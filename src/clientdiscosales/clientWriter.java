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
        String msg;
        out.print(l.controllo());
        do {
            System.out.print("Write: ");
            msg = input.nextLine();
            out.println("write;"+msg);
            if (msg.equalsIgnoreCase("stop")) {
                stop();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(clientWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!fermo);
    }
    
    public void stop() {
        fermo = true;
    }
}