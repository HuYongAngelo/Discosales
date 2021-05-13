package Server;

import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerManaging implements Runnable {
    private Socket clientSocket;
    private ComunicationManager cm;
    
    public ServerManaging(Socket clientSocket) {
        this.clientSocket = clientSocket;
        cm = new ComunicationManager(clientSocket);
    }
    
    @Override
    public void run() {
        System.out.println("Serverino  partito: "+ clientSocket.getInetAddress());
        try {
            Thread t = new Thread(cm);
            t.start();
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String richiesta = "";
            
            
                       
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerManaging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
