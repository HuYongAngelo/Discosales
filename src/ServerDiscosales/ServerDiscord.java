package ServerDiscosales;

import java.net.Socket;
import java.util.Vector;

class ServerDiscord {
    private String id;
    private Vector<CommunicationManager> listaClient;
    
    public ServerDiscord(String id) {
        this.id = id;
        listaClient = new Vector<CommunicationManager>();
    }
    
    public void addClient(Socket s){
        listaClient.add(new CommunicationManager(s, this));
        new Thread(listaClient.elementAt(listaClient.size()-1)).start();
    }
}
