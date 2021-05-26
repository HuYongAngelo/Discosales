package clientdiscosales;

import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    private Scanner sc = new Scanner(System.in);
    
    public Login() {
    }
    
    public String controllo() {
        String credenziali = "";
        
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        
        credenziali = "login;"+username+";"+password;
        
        return credenziali;
    }
}