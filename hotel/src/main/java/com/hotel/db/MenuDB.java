package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.Item;
import com.hotel.system.enums.FoodType;

public class MenuDB extends DB {
    private File database = new File("hotel/menu.csv");
    private String path;

    public MenuDB(){
        super();
        try {
            database.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.path = database.getAbsolutePath();

    }

    public String getPath() {
        return this.path;
    }

    @Override
    public ArrayList read(String fileName) throws IOException{
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();

        for (String [] row : listing ){
            int itemId = Integer.valueOf(row[0]);
            String name = row[1];
            String description = row[2];
            double price = Double.valueOf(row[3]);
            FoodType foodType = FoodType.valueOf(row[4]);
            allData.add(new Item(itemId, name, description, price, foodType));
        }

        return allData;
    }

    @Override
    public void save(String fileName, List al) throws IOException {
        // TODO Auto-generated method stub
        List<String[]> toWrite = new ArrayList<String[]>();
        

        for (int i=0; i<al.size(); i++) {
            Item item = (Item) al.get(i);

            toWrite.add(new String[] { 
                String.valueOf(item.getItemId()),
                item.getName(),
                item.getDescription(),
                String.valueOf(item.getPrice()),
                String.valueOf(item.getType())
            });
  
        }
        super.writeAllData(fileName, toWrite);


        
    }


    
}
