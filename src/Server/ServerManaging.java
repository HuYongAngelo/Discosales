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
    
    private serverReader sr;
    private serverWriter sw;
    
    public ServerManaging(Socket clientSocket) {
        this.clientSocket = clientSocket;
        cm = new ComunicationManager(clientSocket); 
        
        sr = new serverReader(clientSocket);
        sw = new serverWriter(clientSocket);
    }
    
    @Override
    public void run() {
        Thread tsr = new Thread(sr);
        Thread tsw = new Thread(sw);
        
        System.out.println("Serverino  partito: "+ clientSocket.getInetAddress());

        tsr.start();
        tsw.start();
    }
}
