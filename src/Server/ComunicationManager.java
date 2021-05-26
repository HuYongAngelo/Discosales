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

public class ComunicationManager {
    private Socket s;
    private Chat chat;
    private PrintWriter out;
    private BufferedReader in;
    
    public ComunicationManager(Socket s) {
        this.s = s;
        chat = new Chat(s);
        try {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Comunication(String richiesta) {
        String[] input = richiesta.split(";");
        
        if (input[0].equalsIgnoreCase("read")) {
            System.out.println(input[1]);
        } else if (input[0].equalsIgnoreCase("login")) {
            
        } else if (input[0].equalsIgnoreCase("register")) {
            
        }
        else if (input[0].equalsIgnoreCase("write")) {
            out.println(chat.messaggi(input[1]));
        }
    }
}