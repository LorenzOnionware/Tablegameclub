package Dao;

import Models.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    private static Connection con;
    public GameDao() throws SQLException {
        con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/boardgame_club", "root", "secret");
    }

    public static Game searchByName(String Name) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM board_games WHERE title =?");
        ps.setString(1, Name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return new Game(rs.getInt("game_id"), Name,rs.getInt("publisher_id"), rs.getInt("release_year"), rs.getInt("maxplayercount"), rs.getInt("minplayercount"), rs.getInt("average_playing_time"));
        }
        return null;
    }
    public static Game searchById(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM board_games WHERE game_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Game(id, rs.getString("title"), rs.getInt("publisher_id"), rs.getInt("release_year"), rs.getInt("maxplayercount"), rs.getInt("minplayercount"), rs.getInt("average_playing_time"));
        }
        return null;
    }
    public static List<Game> searchByPublisher(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM board_games WHERE publisher_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<Game> games = new ArrayList<>();
        while (rs.next()) {
            Game g = new Game(
                    rs.getInt("game_id"),
                    rs.getString("title"),
                    rs.getInt("publisher_id"),
                    rs.getInt("release_year"),
                    rs.getInt("maxplayercount"),
                    rs.getInt("minplayercount"),
                    rs.getInt("average_playing_time")
            );
            games.add(g);
        }
        return games;
    }
    public static List<Game> getAllGames() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM board_games");
        ResultSet rs = ps.executeQuery();
        List<Game> games = new ArrayList<>();
        while (rs.next()) {
            Game g = new Game(
                    rs.getInt("game_id"),
                    rs.getString("title"),
                    rs.getInt("publisher_id"),
                    rs.getInt("release_year"),
                    rs.getInt("maxplayercount"),
                    rs.getInt("minplayercount"),
                    rs.getInt("average_playing_time")
            );
            games.add(g);
        }
        return games;
    }
    public static void createGame(Game game) throws SQLException {
        String sql = "INSERT INTO board_games (title, release_year, minplayercount, maxplayercount, average_playing_time, publisher_ID) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getTitle());
            ps.setInt(2, game.getReleaseYear());
            ps.setInt(3, game.getMinPlayers());
            ps.setInt(4, game.getMaxPlayers());
            ps.setInt(5, game.getAveragePlayingTime());
            ps.setInt(6, game.getPublisherID());
            ps.executeUpdate();
    }
}
