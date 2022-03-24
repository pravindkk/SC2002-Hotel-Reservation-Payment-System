package system;
import java.util.ArrayList;
public class Menu {

    // create a ArrayList of menu items
   private  ArrayList<Item> Items = new ArrayList<Item>();

   public Menu(ArrayList <Item> items){
       this.Items = items;

   }

   public ArrayList<Item> getMenu(){
       return this.Items;
   }

    
}


