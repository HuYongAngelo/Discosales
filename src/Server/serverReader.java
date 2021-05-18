package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverReader implements Runnable {
    private Socket s;
    private BufferedReader in;
    private String msg;
    
    public serverReader(Socket s) {
        this.s = s;
        
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(serverReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            msg = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(serverReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMsg() {
        return msg;
    }
}