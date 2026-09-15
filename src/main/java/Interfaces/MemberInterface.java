package Interfaces;

import Dao.MemberDao;
import Models.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberInterface {

    public MemberInterface() {
        try {
            memberManagement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void memberManagement() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║       MEMBER MANAGEMENT          ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean run = true;
        while (run) {
            System.out.println(" ");
            System.out.println("1. Find member");
            System.out.println("2. Create member");
            System.out.println("3. Update member");
            System.out.println("0. Back");

            String input = sc.nextLine();
            switch (input) {
                case "1":
                    Findmember();
                    break;
                case "2":
                    createMember();
                    break;
                case "3":
                    updateMember();
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private void updateMember() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID");
        int id = sc.nextInt();
        boolean run = true;
        while (run) {
            System.out.println(" ");
            System.out.println("1. Change first name");
            System.out.println("2. Change last name");
            System.out.println("3. Change status");
            System.out.println("4. Change Email");
            System.out.println("0. Back");
            sc.nextLine();
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Enter new First Name");
                    String firstName = sc.nextLine();
                    MemberDao.updateFirstname(firstName, id);
                    break;
                case "2":
                    System.out.println("Enter new last Name");
                    String lastname = sc.nextLine();
                    MemberDao.updateLastname(lastname, id);
                    break;
                case "3":
                    System.out.println("Enter new status Active or Inactive");
                    String status = sc.nextLine();
                    if (status.equalsIgnoreCase("Inactive")) {
                        MemberDao.updateStatus(false, id);
                    } else if (status.equalsIgnoreCase("active")) {
                        MemberDao.updateStatus(true, id);
                    } else {
                        System.out.println("Invalid input");
                        run = true;
                    }
                    break;
                case "0":
                    run = false;
                    break;
                case "4":
                    System.out.println("Enter new First Name");
                    String email = sc.nextLine();
                    MemberDao.updateEmail(email, id);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        System.out.println("Done");
    }

    private void Findmember() {
        Scanner sc = new Scanner(System.in);
        List<Member> member = new ArrayList<>();
        boolean run = true;
        while (run) {
            System.out.println("1. Find member by ID");
            System.out.println("2. Find member by name");
            System.out.println("3. Show all");
            System.out.println("0. Back");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("enter ID");
                    int id = sc.nextInt();
                    Member me = MemberDao.findById(id);
                    if (me != null) {
                        printMember(me);
                    }else{
                        System.out.println("Invalid ID or member doesn't exist");
                    }
                    run = false;
                    break;
                case "2":
                    System.out.println("enter name");
                    String name = sc.nextLine();
                    member = MemberDao.findByName(name);
                    if (member != null) {
                        for (Member m : member) {
                            printMember(m);
                        }
                    }
                    run = false;
                    break;
                case "3":
                    member = MemberDao.findAll();
                    for (Member m : member) {
                        printMember(m);
                    }
                    run = false;
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
    private void createMember() {
        Scanner sc = new Scanner(System.in);
        Member newMem = new Member("","","", new java.sql.Date(new java.util.Date().getTime()),true);
        System.out.println("Enter member first_name");
        newMem.setFirstName(sc.nextLine());
        System.out.println("Enter member last_name");
        newMem.setLastName(sc.nextLine());
        System.out.println("Enter member email");
        newMem.setEmail(sc.nextLine());
        MemberDao.createMember(newMem);

    }

    private void printMember(Member m) {
        System.out.println(m.getId() + ": " + m.getFirstName() + " " + m.getLastName() + " || " + m.getEmail() + " || " + m.getJoinDate() + "IsActive:" + m.isActive());
    }


}
