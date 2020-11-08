package com.example.project731;

public class ProfileCreation {
    private int ID;
    private String username;
    private String password;

    //constructors


    public ProfileCreation(int ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public ProfileCreation(){
    }
    //tostring

    @Override
    public String toString() {
        return "ProfileCreation{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    //getters and setters


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
