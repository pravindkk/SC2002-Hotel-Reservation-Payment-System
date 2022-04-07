package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.CreditCard;
import com.hotel.system.enums.*;

/**
 * Represents the derived class of DB , which is used to read and write all data to the text file that contains Creditcard.
 * @author Pravind 
 * @version 1.0
 * @since 1.0
 */

public class CreditCardDB extends DB {

    private File database = new File("hotel/creditCard.txt");
    private String path;

    public CreditCardDB(){
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
     * 
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
    public ArrayList read(String fileName) throws IOException{
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();

        for (String [] row : listing ){
            String guestID = row[0];
            String name = row[1];
            String cardno = row[2];
            String expiry = row[3];
            String cvv = row[4];
            CreditCardType cardType = CreditCardType.valueOf(row[5]);

            allData.add(new CreditCard(guestID,name,cardno,expiry,cvv,cardType));
        }

        return allData;
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
            CreditCard creditCard = (CreditCard) al.get(i);

            toWrite.add(new String[] { 
                creditCard.getGuestID(),
                creditCard.getName(),
                String.valueOf(creditCard.getCardNo()),
                creditCard.getExpiry(),
                String.valueOf(creditCard.getCVV()),
                String.valueOf(creditCard.getCardType())
            });
  
        }
        super.writeAllData(fileName, toWrite);

    }


    
}

    

