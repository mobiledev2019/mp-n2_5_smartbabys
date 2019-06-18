package com.ptit.android.kidslearning.Topic;

public class Topic {
    private String ID;
    private String Name;
    private String Image;

    public Topic(String ID, String name, String image) {
        this.ID = ID;
        Image = image;
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}