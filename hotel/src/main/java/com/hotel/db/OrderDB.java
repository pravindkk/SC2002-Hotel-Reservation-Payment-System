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

public class OrderDB extends DB{
    private File database = new File("hotel/orders.csv");
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

    public String getPath() {
        return this.path;
    }

    @Override
	public ArrayList read(String fileName) throws IOException {
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (String[] row : listing) {


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

            ArrayList<Item> allItems = new ArrayList<Item>();
            for (int i=6; i<listing.size(); i+=5) {
                int itemid = Integer.valueOf(row[i]);
                String name = row[i+1];
                String description = row[i+2];
                Double price = Double.valueOf(row[i+3]);
                FoodType foodType = FoodType.valueOf(row[i+4]);
                allItems.add(new Item(itemid, name, description, price, foodType));
            }
            allData.add(new Order(orderId, roomId, reservationNum, allItems, orderDate, orderStatus, remarks));

        }
        return allData;
	}

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
                String.valueOf(order.getDate()),
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
