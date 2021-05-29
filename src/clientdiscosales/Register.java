package clientdiscosales;

import java.util.Scanner;

public class Register {
    private String email;
    private String username;
    private String password;
    private Scanner sc = new Scanner(System.in);
    
    public Register() {
    }
    
    public String controllo() {
        String credenziali = "";
        
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        
        credenziali = "register;"+email+";"+username+";"+password;
        
        return credenziali;
    }
}