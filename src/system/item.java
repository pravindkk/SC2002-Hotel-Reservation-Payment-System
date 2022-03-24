package system;

public enum type{
    STARTER,MAIN_COURSE,DESERT,DRINKS
}

public class item {
    private int itemid;
    private String name;
    private String descripiton;
    private double price;
    public type type;

    public item(String name, String descripiton,double price,type type){
        this.name = name;
        this.descripiton = descripiton;
        this.price = price;
        this.type = type;
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

    public type getType(){
        return this.type;
    }
    public void setType(type Type){
        this.type=Type;
    }





}
