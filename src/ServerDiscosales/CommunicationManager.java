package ServerDiscosales;

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

public class CommunicationManager implements Runnable {
    private final ServerDiscord sd;
    private Socket s;
    private OutputStream outClient;
    private InputStream inClient;
    private PrintWriter writer;
    private BufferedReader reader;
    
    public CommunicationManager(Socket s, ServerDiscord sd) {
        this.s = s;
        this.sd = sd;
        
        try {
            outClient = s.getOutputStream();
            inClient = s.getInputStream();
            
            writer = new PrintWriter(new OutputStreamWriter(outClient));
            reader = new BufferedReader(new InputStreamReader(inClient));
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        DiscordManager dm = new DiscordManager();
        try {
            int cmd1 = 0;
            
            do {
                String command = reader.readLine();
                String[] input = command.split(";");
                cmd1 = Integer.parseInt(input[0]);
                float cmd2;
                switch (cmd1) {
                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    default:
                        System.out.println("Error");
                        break;
                }
                writer.flush();
            } while(cmd1!=4);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
