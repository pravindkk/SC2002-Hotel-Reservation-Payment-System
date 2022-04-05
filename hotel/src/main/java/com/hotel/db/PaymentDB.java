package com.hotel.db;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Date;
import java.util.Arrays ;
import org.apache.commons.lang3.ArrayUtils;

import com.hotel.system.Payment;
import com.hotel.system.Order;
import com.hotel.system.enums.OrderStatus;
import com.hotel.system.enums.FoodType;
import com.hotel.system.Item;
import com.hotel.controller.OrderController;
import java.text.SimpleDateFormat;

/**
 * Represents the derived class of DB , which is used to read and write all data to the text file that contains Payments.
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */


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
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");  

        for (String[] row : listing) {
            String paymentId = row[0];
            String guestId = row[1];
			Double subTotal = Double.valueOf(row[2]);
			Double total = Double.valueOf(row[3]);
            String reservationNum = row[4];
            String date = row[5];
            // Date date = Date.valueOf(row[5]);
            
            Date newDate = null;
            try {
                newDate = df.parse(date);
            } catch (Exception e) {
                //TODO: handle exception
            }
            

            // comment

            // use my menudb to refer to change this then change the write portion accordingly
            // in the payment controller just use OrderController.getOrder(String orderId)

            ArrayList<String> orderId = new ArrayList<String>();
            int rowNumber = 6;

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

            Payment p = new Payment(paymentId, guestId , orderId ,reservationNum ,newDate,total,subTotal);
            allData.add(p);

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
        for(int i =0; i<al.size();i++){
            Payment payment = (Payment)al.get(i);
            String[] toAddPayment = new String[]{
                payment.getPaymentId(),
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
