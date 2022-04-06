package com.hotel.system;

import java.util.ArrayList;

/**
 * Represents the class of Menu, which represents the menu for Room Service
 * The menu is a list of Items
 * @author Quek Kar Min
 * @version 1.0
 * @since 1.0
 */

public class Menu {
    // create a ArrayList of menu items
    private  ArrayList<Item> Items = new ArrayList<Item>();

    /**
     * Constructor for Menu
     * @param items is an arraylist that holds Items to represent the menu
     */
    public Menu(ArrayList <Item> items){
        this.Items = items;

    }

   
    /** 
     * Returns the whole array of items
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getMenu(){
        return this.Items;
    }

}


