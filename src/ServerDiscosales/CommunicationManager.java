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

public class CommunicationManager implements Runnable {
    private final ServerDiscord sd;
    private Chat chat;
    
    private Socket s;
    private OutputStream outClient;
    private InputStream inClient;
    private PrintWriter writer;
    private BufferedReader reader;
    
    public CommunicationManager(Socket s, ServerDiscord sd) {
        this.s = s;
        this.sd = sd;
        chat = new Chat(s);
        
        try {
            outClient = s.getOutputStream();
            inClient = s.getInputStream();
            
            writer = new PrintWriter(new OutputStreamWriter(outClient));
            reader = new BufferedReader(new InputStreamReader(inClient));
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        DiscordManager dm = new DiscordManager();
        
        try {
            String cmd;
            String command = reader.readLine();
            String[] input = command.split(";");
            cmd = input[0];
            
            if (cmd.equalsIgnoreCase("write")) {
                chat.messaggi();
            }
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}