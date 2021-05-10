package ServerDiscosales;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread {

    private ServerSocket socketClient;
    private Socket s;
    private ServerManager sm;
    public static final DiscordManager dm = new DiscordManager();

    public ServerThread(int port) {
        try {
            socketClient = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startServer() {
        try {
            do {
                s = socketClient.accept();

                sm = new ServerManager(s, dm.getListaConti());
                Thread t = new Thread(sm);

                t.start();
            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
