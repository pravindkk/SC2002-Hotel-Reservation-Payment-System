package com.hotel.db;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.sql.Date;
import java.util.Arrays ;
import org.apache.commons.lang3.ArrayUtils;

import com.hotel.system.Payment;
import com.hotel.system.Order;
import com.hotel.system.enums.OrderStatus;
import com.hotel.system.enums.FoodType;
import com.hotel.system.Item;



public class PaymentDB extends DB {

    @Override
	public ArrayList read(String fileName) throws IOException {
        List<String[]> listing = super.readAllData(fileName);
        ArrayList allData = new ArrayList();

        for (String[] row : listing) {

            Integer paymentId = Integer.valueOf(row[0]);
			Double subTotal = Double.valueOf(row[1]);
			Double total = Double.valueOf(row[2]);
            String reservationNum = row[3];
            Date date = Date.valueOf(row[4]);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            formatter.format(date);

            ArrayList<Order> allOrders = new ArrayList<Order>();
            for(int i=5;i<listing.size();i+=4){
                // get orders
                String orderid = row[i];
                String roomid = row[i+1];
                String rNum = row[i+2];
                Date d = Date.valueOf(row[i+3]);
                OrderStatus orderStatus = OrderStatus.valueOf(row[i+4]);
                String remarks = row[i+5];

                // get items
                ArrayList<Item> allItems = new ArrayList<Item>();
                for (int j=11; i<listing.size(); j+=11) {
                    int itemid = Integer.valueOf(row[j]);
                    String name = row[j+1];
                    String description = row[j+2];
                    Double price = Double.valueOf(row[j+3]);
                    FoodType foodType = FoodType.valueOf(row[j+4]);
                    allItems.add(new Item(itemid, name, description, price, foodType));
                }


                allOrders.add(new Order(orderid,roomid,rNum,allItems,d,orderStatus,remarks));
            
            }

            Payment p = new Payment(paymentId , allOrders ,reservationNum ,formatter,total,subTotal);
            allData.add(p);

        }

        return allData;
		
	}

    @Override
    public void save(String fileName, List al) throws IOException {
        // TODO Auto-generated method stub

        List<String[]> toWrite = new ArrayList<String[]>();
        for(int i =0; i<al.size();i++){
            Payment payment = (Payment)al.get(i);
            String[] toAddPayment = new String[]{
                String.valueOf(payment.getPaymentId()),
                String.valueOf(payment.getSubTotal()),
                String.valueOf(payment.getTotal()),
                payment.getReservationNum(),
                String.valueOf(payment.getDate()),
            };

        

            for (int j=0; i<al.size(); j++) {
                Order order = (Order) al.get(i);

                String[] toAddOrder = new String[] {
                    order.getOrderId(),
                    order.getRoomId(),
                    order.getReservationNum(),
                    String.valueOf(order.getDate()),
                    String.valueOf(order.getOrderStatus()),
                    order.getRemarks(),
                };

                // String[] both = ArrayUtils.addAll(first, second)

                ArrayList items = order.getItem();
                for (int k=0; j<items.size(); k++) {
                    Item oneItem = (Item) items.get(j);
                    String[] toAddItem = new String[] {
                        String.valueOf(oneItem.getItemId()),
                        oneItem.getName(),
                        oneItem.getDescription(),
                        String.valueOf(oneItem.getPrice()),
                        String.valueOf(oneItem.getType())
                    };

                    toAddOrder = ArrayUtils.addAll(toAddOrder, toAddItem);
                    // hello.add(String.valueOf(oneItem.getItemId()));
                    // hello[hello.size()++] = newItem;

                }
                toAddPayment = ArrayUtils.addAll(toAddPayment, toAddOrder);

            

        }

        toWrite.add(toAddPayment);

    }
        



        super.writeAllData(fileName, toWrite);

        
    }
}
