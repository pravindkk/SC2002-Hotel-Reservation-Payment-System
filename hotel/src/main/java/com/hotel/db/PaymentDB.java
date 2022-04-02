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
import com.hotel.controller.OrderController;
import java.text.SimpleDateFormat;



public class PaymentDB extends DB {
    private File database = new File("hotel/payments.csv");
    private String path;


    public PaymentDB(){
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

        for (String[] row : listing) {

            String guestId = row[0];
			Double subTotal = Double.valueOf(row[1]);
			Double total = Double.valueOf(row[2]);
            String reservationNum = row[3];
            Date date = Date.valueOf(row[4]);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            formatter.format(date);

            // use my menudb to refer to change this then change the write portion accordingly
            // in the payment controller just use OrderController.getOrder(String orderId)

            ArrayList<String> orderId = new ArrayList<String>();
            int rowNumber = 5;

            while (rowNumber < row.length) {
                // get orders
                // String orderid = row[rowNumber++];
                // String roomId = row[rowNumber++];
                // String rNum = row[rowNumber++];

                // String date = row[rowNumber++];
                // OrderStatus orderStatus = OrderStatus.valueOf(row[rowNumber++]);
                // String remarks = row[rowNumber++];
                // Order newOrder = new Order(orderid, roomId, rNum, items, date, orderStatus, remarks)

                // allOrders.add(new Order(orderid,roomId,rNum,allItems,d,orderStatus,remarks));
                orderId.add(row[rowNumber++]);
            
            }

            Payment p = new Payment(guestId , orderId ,reservationNum ,formatter,total,subTotal);
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
                payment.getGuestId(),
                String.valueOf(payment.getSubTotal()),
                String.valueOf(payment.getTotal()),
                payment.getReservationNum(),
                String.valueOf(payment.getDate()),
            };

        
            ArrayList<String> orders = payment.getOrders();

            for (int j=0; j<orders.size(); j++) {
                String[] toadd = new String[] {
                    orders.get(j)
                };
                toAddPayment = ArrayUtils.addAll(toAddPayment, toadd);
            }

            toWrite.add(toAddPayment);
            
            

        }

        



        super.writeAllData(fileName, toWrite);

        
    }
}
