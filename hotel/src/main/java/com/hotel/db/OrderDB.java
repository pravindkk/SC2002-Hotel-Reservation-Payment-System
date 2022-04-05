package com.hotel.db;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hotel.system.Item;
import com.hotel.system.Order;
import com.hotel.system.Room;
import com.hotel.system.enums.BedType;
import com.hotel.system.enums.FoodType;
import com.hotel.system.enums.OrderStatus;
import com.hotel.system.enums.RoomStatus;
import com.hotel.system.enums.RoomType;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Represents the derived class of DB , which is used to read and write all data to the text file that contains Orders.
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class OrderDB extends DB{

    private File database = new File("hotel/orders.csv");
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private String path;

    public OrderDB(){
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
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();
        
        for (String[] row : listing) {
            ArrayList<Item> allItems = new ArrayList<Item>();

            String orderId = row[0];
			String roomId = row[1];
			String reservationNum = row[2];
			String date = row[3];
			OrderStatus orderStatus = OrderStatus.valueOf(row[4]);
			String remarks = row[5];
			// String roomFloor = row[6];
			// String roomNumber = row[7];

            Date orderDate = null;
			try {
				orderDate = df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // String [] nextLine;
            int rowNumber = 6;

            while (rowNumber < row.length) {
                int itemid = Integer.valueOf(row[rowNumber++]);
                String name = row[rowNumber++];
                String description = row[rowNumber++];
                Double price = Double.valueOf(row[rowNumber++]);
                FoodType foodType = FoodType.valueOf(row[rowNumber++]);
                Item newItem = new Item(itemid, name, description, price, foodType);
                
                allItems.add(newItem);
                // allItems.add(new Item(itemid, name, description, price, foodType));
            }

            allData.add(new Order(orderId, roomId, reservationNum, allItems, orderDate, orderStatus, remarks));

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
            Order order = (Order) al.get(i);

            String[] toAddLine = new String[] {
                order.getOrderId(),
                order.getRoomId(),
                order.getReservationNum(),
                df.format(order.getDate()),
                String.valueOf(order.getOrderStatus()),
                order.getRemarks(),
            };

            // String[] both = ArrayUtils.addAll(first, second)

            ArrayList items = order.getItem();
            for (int j=0; j<items.size(); j++) {
                Item oneItem = (Item) items.get(j);
                String[] toAddItem = new String[] {
                    String.valueOf(oneItem.getItemId()),
                    oneItem.getName(),
                    oneItem.getDescription(),
                    String.valueOf(oneItem.getPrice()),
                    String.valueOf(oneItem.getType())
                };

                toAddLine = ArrayUtils.addAll(toAddLine, toAddItem);
                // hello.add(String.valueOf(oneItem.getItemId()));
                // hello[hello.size()++] = newItem;

            }

            toWrite.add(toAddLine);

        }
        



        super.writeAllData(fileName, toWrite);

        
    }
}
