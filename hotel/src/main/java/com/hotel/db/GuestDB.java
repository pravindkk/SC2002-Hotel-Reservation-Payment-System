package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.Guest;
import com.hotel.system.enums.Gender;

/**
 * Represents the derived class of DB , which is used to read and write all data to the text file that contains guest.
 * @author Pravind 
 * @version 1.0
 * @since 1.0
 */

public class GuestDB extends DB {
    private File database = new File("hotel/guest.csv");
    private String path;

    public GuestDB(){
        super();
        try {
            database.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.path = database.getAbsolutePath();

    }

    
    /** 
     * @return The fucntion getPath returns the path of the file as a String
     */
 

    public String getPath() {
        return this.path;
    }

    /** 
     * @param fileName The name of the text file is passed in as a String input
     * @return ArrayList of the data in the text file is returned
     * @throws IOException Due to communication with the DataBase IOexception is required
     */

    @Override
    public ArrayList read(String fileName) throws IOException {
        // TODO Auto-generated method stub
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();


        for (String[] row : listing) {


            String guestId = row[0];
			String name = row[1];
			String gender = row[2];
			String nationality = row[3];
			String country = row[4];
			String phoneNumber = row[5];
			String creditCardNumber = row[6];

            Guest room = new Guest(guestId, 
                                   name, 
                                   Gender.valueOf(gender), 
                                   nationality, 
                                   country, 
                                   Integer.valueOf(phoneNumber), 
                                   creditCardNumber
                                   );
            allData.add(room);

        }

        return allData;
        // return null;
    }

    
    /** 
     * @param fileName The name of the text file that the data is going to be written to is passed in as a String input
     * @param al ArrayList of the data that is going to be written to is passed as a input
     * @throws IOException Due to communication with the DataBase IOexception is required
     */

    @Override
    public void save(String fileName, List al) throws IOException {
        // TODO Auto-generated method stub
        List<String[]> toWrite = new ArrayList<String[]>();
        

        for (int i=0; i<al.size(); i++) {
            Guest g = (Guest) al.get(i);

            toWrite.add(new String[] { g.getGuestId(),
                                       g.getName(),
                                       String.valueOf(g.getGender()),
                                       g.getNationality(),
                                       g.getCountry(),
                                       String.valueOf(g.getPhoneNumber()),
                                       g.getCreditCardNumber() });
            
        }

        super.writeAllData(fileName, toWrite);
        
    }
    
}
