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
    private FileManager fm;
    
    public ComunicationManager(Socket s) {
        this.s = s;
        chat = new Chat(s);
        fm = new FileManager();
        try {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Comunication(String richiesta) {
        String[] input = richiesta.split(";");
        
        if (input[0].equalsIgnoreCase("login")) {
            if (fm.fileUsersLogin(input[1], input[2], s)) {
                out.println("login;successo");
            } else {
                out.println("login;errore");
            }
        } else if (input[0].equalsIgnoreCase("register")) {
            if (fm.fileUsersRegister(input[1], input[2], input[3], s)) {
                out.println("register;successo");
            } else {
                out.println("register;errore");
            }
        } else if (input[0].equalsIgnoreCase("write")) {
            chat.messaggi(input[1]);
            chat.reading(out);
        }
    }
    
    public void writeStop() {
        out.println("stop;");
    }
}