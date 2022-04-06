package com.hotel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.db.MenuDB;
import com.hotel.system.Item;
import com.hotel.system.enums.FoodType;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Represents the controller function of Menu
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */

public class MenuController {
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static MenuDB allMenus = new MenuDB();


    
    /** 
     * @return ArrayList returns an ArrayList of all the items stored in the database
     * @throws IOException IOException Due to communication with the DataBase IOexception is required
     */
    public static ArrayList<Item> getAllItems() throws IOException {
        ArrayList<Item> allData = allMenus.read(allMenus.getPath());
        return allData;
    }

    
    /** 
     * Prints out all the guest details in the database
     * @throws IOException IOException due to communication with the DataBase IOexception is required
     */
    public static void printAllItems() throws IOException {
        ArrayList<Item> items = getAllItems();
        ArrayList<Item> starter = new ArrayList<Item>();
        ArrayList<Item> main = new ArrayList<Item>();
        ArrayList<Item> drink = new ArrayList<Item>();
        for (int i=0; i<items.size(); i++) {
            Item curr = (Item) items.get(i);
            if (curr.getType().equals(FoodType.STARTER)) {
                starter.add(curr);
            }
            else if (curr.getType().equals(FoodType.MAIN_COURSE)) {
                main.add(curr);
            }
            else if (curr.getType().equals(FoodType.DRINKS)){
                drink.add(curr);
            }
        }
        System.out.println("ID   Name                          Description                          Price(S$)");
        if (starter.size()!=0) {
            System.out.println("====================================Starter=======================================");
            for (int i=0; i<starter.size(); i++) {
                Item curr = (Item) starter.get(i);
                System.out.println(curr.toString());

            }
        }

        if (main.size()!=0) {
            System.out.println("====================================Main Courses====================================");
            for (int i=0; i<main.size(); i++) {
                Item curr = (Item) main.get(i);
                System.out.println(curr.toString());

            }
        }
        if (drink.size()!=0) {
            System.out.println("====================================Drink=========================================");
            for (int i=0; i<drink.size(); i++) {
                Item curr = (Item) drink.get(i);
                System.out.println(curr.toString());

            }
        }
    
        
    }

    
    /** 
     * @param toWrite Contains an ArrayList of all the Items that is going to be stored in the database
     */
    public static void saveAllItems(ArrayList toWrite){
        try {
            allMenus.save(allMenus.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Item not added!");
            System.out.println(e);
        }
    }



    
    /**  
     * @param item An object of the class Item is passed so that this object could be written to the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void createItem(Item item) throws IOException {
        ArrayList<Item> allData = getAllItems();
        allData.add(item);
        saveAllItems(allData);
    }

    
    /** 
     * @param itemId String input of itemID is entered so that the corresponding Item object is returned
     * @return Item The corresponding Item object is returned
     * @throws IOException due to communication with the DataBase IOexception is required
     */
    public static Item getItem(Integer itemId) throws IOException {
        ArrayList<Item> allData = getAllItems();

        for (int i=0; i<allData.size(); i++) {
            // if ()
            Item item = (Item) allData.get(i);
            if (itemId.equals(item.getItemId())) {
                return item;
            }
        }
        return null;
    }

    
    /** 
     * @param itemId tring input of itemID is entered so that the corresponding Item object could be deleted
     * @throws IOException due to communication with the DataBase IOexception is required
     */
    public static void deleteItem(Integer itemId) throws IOException {
        ArrayList<Item> allData = getAllItems();

        for (int i=0; i<allData.size(); i++) {
            // if ()
            Item item = (Item) allData.get(i);
            if (itemId.equals(item.getItemId())) {
                // return item;

                // ArrayList.remove(allData, i);
                allData.remove(i);
                saveAllItems(allData);
                return;
            }
        }
        System.out.println("cannot delete item");
    }

    
    /** 
     * @param newItem String input of GuestID is entered so that the corresponding Item object is updated
     * @throws IOException due to communication with the DataBase IOexception is required
     */
    public static void updateItem(Item newItem) throws IOException {
        ArrayList<Item> allData = getAllItems();

        for (int i=0; i<allData.size(); i++) {
            // if ()
            Item item = (Item) allData.get(i);

            
            if (newItem.getItemId() == item.getItemId()) {
                allData.set(i, newItem);
                saveAllItems(allData);
                return;
            }
        }
        System.out.println("cannot delete item");
    }
}
