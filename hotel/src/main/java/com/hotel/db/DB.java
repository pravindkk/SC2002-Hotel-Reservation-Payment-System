package com.hotel.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import com.opencsv.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the abstract class of DB , which is used to read and write all data to the text file.
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

abstract class DB {
    /**
     * 
     * @param fileName The name of the file is passed as the parameter so that data could be read from the text file
     * @return An arraylist of the data is returned
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public abstract ArrayList read(String fileName) throws IOException ;

    /**
     * 
     * @param fileName The name of the file that is the data is going to be written to 
     * @param al Contains data that is going to be written in to the file
     * @throws IOException Due to communication with the DataBase IOexception is required
     */



	public abstract void save(String fileName, List al) throws IOException ;

    /**
     * 
     * @param file The name of the file is passed as the parameter so that data could be read from the text file
     * @return An arraylist of the data is returned
     * @throws FileNotFoundException
     */

    public static List readAllData(String file) throws FileNotFoundException {
        List data = new ArrayList();
	
		Scanner scanner = new Scanner(new FileInputStream(file));
		try {
            while (scanner.hasNextLine()){
                String[] add = scanner.nextLine().split(",");
                
                data.add(add);
                // data.add(scanner.nextLine());
            }
        }
        finally{
            scanner.close();
        }
		return data;
       
        // storing the data in arraylist to array
        
       
        // printing each line of file
        // which is stored in array
        // return listOfStrings;
    }

    /**
     * 
     * @param fileName The name of the file that is the data is going to be writtten to 
     * @param stringArray Contains data that is going to be written in to the file
     * @throws IOException Due to communication with the DataBase IOexception is required
     */

    public static void writeAllData(String fileName, List<String[]> stringArray) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try{
            for (String[] line: stringArray) {
                StringBuilder builder = new StringBuilder();
                // for(String s : line) {
                //     builder.append(s);
                // }
                for (int i=0; i<line.length; i++) {
                    builder.append(line[i]);
                    builder.append(",");
                }
                builder.append(line[line.length-1]);
                String str = builder.toString();
                out.println(str);
            }
        }
        finally {
            out.close();
        }

    }
}
