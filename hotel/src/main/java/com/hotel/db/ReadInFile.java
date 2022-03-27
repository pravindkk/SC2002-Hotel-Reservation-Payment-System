package com.hotel.db;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.opencsv.*;

public class ReadInFile {


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

    public static void writeAllData(String fileName, List<String[]> stringArray) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        for (String[] array : stringArray) {
            writer.writeNext(array);
        }
        
        writer.close();
    }
}
