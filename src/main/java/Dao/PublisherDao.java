package Dao;

import Models.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao {
    private static Connection con;
    public PublisherDao() throws SQLException {
        con = (org.mariadb.jdbc.Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/boardgame_club", "root", "secret");
    }
    public static void createPublisher(String name) throws SQLException {
        String query = "INSERT INTO publishers (name) VALUES (?)";
        con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public static Publisher findByID(int id) throws SQLException {
        String query = "SELECT * FROM publishers WHERE ID = ?";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Publisher(id, rs.getString("name"));
        }
        return null;
    }
    public static List<Publisher> listPublisher() throws SQLException {
        List<Publisher> publishers = new ArrayList<>();
        String query = "SELECT * FROM publishers";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                publishers.add(new Publisher(rs.getInt("ID"), rs.getString("name")));
        }
        return publishers;
    }

    public static Publisher findByName(String name) throws SQLException {
        String query = "SELECT * FROM publishers WHERE name = ?";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Publisher(rs.getInt("ID"), name);
        }
        return null;
    }
}
