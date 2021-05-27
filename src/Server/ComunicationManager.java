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
        String output = "";
        
        if (input[0].equalsIgnoreCase("login")) {
            if (fm.fileUsersLogin(input[2], input[3])) {
                out.println("login;successo");
            } else {
                out.println("login;errore");
            }
        } else if (input[0].equalsIgnoreCase("register")) {
            if (fm.fileUsersRegister(input[1], input[2], input[3], s)) {
                System.out.println("In teoria è dentro");
                out.println("register;successo");
            } else {
                out.println("register;errore");
            }
        } else if (input[0].equalsIgnoreCase("write")) {
            out.println(chat.messaggi(input[1]));
        }
    }
}