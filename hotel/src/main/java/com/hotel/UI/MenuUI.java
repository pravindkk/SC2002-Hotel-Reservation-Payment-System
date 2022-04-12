package com.hotel.UI;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.plaf.metal.MetalBorders.MenuItemBorder;

import com.hotel.controller.MenuController;
import com.hotel.system.Item;
import com.hotel.system.enums.FoodType;
import org.apache.commons.lang3.RegExUtils;

/**
 * Represents the class of MenuUI, which prints the UI for Menu-related operations
 * @author Quek Kar Min
 * @version 1.0
 * @since 1.0
 */

public class MenuUI {
    MenuController menu = new MenuController();
    static Scanner sc = new Scanner(System.in);

    

    /** 
     * This method prints the UI to create a new Item by calling the relevant methods
    */
    public static void createNewItem() {
        String itemName=getItemName();
        double price=getItemPrice();
        String itemDesc = getItemDesc();
        FoodType foodType = getItemFoodType();
        
        

        Integer itemId = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        try {
            MenuController.createItem(new Item(itemId, itemName, itemDesc, price, foodType));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    /** 
     * This method prints the commandline UI foor updating a menu item
     * @throws IOException
     */
    public static void updateMenuItem() throws IOException {
        Integer itemdId=null;
        do {
            System.out.println("Enter the id of the item:  ");
            try {
                itemdId = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong id input!");
            }
            try {
                if (MenuController.getItem(itemdId) != null) break;
                else System.out.println("Item doesnt exist");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (true);
        Item newItem = MenuController.getItem(itemdId);
        int choice=0;
        System.out.print("(1) Item Name\n"+
                        "(2) Item Description\n"+
                        "(3) Item Price\n"+
                        "(4) Item FoodType\n"+
                        "What do you want to change? :");
        do {
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }
        } while (choice<1 || choice >4);

        switch (choice) {
            case 1:
                newItem.setName(getItemName());
                break;
            case 2:
                newItem.setDescription(getItemDesc());
                break;
            case 3:
                newItem.setPrice(getItemPrice());
                break;
            case 4:
                newItem.setType(getItemFoodType());
                break;
        
            default:
                break;
        }

        MenuController.updateItem(newItem);
        
        
    }

    
    /** 
     * This method prints the UI to delete an item from the menu
     * @throws IOException
     */
    public static void deleteMenuItem() throws IOException {
        Integer itemdId=null;
        do {
            System.out.println("Enter the id of the item:  ");
            try {
                itemdId = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong id input!");
            }
            try {
                if (MenuController.getItem(itemdId) != null) break;
                else {
                    System.out.println("Item doesnt exist");
                    return;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (true);
        // Item newItem = MenuController.getItem(itemdId);

        MenuController.deleteItem(itemdId);
    }



    
    /** 
     * This method prints the UI to get the name of an item
     * @return String
     */
    public static String getItemName() {
        String itemName=null;
        do {
            System.out.println("Enter the name of the item : ");
            try {
                itemName = sc.nextLine();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong name input!");
            }
        } while (itemName==null);
        return itemName;
    }


    
    /** 
     * This method prints the UI to get the description of an item
     * After the description is obtained, it will be returned as a String
     * @return String
     */
    public static String getItemDesc(){
        String itemDesc=null;
        do {
            System.out.println("Enter the description of the item :  ");
            try {
                itemDesc = sc.nextLine();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong desc input!");
            }
        } while (itemDesc == null);
        return itemDesc;
    }

    
    /** 
     * This method prints the UI to get the price of an item
     * After the price is obtained, it will be returned as a double
     * @return double
     */
    public static double getItemPrice() {
        String price=null;
        String priceFormat = "(\\d+\\.\\d{1,2})" ;
        do {
            System.out.println("Enter the price of the item (e.g 26.50):  ");
            price = sc.next();
 
        } while (price.equals("")|| !price.matches(priceFormat));
        sc.nextLine();
        return Double.valueOf(price);
    }

    
    /** 
     * This method prints the UI to get the FoodType of an item
     * After the FoodType is obtained, it will be returned as a FoodType (STARTER, MAIN_COURSE MAIN_COURSE, DRINKS)
     * @return FoodType
     */
    public static FoodType getItemFoodType() {
        FoodType foodType=null;
        do {
            System.out.println("(1) Starter\n"+
                                "(2) Main Course\n"+
                                "(3) Drinks");
            System.out.println("Enter the FoodType of the item:  ");
            int choice=0;
            try {
                    choice = sc.nextInt();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong input!");
            }
            if (choice >0 && choice < 4) {
                switch (choice) {
                    case 1:
                        foodType = FoodType.STARTER;
                        break;
                    case 2:
                        foodType = FoodType.MAIN_COURSE;
                        break;
                    case 3:
                        foodType = FoodType.DRINKS;
                        break;
                
                    default:
                        break;
                }
            }
            else System.out.println("wrong choice");


        } while (foodType == null);

        return foodType;
    }


}
