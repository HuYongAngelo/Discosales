package Server;

import clientdiscosales.clientReader;
import clientdiscosales.clientWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverWriter implements Runnable {
    private Socket s;
    private PrintWriter out;
    private Scanner input;
    private boolean fermo = false;
    
    private boolean registerCheck = false;
    private boolean loginCheck = false;
    
    public serverWriter(Socket s) {
        this.s = s;
        input = new Scanner(System.in);
        try {
            out = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(serverWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        do {
            if (registerCheck) {
                
            }
            
            
        /*  System.out.print("Write: ");
            msg = input.nextLine();
            out.println("write;"+msg);
            if (msg.equalsIgnoreCase("stop")) {
                stop();
            }
        */
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(clientWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!fermo);
    }
    
    public void setRegistrazione(boolean registerCheck) {
        this.registerCheck = registerCheck;
    }
    
    public void setLogin(boolean loginCheck) {
        this.loginCheck = loginCheck;
    }
    
    public void stop() {
        fermo = true;
    }
}