package com.hotel.db;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.opencsv.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the abstract class of DB , which is used to read and write all data to the text file.
 * @author Pravind Kummar
 * @version 1.0
 * @since 1.0
 */

abstract class DB {
    /**
     * Abstract method to read data from the database. This will be overwritten by child classes
     * @param fileName The name of the file is passed as the parameter so that data could be read from the text file
     * @return An arraylist of the data is returned
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public abstract ArrayList read(String fileName) throws IOException ;


    /**
     * Abstract method to save data to the database. This will be overwritten by child classes
     * @param fileName The name of the file that is the data is going to be written to 
     * @param al Contains data that is going to be written in to the file
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
	public abstract void save(String fileName, List al) throws IOException ;

    /**
     * Reads all data to the database
     * @param file The name of the file is passed as the parameter so that data could be read from the text file
     * @return An arraylist of the data is returned
     */
    public static List readAllData(String file)
    {
        try {
 
            // Create an object of filereader class
            // with CSV file as a parameter.
            FileReader filereader = new FileReader(file);
 
            // create csvReader object
            // and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                                    //   .withSkipLines(1)
                                      .build();
            List<String[]> allData = csvReader.readAll();
			return allData;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Saves all data to the database
     * @param fileName The name of the file that is the data is going to be writtten to 
     * @param stringArray Contains data that is going to be written in to the file
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void writeAllData(String fileName, List<String[]> stringArray) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        for (String[] array : stringArray) {
            writer.writeNext(array);
        }
        
        writer.close();
    }
}
