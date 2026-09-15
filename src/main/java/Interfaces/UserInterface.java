package Interfaces;
import java.util.*;

public class UserInterface {
    public UserInterface(){
        Scanner sc = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║       Tablegame Management       ║");
        System.out.println("╚══════════════════════════════════╝");
        boolean run = true;
        while (run) {
            System.out.println(" ");
            System.out.println("1. Member management");
            System.out.println("2. Game management");
            System.out.println("3. gameNight management");
            System.out.println("4. Publisher Management");
            System.out.println("0. Exit");
            switch (sc.nextLine()) {
                case "1":
                    new MemberInterface();
                    break;
                case "2":
                    new GameInterface();
                    break;
                case "3":
                    new GameNightInterface();
                    break;
                case "4":
                    new PublisherInterface();
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }
}