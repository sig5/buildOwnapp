package com.example.myapplication;

public class Datamodel {
    String name;
    String Price;
    String image;
    String description;
    String authorname;

    public Datamodel(String name, String price, String image,String authorname,String description) {
        this.name = name;
        this.Price = price;
        this.image = image;
        this.authorname=authorname;
        this.description=description;
    }

    public String getName1() {
        return name;
    }

    public String getPrice() {
        return Price;
    }

    public String getImage() {
        return image;
    }
}
