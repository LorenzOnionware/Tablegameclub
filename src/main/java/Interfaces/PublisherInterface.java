package Interfaces;

import Dao.PublisherDao;
import Models.Publisher;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PublisherInterface {
    private final PublisherDao publisherDao;

    public PublisherInterface() {
        publisherDao = new PublisherDao();
        publisherManagement();
    }
    private void publisherManagement(){
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

    private void findPublisherByID(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID");
        int id = sc.nextInt();
        Publisher p = publisherDao.findByID(id);
        System.out.println(" ");
        System.out.println(p.getId());
        System.out.println(p.getName());
    }
    private void findPublisher(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Publisher Name");
        String name = sc.nextLine();
        Publisher p = publisherDao.findByName(name);

        System.out.println(" ");
        System.out.println(p.getId());
        System.out.println(p.getName());

    }
    private void createPublisher(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Publisher Name");
        String name = sc.nextLine();
        publisherDao.createPublisher(name);
    }
    private void listPublisher(){
        Scanner sc = new Scanner(System.in);
        List<Publisher> list = publisherDao.listPublisher();
        System.out.println(" ");
        for (Publisher p : list) {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(" ");
        }
    }
}
