package system;
import system.enums.*;


public class Item {
    
    private int itemid;
    private String name;
    private String descripiton;
    private double price;
    public FoodType foodType;

    public Item(String name, String descripiton,double price,FoodType foodType){
        this.name = name;
        this.descripiton = descripiton;
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
        return this.descripiton;
    }
    public void setDescription(String description){
        this.descripiton=description;

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
