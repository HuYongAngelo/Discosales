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
    private ComunicationManager cm;
    public static HashMap <Socket, String> hm;
    
    public ServerManaging(Socket clientSocket) {
        this.clientSocket = clientSocket;
        cm = new ComunicationManager(clientSocket);
        hm = new HashMap();
    }
    
    public void riempiLista(String[] user) {
        hm.put(clientSocket, user[1]);
    }
    
    @Override
    public void run() {
        try {
            String richiesta;
            System.out.println("Serverino  partito: "+ clientSocket.getInetAddress());
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            riempiLista(in.readLine().split(";"));
            
            do {
                richiesta = in.readLine();
                cm.Comunication(richiesta);
            } while(!richiesta.startsWith("exit"));
            
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerManaging.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
