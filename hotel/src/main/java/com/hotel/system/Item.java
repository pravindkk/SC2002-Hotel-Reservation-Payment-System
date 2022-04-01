package com.hotel.system;


import com.hotel.system.enums.FoodType;



public class Item{
    
    private Integer itemid;
    private String name;
    private String description;
    private double price;
    public FoodType foodType;

    public Item(Integer itemid, String name, String description,double price,FoodType foodType){
        this.itemid = itemid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.foodType = foodType;
    }

    public int getItemId(){
        return this.itemid;
    }
    public void setItemId(int id){
        this.itemid=id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String Name){
        this.name = Name;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description=description;

    }

    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price=price;
    }

    public FoodType getType(){
        return this.foodType;
    }
    public void setType(FoodType Type){
        this.foodType=Type;
    }





}
