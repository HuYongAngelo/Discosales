package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    private BufferedWriter bw;
    private BufferedReader br;
    // Se è true, si sono create le cartelle;
    // Se è false, esiste già la cartella con quella mail
    private boolean controllo = true;
    
    public FileManager() {
    }
    
    public void createFiles(String email) {
        if (!new File("C:\\discosales").exists()) {
            new File("C:\\discosales").mkdir();
            new File("C:\\discosales\\Users").mkdir();
        }
    }
    
    public void fileUsers(String email) {
        try {
            bw = new BufferedWriter(new FileWriter("C:\\discosales\\Users\\"+email+".txt", true));
            
            if (!new File("C:\\discosales\\Users\\"+email+".txt").exists()) {
                new File("C:\\discosales\\Users\\"+email+".txt").createNewFile();
                controllo = true;
            } else {
               controllo = false; 
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}