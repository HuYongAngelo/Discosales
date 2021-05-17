package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat {
    private Socket s;
    
    public Chat(Socket s) {
        this.s = s;
        
        
    }
    
    public String messaggi(String message) {
        System.out.println(message);
        return "read;"+s.getLocalAddress()+": "+message;
    }
}