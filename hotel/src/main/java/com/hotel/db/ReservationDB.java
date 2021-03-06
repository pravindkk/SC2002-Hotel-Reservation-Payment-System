package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.hotel.system.Reservation;
import com.hotel.system.enums.*;

// import Entity.Reservation;
/**
 * Represents the derived class of DB , which is used to read and write all data to the text file that contains Reservation.
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class ReservationDB extends DB {
	
	// public static final String SEPARATOR = "|";
    private File database = new File("hotel/reservation.csv");
    private String path;

    /**
     * Constructor for ReservationDB
     */
    public ReservationDB(){
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
     * The function getPath returns the path of the file as a String 
     * @return returns the path of the file as a String 
     */
    public String getPath() {
        return this.path;
    }


    /** 
     * Reads data from the database
     * @param fileName The name of the text file is passed in as a String input
     * @return ArrayList of the data in the text file is returned
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
	@Override
	public ArrayList read(String fileName) throws IOException {
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        for (String[] row : listing) {

            String reservationNum = row[0];
			String guestId = row[1];
			String roomId = row[2];

			String status = row[3];
			String checkInDate = row[4];
			String checkOutDate = row[5];
			String numOfAdults = row[6];
			String numOfChildren = row[7];


            Date checkIn = null;
			try {
				checkIn = df.parse(checkInDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Date checkOut = null;
			try {
				checkOut = df.parse(checkOutDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            Integer adults = Integer.valueOf(numOfAdults);
			Integer children = Integer.valueOf(numOfChildren);

            ReservationStatus rStatus = ReservationStatus.valueOf(status);

            Reservation r = new Reservation(rStatus, reservationNum, guestId, roomId, checkIn, checkOut, adults, children);
            allData.add(r);

        }

        return allData;
		
	}
	
	
    /** 
     * Saves data to the database
     * @param fileName The name of the text file that the data is going to be written to is passed in as a String input
     * @param al ArrayList of the data that is going to be written to is passed as a input
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    
    @Override
	public void save(String filename, List al) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<String[]> toWrite = new ArrayList<String[]>();
        

        for (int i=0; i<al.size(); i++) {
            Reservation r = (Reservation) al.get(i);

            toWrite.add(new String[] { r.getReservationNum(), 
                                       r.getGuestId(), 
                                       r.getRoomId(),
                                       r.getReservationStatus().toString(),
                                       df.format(r.getCheckInDate()),
                                       df.format(r.getCheckOutDate()),
                                       String.valueOf(r.getNumOfAdults()),
                                       String.valueOf(r.getNumOfChildren()),
                                     });


            
        }

        super.writeAllData(filename, toWrite);
	// 	List alw = new ArrayList();
	// 	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// 	for (int i = 0; i < al.size(); i++) {
	// 		Reservation r = (Reservation) al.get(i);
	// 		StringBuilder st = new StringBuilder();
	// 		st.append(r.getReservationNum().trim());
	// 		st.append(SEPARATOR);
	// 		st.append(r.getGuestId().trim());
	// 		st.append(SEPARATOR);
	// 		st.append(r.getRoomId().trim());
	// 		st.append(SEPARATOR);
	// 		st.append(r.getStatus().trim());
	// 		st.append(SEPARATOR);
	// 		st.append(sdf.format(r.getCheckInDate()).trim());
	// 		st.append(SEPARATOR);
	// 		st.append(sdf.format(r.getCheckOutDate()).trim());
	// 		st.append(SEPARATOR);
	// 		st.append(String.valueOf(r.getNumOfAdults()).trim());
	// 		st.append(SEPARATOR);
	// 		st.append(String.valueOf(r.getNumOfChildren()).trim());
	// 		st.append(SEPARATOR);
	// 		alw.add(st.toString());
	// 	}
		
	// 	ReadinFile.write(filename, alw);
	// }
    }
}
