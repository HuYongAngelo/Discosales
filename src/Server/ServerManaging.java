package Server;

import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerManaging implements Runnable {
    private Socket clientSocket;
    public static ComunicationManager cm;
    public static HashMap <Socket, String> hm;
    
    private serverReader sr;
    private serverWriter sw;
    
    public ServerManaging(Socket clientSocket) {
        this.clientSocket = clientSocket;
        cm = new ComunicationManager(clientSocket);
        hm = new HashMap();
        
        sr = new serverReader(clientSocket);
        sw = new serverWriter(clientSocket);
    }
    
    public void riempiLista(String[] user) {
        hm.put(clientSocket, user[1]);
    }
    
    @Override
    public void run() {
        Thread tsr = new Thread(sr);
        Thread tsw = new Thread(sw);
        
        try {
            System.out.println("Serverino  partito: "+ clientSocket.getInetAddress());
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            riempiLista(in.readLine().split(";"));
            
            tsr.start();
            tsw.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerManaging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
