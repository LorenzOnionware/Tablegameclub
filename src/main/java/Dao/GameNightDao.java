package Dao;

import Models.Game;
import Models.Member;
import Models.gameNight;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GameNightDao {
    private Connection con;

    public GameNightDao() throws SQLException {
        con = (org.mariadb.jdbc.Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/boardgame_club", "root", "secret");
    }

    public gameNight findGameNight(int ID) throws SQLException {
        String sql = "select * from game_nights where ID = ?";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new gameNight(
                    rs.getInt("ID"),
                    rs.getDate("date"),
                    rs.getTime("starttime"),
                    rs.getString("location"),
                    getMembers(ID),
                    getGames(ID)
            );
        }
        return null;
    }
    public List<gameNight> listGameNights() throws SQLException {
        List<gameNight> gameNights = new ArrayList<>();
        String sql = "select * from game_nights";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);;
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            gameNights.add(new gameNight(
                    rs.getInt("ID"),
                    rs.getDate("date"),
                    rs.getTime("starttime"),
                    rs.getString("location"),
                    getMembers(rs.getInt("ID")),
                    getGames(rs.getInt("ID"))
            ));
        }
        return gameNights;
    }

    public void createGameNight(String Date, String starttime, String members, String games, String location) throws SQLException {
        String sql = "INSERT INTO game_nights (date, starttime, location) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, java.sql.Date.valueOf(LocalDate.parse(Date)));
        ps.setTime(2, java.sql.Time.valueOf(LocalTime.parse(starttime)));
        ps.setString(3, location);
        ps.executeUpdate();

        int id = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id=rs.getInt(1);
        }

        PreparedStatement ps1 = con.prepareStatement("INSERT INTO night_members (gamenight_id, member_id) VALUES (?, ?)");
        for(int i : parseIds(members)) {
            ps1.setInt(1, id);
            ps1.setInt(2, i);
            ps1.executeUpdate();
        }
        PreparedStatement ps2 = con.prepareStatement("INSERT INTO night_games (gamenight_id, game_id) VALUES (?, ?)");
        for(int i : parseIds(games)) {
            ps2.setInt(1, id);
            ps2.setInt(2, i);
            ps2.executeUpdate();
        }
    }

    private List<Member> getMembers(int ID) throws SQLException {
        List<Member> members = new ArrayList<>();
        List<Integer> mem = new ArrayList<Integer>();
        String sql = "select * from night_members where gamenight_id = ?";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            mem.add(rs.getInt("member_id"));
        }
        ps.close();
        String sql1 = "SELECT * FROM members WHERE member_id = ?";
        PreparedStatement ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        for (Integer i : mem) {
            ps1.setInt(1, i);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Member m = new Member(
                        rs1.getInt("member_id"),
                        rs1.getString("first_name"),
                        rs1.getString("last_name"),
                        rs1.getString("email"),
                        rs1.getDate("join_date"),
                        rs1.getBoolean("is_active")
                        );
                members.add(m);
            }
        }
        return members;
    }

    private List<Game> getGames(int ID) throws SQLException {
        List<Game> games = new ArrayList<>();
        List<Integer> gamesID = new ArrayList<>();
        String sql = "select * from night_games where gamenight_id = ?";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            gamesID.add(rs.getInt("game_id"));
        }
        String sql1 = "SELECT * FROM board_games WHERE game_id = ?";
        PreparedStatement ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        for (Integer i : gamesID) {
            ps1.setInt(1, i);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                games.add(new Game(
                        rs1.getInt("game_id"),
                        rs1.getString("title"),
                        rs1.getInt("publisher_ID"),
                        rs1.getInt("release_year"),
                        rs1.getInt("maxplayercount"),
                        rs1.getInt("minplayercount"),
                        rs1.getInt("average_playing_time")
                ));
            }
        }
        return games;
    }

    private List<Integer> parseIds(String input) {
        List<Integer> ids = new ArrayList<>();
        for (String s : input.split(",")) {
            s = s.trim();
            if (s.isEmpty()) {
                continue;
            }
            try {
                ids.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                System.out.println("'" + s + "' is not a valid number - skipping");
            }
        }
        return ids;
    }
}
