package Server;

import static Server.Server.hm;
import static Server.Server.messages;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat {
    private Socket s;
    private BufferedWriter bw;
    private BufferedReader br;
    
    public Chat(Socket s) {
        this.s = s;
    }
    
    public void messaggi(String message) {
        messages.add(hm.get(s)+": "+message);
        System.out.println(hm.get(s)+": "+message);
        
        try {
            bw = new BufferedWriter(new FileWriter("C:\\discosales\\chatLog.txt", true));
            
            bw.write(messages.toString());
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        messages.clear();
    }
    
    public void reading(PrintWriter out) {
        String messages = "";
        try {
            br = new BufferedReader(new FileReader("C:\\discosales\\chatLog.txt"));
            
            do {
                messages = br.readLine();
                if (messages != null) {
                    out.println("read;"+messages);
                }
            } while(messages != null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}