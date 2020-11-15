package com.example.project731;

public class ShoeProfileForLists {
    private String shoeName;
    //to be replaced with a picture later
    private int shoePic;
    private String shoeImage;


    //constructors

    public ShoeProfileForLists(String shoeName, int shoePic, String shoeImage) {
        this.shoeName = shoeName;
        this.shoePic = shoePic;
        this.shoeImage = shoeImage;

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
        return  shoeName +
                "\n" + shoePic;
    }


    //setters and getters


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

    public int getShoePic() {
        return shoePic;
    }

    public void setShoePic(int shoePic) {
        this.shoePic = shoePic;
    }


}
