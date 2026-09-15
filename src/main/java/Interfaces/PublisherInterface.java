package Interfaces;

import Dao.PublisherDao;
import Models.Publisher;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PublisherInterface {
    public PublisherInterface() {
        try {
            publisherManagement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void publisherManagement() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║       PUBLISHER MANAGEMENT       ║");
        System.out.println("╚══════════════════════════════════╝");
        boolean run = true;
        while (run) {
            System.out.println(" ");
            System.out.println("1. Create Publisher");
            System.out.println("2. find publisherByName");
            System.out.println("3. findPublisherByID");
            System.out.println("4. list publishers");
            System.out.println("0. Back");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    createPublisher();
                    run = false;
                    break;
                case "2":
                    findPublisher();
                    run = false;
                    break;
                case "3":
                    findPublisherByID();
                    run = false;
                    break;
                case "0":
                    run = false;
                    break;
                case "4":
                    listPublisher();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    private void findPublisherByID() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID");
        int id = sc.nextInt();
        Publisher p = PublisherDao.findByID(id);
        System.out.println(" ");
        System.out.println(p.getId());
        System.out.println(p.getName());
    }
    private void findPublisher() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Publisher Name");
        String name = sc.nextLine();
        Publisher p = PublisherDao.findByName(name);

        System.out.println(" ");
        System.out.println(p.getId());
        System.out.println(p.getName());

    }
    private void createPublisher() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Publisher Name");
        String name = sc.nextLine();
        PublisherDao.createPublisher(name);
    }
    private void listPublisher() throws SQLException {
        Scanner sc = new Scanner(System.in);
        List<Publisher> list = PublisherDao.listPublisher();
        System.out.println(" ");
        for (Publisher p : list) {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(" ");
        }
    }
}
