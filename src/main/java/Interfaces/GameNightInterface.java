package Interfaces;

import Dao.GameNightDao;
import Models.Game;
import Models.Member;
import Models.gameNight;

import java.sql.SQLException;
import java.util.Scanner;

public class GameNightInterface {
    public GameNightInterface() {
        try {
            gameNightManagement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void gameNightManagement() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║       GAMENIGHT MANAGEMENT       ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean run = true;
        while (run) {
            System.out.println(" ");
            System.out.println("1. Create GameNight");
            System.out.println("2. Find GameNights");
            System.out.println("3. List All GameNights");
            System.out.println("0. Back");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    createGameNight();
                    run = false;
                    break;
                case "2":
                    searchGameNight();
                    run = false;
                    break;
                case "3":
                    listGameNights();
                    run=false;
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            };
        }
    }
    private void createGameNight() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Create GameNight");
        System.out.println("date of the gamenight (yyyy-mm-dd):");
        String date = sc.nextLine();
        System.out.println("Start time (HH:MM):");
        String starttime = sc.nextLine();
        System.out.println("members Sepereate meberID like 1,2,3,4");
        String Member = sc.nextLine();
        System.out.println("games Seperate gameID like 1,2,3,4");
        String Game = sc.nextLine();
        System.out.println("Location:");
        String location = sc.nextLine();
        GameNightDao.createGameNight(date, starttime, Member, Game, location);
    }
    private void searchGameNight() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter GameNight ID");
        PrintGameNight(gameNightDao.findGameNight(sc.nextInt()));
    }
    private void PrintGameNight(gameNight gameNight){
        System.out.println("Game Night ID: " + gameNight.getgID());
        System.out.println("Game Night date: " + gameNight.getDate());
        System.out.println("Game Night time: " + gameNight.getStartTime());
        System.out.println("Game Night location: " + gameNight.getLocation());
        System.out.println(" ");
        System.out.println("Participants");
        System.out.println("---------------------------------------------------------------");
        for(Member member : gameNight.getNightMembers()){
            System.out.println(member.getId()+ " " + member.getFirstName()+ " " + member.getLastName());
        }
        System.out.println(" ");
        System.out.println("Played games");
        System.out.println("---------------------------------------------------------------");
        for(Game game : gameNight.getNightGames()){
            System.out.println(game.getId() + " " + game.getTitle());
        }
        System.out.println(" ");
        System.out.println("================================================================");
    }
    private void listGameNights() throws SQLException {

        for(gameNight g : GameNightDao.listGameNights()){
            PrintGameNight(g);
        }

    }
}
