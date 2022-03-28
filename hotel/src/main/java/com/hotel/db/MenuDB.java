package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.Item;
import com.hotel.system.enums.FoodType;

public class MenuDB implements DB {
    private File database = new File("hotel/menu.csv");
    private String path;

    public MenuDB(){
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
        List<String[]> listing = ReadInFile.readAllData(fileName);
        ArrayList allData = new ArrayList();

        for (String [] row : listing ){
            String itemId = row[0];
            String name = row[1];
            String description = row[2];
            double price = Double.valueOf(row[3]);
            FoodType foodType = FoodType.valueOf(row[4]);
        }

        return null;
    }

    @Override
    public void save(String fileName, List al) throws IOException {
        // TODO Auto-generated method stub
        
    }


    
}
