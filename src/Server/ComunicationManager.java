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
        String cmd;
        String[] input = richiesta.split(";");
        cmd = input[0];

        if (cmd.equalsIgnoreCase("write")) {
            out.println(chat.messaggi(input[1]));
        }
    }
}