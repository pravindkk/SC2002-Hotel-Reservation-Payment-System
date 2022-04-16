package com.hotel.system;


import com.hotel.system.enums.FoodType;

/**
 * Represents the class of Item, which represents an item on the menu for Room Service
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class Item{
    
    private Integer itemid;
    private String name;
    private String description;
    private double price;
    public FoodType foodType;

    /**
     * Constructor for Item object
     * @param itemid        Unique identifier for each Item 
     * @param name          Name of dish
     * @param description   Description of dish
     * @param price         Price of dish
     * @param foodType      FoodType of Item (STARTER, MAIN_COURSE, DRINKS)
     */
    public Item(Integer itemid, String name, String description,double price,FoodType foodType){
        this.itemid = itemid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.foodType = foodType;
    }

    
    /** 
     * Returns ItemID as Integer
     * @return int
     */
    public int getItemId(){
        return this.itemid;
    }
    
    /** 
     * Takes Integer id and sets it as the new itemid
     * @param id uniquely identifies the item
     */
    public void setItemId(int id){
        this.itemid=id;
    }

    
    /** 
     * Returns name as String
     * @return String
     */
    public String getName(){
        return this.name;
    }
    
    /** 
     * Takes String name and sets its as the new name
     * @param Name is the name of the item
     */
    public void setName(String Name){
        this.name = Name;
    }

    
    /** 
     * Returns description as String
     * @return String
     */
    public String getDescription(){
        return this.description;
    }
    
    /** 
     * Takes String description and sets it as the new description
     * @param description describes the item 
     */
    public void setDescription(String description){
        this.description=description;

    }

    
    /** 
     * Returns price as double
     * @return double
     */
    public double getPrice(){
        return this.price;
    }
    
    /** 
     * Takes double price and sets it as the new price
     * @param price is how much the item costs
     */
    public void setPrice(double price){
        this.price=price;
    }

    
    /** 
     * Returns foodtype as enum FoodType
     * @return FoodType
     */
    public FoodType getType(){
        return this.foodType;
    }
    
    /** 
     * Takes FoodType Type and sets it as new foodType
     * @param Type describes what kind of food it is
     */
    public void setType(FoodType Type){
        this.foodType=Type;
    }

    
    /** 
     * Used to format printing
     * @return String
     */
    public String toString() {

        return (String.format("%-5d%-25s%-45s%.2f", itemid, name, description, price));
    }
}
