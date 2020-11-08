package com.example.project731;
/*

NOT IN USE YET

 */
public class UserProfile {
    private ProfileCreation newUser;
    private String name;
    private String shoe;

    //constructors

    public UserProfile(ProfileCreation newUser, String name, String shoe){
        this.newUser = newUser;
        this.name = name;
        this.shoe = shoe;
    }
    public UserProfile(){
    }
    //tostring


    @Override
    public String toString() {
        return "UserProfile{" +
                "newUser=" + newUser +
                ", name='" + name + '\'' +
                ", shoe='" + shoe + '\'' +
                '}';
    }


    //getters and setters

    public ProfileCreation getNewUser() {
        return newUser;
    }

    public void setNewUser(ProfileCreation newUser) {
        this.newUser = newUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShoe() {
        return shoe;
    }

    public void setShoe(String shoe) {
        this.shoe = shoe;
    }
}
