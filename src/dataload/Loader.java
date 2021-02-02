package dataload;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import datamodel.MeasurementRecord;
import datamodel.MeasurementRecordFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Loader<E> implements ILoader<MeasurementRecord> {
	
	
	private Scanner inputStream;
	
	/* Reads the data from the given file and stores them in an ArrayList
	 * @return the number of rows that are eventually added to objCollection or 0 if unable to load file
	 */
	@Override
	public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields,
			ArrayList<MeasurementRecord> objCollection) {
		
		// Check the number of fields
		if (numFields < 9) {
			System.out.println("Wrong number of fileds in file, expected 9");
			System.out.println("Loading failure for file: " + fileName);
			return 0;
		}
		
		inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch (FileNotFoundException e) {
			inputStream.close();
			System.out.println("Loading failure for file: " + fileName);
			return 0;
		}
		
		int lineCount = 0;
		
		// Check for title 
		if (hasHeaderLine) {
			inputStream.nextLine();
		}
		
		// Process the lines one by one
		String line = "";
		while (inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			lineCount++;
			StringTokenizer tokenizer = new StringTokenizer(line, delimeter);
			int numTokens = tokenizer.countTokens();
			
			if (numTokens != numFields) {
				System.out.println("\n"+"Wrong input format in file " + fileName +"."+ " Line:" + lineCount +
						"\n"+ "I found " + numTokens + " values, but I expected " + numFields + " values per row!"+"\n"
						+"\n"+"Line not loaded!"+"\n");
			}
			else if (numTokens == numFields) {
				String[] tokens = new String[numFields - 4];
				int tokensLength = 0;
				for (int i=0; i < numFields; i++) {
					if (i == 0 || i == 1 || i == 6 || i == 7 || i == 8) {
						tokens[tokensLength] = tokenizer.nextToken();
						tokensLength++;
					}
					else {
						tokenizer.nextToken();
					}
				}
				int objConstructionErrorCode;
				objConstructionErrorCode = constructObjectFromLine(tokens, objCollection);
				if (objConstructionErrorCode != 0) {
					System.out.println("Invalid data format at line " + lineCount + ". Line not loaded!");
				}
				
			}
		}
		inputStream.close();
		System.out.println("\n" + "Successfully processed " + lineCount + " lines and loaded " + objCollection.size() + " objects.");
		
		return lineCount;
	}
	

	
	/* Method responsible for converting the String measurements of a line to their appropriate respective formats,
	 * checking if the measurements of the file are in valid format and adding them to an ArrayList<MeasurementRecord>.
	 * Returns 0 if the measurements are in valid format or 1 if they are not.
	 */ 
	private int constructObjectFromLine(String[] tokens, ArrayList<MeasurementRecord> objCollection) {
		
		LocalDate day;
		LocalTime time;
		double kitchen;
		double laundry;
		double AC;
		
		day = convertStringToDate(tokens[0]);
		time = convertStringToTime(tokens[1]);
		kitchen = convertStringToDouble(tokens[2]);
		laundry = convertStringToDouble(tokens[3]);
		AC = convertStringToDouble(tokens[4]);
		
		
		if (day != null && time != null && kitchen >= 0.0 && laundry >= 0.0 && AC >= 0.0) {
			
			MeasurementRecordFactory mFactory = new MeasurementRecordFactory();
			MeasurementRecord measurement = mFactory.createMeasurementRecord(day, time, kitchen, laundry, AC);
			
			objCollection.add(measurement);
			
			return 0;
		}
		else {
			
			return 1;
		}
		
	}


	
	/* Converts the given string to a double, checks exception
	 * Returns the string in double format or -99.99 if the given string is in invalid format
	 */
	private double convertStringToDouble(String measurement) {
		
		try {
			double measurementAsDouble = Double.parseDouble(measurement);
			return measurementAsDouble;
		}
		catch(NumberFormatException nfe) {
			return -99.99;
		}
	}



	/* Converts the given string to LocalTime format, checks exception
	 * Returns the string in LocalTime format or null if the given string is in invalid format
	 */
	private LocalTime convertStringToTime(String time) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		try {
			LocalTime dateTime = LocalTime.parse(time, formatter);
			return dateTime;
		}
		catch (DateTimeParseException e) {
			return null;
		}
	}



	/* Converts the given string to LocalDate format, checks exception
	 * Returns the string in LocalDate format or null if the given string is in invalid format
	 */
	private LocalDate convertStringToDate(String day) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			LocalDate dateTime = LocalDate.parse(day, formatter);
			return dateTime;
		}
		catch (DateTimeParseException e) {
			return null;
		}
	}

}
