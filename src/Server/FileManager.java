package Server;

import static Server.Server.hm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    private BufferedWriter bw;
    private BufferedReader br;
    
    public FileManager() {
        createFiles();
    }
    
    public void createFiles() {
        if (!new File("C:\\discosales").exists()) {
            new File("C:\\discosales").mkdir();
            new File("C:\\discosales\\Users").mkdir();
        }
    }
    
    public boolean fileUsersRegister(String email, String username, String password, Socket s) {
     // Se è true, si sono create le cartelle;
     // Se è false, esiste già la cartella con quella mail
        boolean controllo = true;
        try {
            if (!new File("C:\\discosales\\Users\\"+username+".txt").exists()) {
                new File("C:\\discosales\\Users\\"+username+".txt").createNewFile();
                bw = new BufferedWriter(new FileWriter("C:\\discosales\\Users\\"+username+".txt"));
                bw.write(email+":"+username+":"+password);
                bw.flush();
                bw.close();
                hm.put(s, username);
                controllo = true;
            } else {
                controllo = false; 
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(controllo);
        return controllo;
    }
    
    public boolean fileUsersLogin(String username, String password) {
     // Se è true
     // Se è false
        boolean controllo = true;
        String[] dati;
        File f = new File("C:\\discosales\\Users\\"+username+".txt");
        
        if (f.exists()) {
            try {
                br = new BufferedReader(new FileReader(f));

                dati = br.readLine().split(":");
                
                if (dati[2].equals(password)) {
                    controllo = true;
                } else {
                    controllo = false;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            controllo = false;
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controllo;
    }
}