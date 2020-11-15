package com.example.project731;

public class ShoeProfileForLists {
    private String shoeName;
    //to be replaced with a picture later
    private int shoePic;


    //constructors

    public ShoeProfileForLists(String shoeName, int shoePic) {
        this.shoeName = shoeName;
        this.shoePic = shoePic;

    }
    public ShoeProfileForLists(String s){
        this.shoeName = shoeName;
        this.shoePic = shoePic;
    }

    public ShoeProfileForLists(){
    }
    //to string

    @Override
    public String toString() {
        return "ShoeProfileForLists{" +
                "shoeName='" + shoeName + '\'' +
                ", shoePic=" + shoePic +
                '}';
    }


    //setters and getters


    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public int getShoePic() {
        return shoePic;
    }

    public void setShoePic(int shoePic) {
        this.shoePic = shoePic;
    }


}
