package com.hotel;

import java.io.File;

// Java program to illustrate reading
// two CSV files
// with different separators
 
import java.io.FileReader;
import java.io.IOException;
// import java.io.IOException;
import java.util.List;
import com.opencsv.*;
 
public class ReadCSVData {
    private static final String CSV_FILE_PATH
        = "test.csv";
    private static final String CSV_FILE_CUSTOM_SEPARATOR
        = "test.csv";
 
    public static void main(String[] args){
        File file = new File("hotel/data.csv");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        System.out.println(file.getAbsolutePath());

        System.out.println("Read Data Line by Line With Header \n");
        readDataLineByLine(file.getAbsolutePath());
        System.out.println("_______________________________________________");
 
        System.out.println("Read All Data at Once and Hide the Header also \n");
        readAllDataAtOnce(file.getAbsolutePath());
        System.out.println("_______________________________________________");
 
        // System.out.println("Custom Separator here semi-colon\n");
        // readDataFromCustomSeparator(CSV_FILE_CUSTOM_SEPARATOR);
        // System.out.println("_______________________________________________");
    }
 
    public static void readDataLineByLine(String file)
    {
 
        try {
            
 
            // Create an object of filereader class
            // with CSV file as a parameter.
            FileReader filereader = new FileReader(file);
 
            // create csvReader object passing
            // filereader as parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
 
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void readAllDataAtOnce(String file)
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
 
            // print Data
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void readDataFromCustomSeparator(String file)
    {
        try {
            // Create object of filereader
            // class with csv file as parameter.
            FileReader filereader = new FileReader(file);
 
            // create csvParser object with
            // custom separator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
 
            // create csvReader object with
            // parameter filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                                      .withCSVParser(parser)
                                      .build();
 
            // Read all data at once
            List<String[]> allData = csvReader.readAll();
 
            // print Data
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}