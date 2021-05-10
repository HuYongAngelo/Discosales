package clientdiscosales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDiscosales {

    public static void main(String[] args) {
        Socket server;
        try {
            server = new Socket("127.0.0.1", 5500);

            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            Scanner tastiera = new Scanner(System.in);
            do {
                out.write("write;"+tastiera.nextLine());
                
                System.out.println(in.readLine());
            } while(true);
        } catch (IOException ex) {
            Logger.getLogger(ClientDiscosales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
