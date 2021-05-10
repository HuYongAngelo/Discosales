package ServerDiscosales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat {
    private Socket s;
    private OutputStream outClient;
    private InputStream inClient;
    private PrintWriter writer;
    private BufferedReader reader;
    
    public Chat(Socket s) {
        this.s = s;
        
        try {
            outClient = s.getOutputStream();
            inClient = s.getInputStream();
            
            writer = new PrintWriter(new OutputStreamWriter(outClient));
            reader = new BufferedReader(new InputStreamReader(inClient));
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void messaggi(String message) {
        writer.print(s.getLocalAddress()+message);
    }
}