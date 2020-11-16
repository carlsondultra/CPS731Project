package com.example.project731;

public class ShoeProfileForLists {
    private String shoeName;
    //to be replaced with a picture later

    private String shoeImage;


    //constructors

    public ShoeProfileForLists(String shoeName, String shoeImage) {
        this.shoeName = shoeName;
        this.shoeImage = shoeImage;

    }

    public ShoeProfileForLists(){
    }

    public String getShoeImage() {
        return shoeImage;
    }

    public void setShoeImage(String shoeImage) {
        this.shoeImage = shoeImage;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }




}
