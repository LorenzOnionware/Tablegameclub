package Models;

import java.util.Date;

import java.util.Date;

public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date joinDate;
    private boolean active;

    public Member(Integer id, String firstName, String lastName, String email, java.sql.Date joinDate, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.active = active;
    }
    public Member(String firstName, String lastName, String email, java.sql.Date joinDate,boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joinDate = joinDate;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getJoinDate() {
        return joinDate;
    }
    public boolean isActive() {
        return active;
    }

    // ---- Setters ----
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
