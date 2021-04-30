package ServerDiscosales;

import java.util.Scanner;

public class ServerDiscosales {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci la porta");
        new ServerThread(sc.nextInt()).startServer();
    }
}