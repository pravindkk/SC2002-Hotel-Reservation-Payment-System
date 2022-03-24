package system;
import java.util.ArrayList;
public class menu {

    // create a ArrayList of menu items
   private  ArrayList<Item> Items = new ArrayList<Item>();

   public menu(ArrayList <Item> items){
       this.Items = items;

   }

   public ArrayList<Item> getMenu(){
       return this.Items;
   }

    
}


