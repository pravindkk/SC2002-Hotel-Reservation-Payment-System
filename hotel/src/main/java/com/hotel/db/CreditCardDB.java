package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.CreditCard;
import com.hotel.system.enums.CreditCardType;

public class CreditCardDB extends DB {
    private File database = new File("hotel/creditCard.csv");
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

    public String getPath() {
        return this.path;
    }

    @Override
    public ArrayList read(String fileName) throws IOException{
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();

        for (String [] row : listing ){
            String guestID = row[0];
            String name = row[1];
            Integer cardno = Integer.valueOf(row[2]);
            String expiry = row[3];
            Integer cvv = Integer.valueOf(row[4]);
            CreditCardType cardType = CreditCardType.valueOf(row[5]);

            allData.add(new CreditCard(guestID,name,cardno,expiry,cvv,cardType));
        }

        return allData;
    }



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

    

