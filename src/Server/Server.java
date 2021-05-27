package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static HashMap <Socket, String> hm = new HashMap();
    
    public static void main(String[] args) {
        
        try {
            ServerSocket server;
        
            server = new ServerSocket(5500);
            
            while (true) {
                Socket client = server.accept();
                Thread Serverino = new Thread(new ServerManaging(client));
                Serverino.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}