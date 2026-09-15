package Dao;

import Models.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao {
    private Connection con;
    public PublisherDao() {
        try {
            con = (org.mariadb.jdbc.Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/boardgame_club", "root", "secret");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createPublisher(String name) {
        try {
            String query = "INSERT INTO publisher (name) VALUES (?)";
            con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Publisher findByID(int id) {
        try {
            String query = "SELECT * FROM publisher WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Publisher(id, rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Publisher> listPublisher() {
        List<Publisher> publishers = new ArrayList<>();
        try {
            String query = "SELECT * FROM publisher";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                publishers.add(new Publisher(rs.getInt("ID"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return publishers;
    }
    public Publisher findByName(String name) {
        try {
            String query = "SELECT * FROM publisher WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Publisher(rs.getInt("ID"), name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}