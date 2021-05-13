package Server;

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

public class ComunicationManager implements Runnable {
    private Socket s;
    private OutputStream outClient;
    private InputStream inClient;
    private PrintWriter writer;
    private BufferedReader reader;
    private Chat chat;
    
    public ComunicationManager(Socket s) {
        this.s = s;
        chat = new Chat(s);
        try {
            outClient = s.getOutputStream();
            inClient = s.getInputStream();
            
            writer = new PrintWriter(new OutputStreamWriter(outClient));
            reader = new BufferedReader(new InputStreamReader(inClient));
        } catch (IOException ex) {
            Logger.getLogger(ComunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            String cmd;
            String command = reader.readLine();
            String[] input = command.split(";");
            cmd = input[0];
            
            if (cmd.equalsIgnoreCase("write")) {
                System.out.println("Dentro if CM");
                chat.messaggi(input[0]);
            }
        } catch (IOException ex) {
            Logger.getLogger(ComunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}