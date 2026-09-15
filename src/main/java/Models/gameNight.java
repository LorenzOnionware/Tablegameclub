package Models;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class gameNight {
    private int gID;
    private Date date;
    private Time startTime;
    private String location;
    private List<Member> nightMembers;
    private List<Game> nightGames;

    public gameNight(int gID, Date date, Time startTime, String location, List<Member> nightMembers, List<Game> nightGames) {
        this.gID = gID;
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.nightMembers = nightMembers;
        this.nightGames = nightGames;
    }

    public gameNight(Date date, Time startTime, String location, List<Member> nightMembers, List<Game> nightGames) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.nightMembers = nightMembers;
        this.nightGames = nightGames;
    }

    public int getgID() {
        return gID;
    }
    public void setgID(int gID) {
        this.gID = gID;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Time getStartTime() {
        return startTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public List<Member> getNightMembers() {
        return nightMembers;
    }
    public void setNightMembers(List<Member> nightMembers) {
        this.nightMembers = nightMembers;
    }
    public List<Game> getNightGames() {
        return nightGames;
    }
    public void setNightGames(List<Game> nightGames) {
        this.nightGames = nightGames;
    }
}
