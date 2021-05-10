package ServerDiscosales;

import java.net.Socket;
import java.util.HashMap;

public class DiscordManager {
    private HashMap<String, ServerDiscord> lista;
    
    public DiscordManager() {
        lista = new HashMap();
        genera();
    }
    
    private void genera(){
        for(int i = 0; i < 10; i++){
            lista.put(""+i, new ServerDiscord(""+i));
        }
    }
    
    public String getListaConti(){
        String l="";
        for(String s : lista.keySet()){
            l = l +"::"+s;
        }
        return l;
    }
    
    public boolean addClient(Socket s, String nconto){
        lista.get(nconto).addClient(s);
        return true;
    }
    
    
}