package Dao;

import Models.Member;
import org.mariadb.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private Connection con;

    public MemberDao() {
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/boardgame_club", "root", "secret");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Member> findAll() {
        List<Member> output = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM member");
            rs = ps.executeQuery();
            while (rs.next()) {
                Member m = new Member(
                        rs.getInt("ID"),
                        rs.getString("first_name"), // Spaltennamen wie in der DB!
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("join_date"),
                        rs.getBoolean("is_active")
                );
                output.add(m);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return output;
    }

    public Member findById(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM member WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Member m = new Member(
                        rs.getInt("ID"),
                        rs.getString("first_name"), // Spaltennamen wie in der DB!
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("join_date"),
                        rs.getBoolean("is_active")
                );
                return m;
            }
            return null;
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Member> findByName(String Name) {
        List<Member> output = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT * FROM member WHERE first_name = ? OR last_name = ?");
            ps.setString(1, Name);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Member m = new Member(
                        rs.getInt("ID"),
                        rs.getString("first_name"), // Spaltennamen wie in der DB!
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("join_date"),
                        rs.getBoolean("is_active")
                );
                output.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
    public void createMember(Member m) {
        String sql = "INSERT INTO member (first_name, last_name, email, join_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getFirstName());
            ps.setString(2, m.getLastName());
            ps.setString(3, m.getEmail());
            ps.setDate(4, new java.sql.Date(m.getJoinDate().getTime()));
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    m.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateFirstname(String firstname, int member_id){
        try {
            String sql = "UPDATE member SET first_name = ? WHERE ID = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, firstname);
                ps.setInt(2, member_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateLastname(String lastname, int member_id) {
        try {
            String sql = "UPDATE member SET last_name = ? WHERE ID = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, lastname);
                ps.setInt(2, member_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateStatus(boolean stat, int member_id) {
        try {
            String sql = "UPDATE member SET is_active = ? WHERE ID = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, stat ? "1" : "0");
                ps.setInt(2, member_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateEmail(String email, int member_id) {
        try {
            String sql = "UPDATE member SET email = ? WHERE ID = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setInt(2, member_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}