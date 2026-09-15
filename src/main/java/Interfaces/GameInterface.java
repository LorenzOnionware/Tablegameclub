package Interfaces;

import Dao.GameDao;
import Models.Game;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GameInterface {
    private final GameDao gameDao;

    public GameInterface() {
        this.gameDao = new GameDao();
        gameManagement();
    }

    private void gameManagement(){
        Scanner sc = new Scanner(System.in);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║         GAME MANAGEMENT          ║");
        System.out.println("╚══════════════════════════════════╝");
        boolean run = true;
        while (run){
            System.out.println(" ");
            System.out.println("1. Find game");
            System.out.println("2. Create game");
            System.out.println("0. Back");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    findGame();
                    run = false;
                    break;
                case "2":
                    createGame();
                    run = false;
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }


    }
    private void createGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter game name");
        String name = sc.nextLine();
        System.out.println("Enter game release_year");
        int releaseYear = sc.nextInt();
        System.out.println("Enter minPlayerCount");
        int minPlayerCount = sc.nextInt();
        System.out.println("Enter maxPlayerCount");
        int maxPlayerCount = sc.nextInt();
        System.out.println("Enter AveragePlaytime");
        int averagePlaytime = sc.nextInt();
        System.out.println("Enter PublisherID");
        int publisherID = sc.nextInt();
        Game game = new Game(
                name,
                publisherID,
                releaseYear,
                maxPlayerCount,
                minPlayerCount,
                averagePlaytime
        );
        gameDao.createGame(game);
    }
    private void findGame(){
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while(run) {
            System.out.println(" ");
            System.out.println("1. Search by name");
            System.out.println("2. Search by ID");
            System.out.println("3. search by publisherID");
            System.out.println("4. show all");
            System.out.println("0. Back");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("enter title");
                    run=false;
                    printGame(gameDao.searchByName(sc.nextLine()));
                    break;
                case "2":
                    System.out.println("enter ID");
                    Game game = gameDao.searchById(sc.nextInt());
                    printGame(game);
                    run = false;
                    break;
                case "3":
                    System.out.println("enter publisherID");
                    printGame(gameDao.searchByPublisher(sc.nextInt()));
                    run = false;
                    break;
                case "4":
                    printGame(gameDao.getAllGames());
                    run = false;
                    break;
                case "0":
                    run=false;
                    break;
                default:
                    System.out.println("Invalid input");

            }
        }
    }
    private void printGame(Game game){
        if(game==null){
            System.out.println("Invalid input");
            return;
        }
        System.out.println(" ");
        System.out.println("ID: " + game.getId());
        System.out.println("title: " + game.getTitle());
        System.out.println("release_year: " + game.getReleaseYear());
        System.out.println("maxplayers: " + game.getMaxPlayers());
        System.out.println("minplayers: " + game.getMinPlayers());
        System.out.println("average_playing_time: " + game.getAveragePlayingTime());
        System.out.println("publisherID: " + game.getPublisherID());
    }
    private void printGame(List<Game> games){
        for(Game g : games){
            printGame(g);
        }
    }
}
