package com.hotel.UI;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.plaf.metal.MetalBorders.MenuItemBorder;

import com.hotel.controller.MenuController;
import com.hotel.system.Item;
import com.hotel.system.enums.FoodType;

public class MenuUI {
    MenuController menu = new MenuController();
    static Scanner sc = new Scanner(System.in);

    public void displayOptions() throws IOException {
    	int choice;
    	do {
            MenuController.printAllItems();
            System.out.println("\n==================================================");
			System.out.println(" Menu item Management: ");
			System.out.println("==================================================");
			System.out.println("(1) Create Menu item\t(2) Update Menu item");
			System.out.println("(3) Remove Menu item\t(4) Back");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                	createNewItem();
                    break;
                case 2:
                	// updateIn();
                    updateMenuItem();
                    break;
                case 3:
                	// removeIn();
                    deleteMenuItem();
                    break;
                case 4:
                    return;
                default:
                	System.out.println("Please enter a valid option!");
					choice = 0;
            }
        } while (choice < 4);
    }
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

    public static void updateMenuItem() throws IOException {
        Integer itemdId=null;
        do {
            System.out.println("Enter the id of the item to add:  ");
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

    public static void deleteMenuItem() throws IOException {
        Integer itemdId=null;
        do {
            System.out.println("Enter the id of the item to add:  ");
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



    public static String getItemName() {
        String itemName=null;
        do {
            System.out.println("Enter the name of the item to add:  ");
            try {
                sc.nextLine();
                itemName = sc.nextLine();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong name input!");
            }
        } while (itemName==null);
        return itemName;
    }


    public static String getItemDesc(){
        String itemDesc=null;
        do {
            System.out.println("Enter the description of the item to add:  ");
            try {
                sc.nextLine();
                itemDesc = sc.nextLine();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong desc input!");
            }
        } while (itemDesc == null);
        return itemDesc;
    }

    public static double getItemPrice() {
        double price=0.0;
        do {
            System.out.println("Enter the price of the item to add (e.g 26.5):  ");
            try {
                price = sc.nextDouble();
                if (price != 0.0) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong price input!");
            }
        } while (true);
        return price;
    }

    public static FoodType getItemFoodType() {
        FoodType foodType=null;
        do {
            System.out.println("(1) Starter\n"+
                                "(2) Main Course\n"+
                                "(3) Drinks");
            System.out.println("Enter the FoodType of the item to add:  ");
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
