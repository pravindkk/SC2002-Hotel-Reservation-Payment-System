package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.Guest;
import com.hotel.system.enums.Gender;

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

    // public static void main(String[] args){
    //     GuestDB test = new GuestDB();
    // }

    public String getPath() {
        return this.path;
    }

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
			//int phoneNo = Integer.parseInt(star.nextToken().trim());
			String address = row[6];
			String creditCardNumber = row[7];

            Guest guest = new Guest(guestId, 
                                   name, 
                                   Gender.valueOf(gender), 
                                   nationality, 
                                   country, 
                                   Integer.valueOf(phoneNumber), 
                                   creditCardNumber
                                   );
            allData.add(guest);

        }

        return allData;
        // return null;
    }

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
