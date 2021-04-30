package ServerDiscosales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerManager implements Runnable {
    private Socket s;
    private String server;
    
    public ServerManager(Socket s, String server) {
        this.s = s;
        this.server = server;
    }

    @Override
    public void run() {
        String nServer;
        try {
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            pw.println(server);
            nServer = in.readLine();
            ServerThread.dm.addClient(s, nServer);
        } catch (IOException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
