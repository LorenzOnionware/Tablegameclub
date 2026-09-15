package Dao;

import Models.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    private static Connection con;
    public GameDao() {
        try {
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/boardgame_club", "root", "secret");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Game searchByName(String Name) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT * FROM board_game WHERE title = ?");
            ps.setString(1, Name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Game(rs.getInt("ID"), Name, rs.getInt("publisher_ID"), rs.getInt("release_year"), rs.getInt("max_player"), rs.getInt("min_player"), rs.getInt("average_play_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Game searchById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM board_game WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Game(id, rs.getString("title"), rs.getInt("publisher_ID"), rs.getInt("release_year"), rs.getInt("max_player"), rs.getInt("min_player"), rs.getInt("average_play_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Game> searchByPublisher(int id){
        List<Game> games = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM board_game WHERE publisher_ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game g = new Game(
                        rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getInt("publisher_ID"),
                        rs.getInt("release_year"),
                        rs.getInt("max_player"),
                        rs.getInt("min_player"),
                        rs.getInt("average_play_time")
                );
                games.add(g);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return games;
    }
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM board_game");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Game g = new Game(
                        rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getInt("publisher_ID"),
                        rs.getInt("release_year"),
                        rs.getInt("max_player"),
                        rs.getInt("min_player"),
                        rs.getInt("average_play_time")
                );
                games.add(g);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return games;
    }
    public void createGame(Game game) {
        try {
            String sql = "INSERT INTO board_game (title, release_year, min_player, max_player, average_playing_time, publisher_ID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getTitle());
            ps.setInt(2, game.getReleaseYear());
            ps.setInt(3, game.getMinPlayers());
            ps.setInt(4, game.getMaxPlayers());
            ps.setInt(5, game.getAveragePlayingTime());
            ps.setInt(6, game.getPublisherID());
            ps.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
