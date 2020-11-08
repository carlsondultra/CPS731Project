package com.example.project731;

public class ProfileCreation {
    private String username;
    private String password;

    //constructors


    public ProfileCreation( String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ProfileCreation(){
    }
    //tostring

    @Override
    public String toString() {
        return "ProfileCreation{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    //getters and setters

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
