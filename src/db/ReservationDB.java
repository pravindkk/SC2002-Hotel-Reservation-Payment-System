package db;

import system.Reservation;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Date;

public class ReservationDB implements DB {
    public static final String SEP = ",";

    

    

    @Override
    public ArrayList read(String fileName) throws IOException {
        // TODO Auto-generated method stub
        SimpleDateFormat stringToDate = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList stringArray = (ArrayList) ProcessFile.read(fileName);
        ArrayList totalArray = new ArrayList();

        for (int i=0; i<stringArray.size(); i++) {
            String currentEntry = (String) stringArray.get(i);

            StringTokenizer seperateEntry = new StringTokenizer(
                            currentEntry,SEP);

            
        }

        
        return null;
    }

    @Override
    public void save(String fileName, List al) throws IOException {
        // TODO Auto-generated method stub
        
    }
    
}
