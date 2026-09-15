package Models;

public class Game {
    private int id;
    private String title;
    private int publisherID;
    private int releaseYear;
    private int maxPlayers;
    private int minPlayers;
    private int averagePlayingTime;



    public Game(int id, String title, int publisherID, int releaseYear, int maxPlayers, int minPlayers, int averagePlayingTime) {
        this.id = id;
        this.title = title;
        this.publisherID = publisherID;
        this.releaseYear = releaseYear;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.averagePlayingTime = averagePlayingTime;
    }
    public Game(String title, int publisherID, int releaseYear, int maxPlayers, int minPlayers, int averagePlayingTime) {
        this.title = title;
        this.publisherID = publisherID;
        this.releaseYear = releaseYear;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.averagePlayingTime = averagePlayingTime;
    }

    public int getAveragePlayingTime() {
        return averagePlayingTime;
    }

    public void setAveragePlayingTime(int averagePlayingTime) {
        this.averagePlayingTime = averagePlayingTime;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublisherID() {
        return publisherID;
    }

    // ---- Setter ----
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }
}
