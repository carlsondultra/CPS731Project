package com.example.project731;
/*

NOT IN USE YET

 */
public class UserProfile {
    private String name;
    private int id;
    private String shoe;

    //constructors

    public UserProfile(int id, String name, String shoe){
        this.id = id;
        this.name = name;
        this.shoe = shoe;
    }
    public UserProfile(){
    }
    //tostring


    @Override
    public String toString() {
        return "UserProfile{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", shoe='" + shoe + '\'' +
                '}';
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShoe() {
        return shoe;
    }

    public void setShoe(String shoe) {
        this.shoe = shoe;
    }
}
