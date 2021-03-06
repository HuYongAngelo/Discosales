package clientdiscosales;

import static clientdiscosales.ClientDiscosales.ferma;
import static clientdiscosales.ClientDiscosales.registrazione;
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
    private Scanner sc;
    private Scanner input;
    private boolean fermo = false;
    private Login l;
    private Register r;
    private String message;
    
    public clientWriter(Socket s) {
        this.s = s;
        sc = new Scanner(System.in);
        input = new Scanner(System.in);
        
        l = new Login();
        r = new Register();
        try {
            out = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(clientReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        int opzione;
        do {
            System.out.println("MENÚ\n");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit\n");
            opzione = sc.nextInt();
            
            if (opzione == 1) {
                String s = r.controllo();
                out.println(s);
                out.flush();
                if (registrazione = true) {
                    opzione = 3;
                    System.out.println("Registrazione avvenuto con successo\n");
                }
            } else if (opzione == 2) {
                String s = l.controllo();
                out.println(s);
                out.flush();
                if (registrazione = true) {
                    opzione = 3;
                    System.out.println("Login avvenuto con successo\n");
                }
            }
            System.out.println("");
        } while(opzione != 3);
        
        do {
            while(registrazione) {
                writeMessage();

                out.println(message);
                if (message.endsWith("stop")) {
                    stop();
                    ferma=true;
                    out.println("stop");
                }
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(clientWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!fermo);
        out.close();
    }
    
    public void writeMessage() {
        String message = input.nextLine();
        
        this.message = "write;"+message;
    }
    
    public void stop() {
        fermo = true;
    }
}