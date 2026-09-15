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

    public GameNightDao(){
        try {
            con = (org.mariadb.jdbc.Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/boardgame_club", "root", "secret");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public gameNight findGameNight(int ID) {
        try {
            String sql = "select * from game_night where ID = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new gameNight(
                        rs.getInt("ID"),
                        rs.getDate("date"),
                        rs.getTime("start_time"),
                        rs.getString("location"),
                        getMembers(ID),
                        getGames(ID)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<gameNight> listGameNights(){
        List<gameNight> gameNights = new ArrayList<>();
        try {
            String sql = "select * from game_night";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gameNights.add(new gameNight(
                        rs.getInt("ID"),
                        rs.getDate("date"),
                        rs.getTime("start_time"),
                        rs.getString("location"),
                        getMembers(rs.getInt("ID")),
                        getGames(rs.getInt("ID"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gameNights;
    }

    public void createGameNight(String Date, String starttime, String members, String games, String location) {
        try {
            String sql = "INSERT INTO game_night (date, start_time, location) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.parse(Date)));
            ps.setTime(2, java.sql.Time.valueOf(LocalTime.parse(starttime)));
            ps.setString(3, location);
            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            PreparedStatement ps1 = con.prepareStatement("INSERT INTO night_member (game_night_ID, member_ID) VALUES (?, ?)");
            for (int i : parseIds(members)) {
                ps1.setInt(1, id);
                ps1.setInt(2, i);
                ps1.executeUpdate();
            }
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO night_game (game_night_ID, game_ID) VALUES (?, ?)");
            for (int i : parseIds(games)) {
                ps2.setInt(1, id);
                ps2.setInt(2, i);
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Member> getMembers(int ID) {
        List<Member> members = new ArrayList<>();
        List<Integer> mem = new ArrayList<Integer>();
        try {
            String sql = "select * from night_member where game_night_ID = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mem.add(rs.getInt("member_ID"));
            }
            ps.close();
            String sql1 = "SELECT * FROM member WHERE ID = ?";
            PreparedStatement ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            for (Integer i : mem) {
                ps1.setInt(1, i);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    Member m = new Member(
                            rs1.getInt("ID"),
                            rs1.getString("first_name"),
                            rs1.getString("last_name"),
                            rs1.getString("email"),
                            rs1.getDate("join_date"),
                            rs1.getBoolean("is_active")
                    );
                    members.add(m);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }

    private List<Game> getGames(int ID){
        List<Game> games = new ArrayList<>();
        List<Integer> gamesID = new ArrayList<>();
        try {
            String sql = "select * from night_game where game_night_ID = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gamesID.add(rs.getInt("game_ID"));
            }
            String sql1 = "SELECT * FROM board_game WHERE ID = ?";
            PreparedStatement ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            for (Integer i : gamesID) {
                ps1.setInt(1, i);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    games.add(new Game(
                            rs1.getInt("ID"),
                            rs1.getString("title"),
                            rs1.getInt("publisher_ID"),
                            rs1.getInt("release_year"),
                            rs1.getInt("max_player"),
                            rs1.getInt("min_player"),
                            rs1.getInt("average_play_time")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
