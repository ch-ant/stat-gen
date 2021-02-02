package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UIMethods implements IUserInterface {

	
	// Terminates the program
	@Override
	public void exitProgram() {

		System.out.println("\n"+"Goodbye!~");
		System.exit(0);
		
	}

	
	/* Main method responsible for getting the user input
	 * Returns an int corresponding to the user's choice: 
	 * 1 to load file, 2 to calculate statistics, 3 to create report, 4 to view report history,
	 * 0 if smth goes wrong
	 */
	@Override
	public int askForUserInput(Scanner scan) {
		
		
		String userInput = " ";
		
		while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("0")) {
			
			System.out.println("Please enter:");
			System.out.println("(1) to load file");
			System.out.println("(2) to calculate statistics");
			System.out.println("(3) to create report");
			System.out.println("(4) to view report history");
			System.out.println("(0) to exit program");
			
			userInput = scan.nextLine();
			
			if (userInput.equals("1")) {
				return 1;
			}
			else if (userInput.equals("2")) {
				return 2;
			}
			else if (userInput.equals("3")) {
				return 3;
			}
			else if (userInput.equals("4")) {
				return 4;
			}
			else if (userInput.equals("0")) {
				return 0;
			}
			else {
				System.out.println("\n" + "Invalid input!");
			}
		}
		return 0;
	}


	/* Method responsible for asking the user for the file path of the file to be loaded
	 * Returns a String containing the file path or a String 0 if the given path/file is invalid
	 */
	@Override
	public String askForInputFilePath(Scanner scan) {
		
		String fileName = " ";
		boolean validFile = false;
		
		System.out.println("NOTES:");
		System.out.println("An absolute file path is required (e.g. E:\\eclipse-workspace\\text.txt)");
		System.out.println("Acceptable file types are: .txt and .tsv");
		System.out.println("File type extension must be included");
		System.out.println("Acceptable delimeters are: ;, tab, space)");
		System.out.println("Expected number of columns for each line: 9");
		System.out.println("\n"+"Please enter a file path:");
		
		fileName = scan.nextLine();
		validFile = checkInputFile(fileName);
		
		if (validFile) {
			return fileName;
		}
		else {
			System.out.println("\n"+"Invalid file!");
			return "0";
		}
		
	}


	/* checks if a file exists and it's extension
	 * returns true if the file exists and is in txt or tsv format
	 */
	private boolean checkInputFile(String fileName) {
		
		File newFile = new File(fileName);
		
		if (newFile.isFile()) {
			
			String extension = "";
			
			int i = fileName.lastIndexOf('.');
	        if (i > 0) {
	        	extension = fileName.substring(i+1);
	        }
	        if (extension.equals("txt") || extension.equals("tsv")) {
	        	return true;
	        }
	        else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	
	/* Method responsible for getting the description thats complements the statistics from the user
	 * Note that the description can not be larger than one line
	 * Returns a string with the description
	 */
	public String askForResultDescription(Scanner scan) {
		
		System.out.println("Please enter a description to complement the resulting statistics:");
		
		String userInput;
		userInput = scan.nextLine();
		
		return userInput;
	}


	/* Method responsible for getting the function type input from the user
	 * Returns a string corresponding to the chosen function type or a String 0 if the user selects exit of aggregation process
	 */
	public String askForFunctionType(Scanner scan) {
		
		String userInput = " ";
		
		while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("0")) {
			
			System.out.println("Select a function type or cancel:");
			System.out.println("(1) average");
			System.out.println("(2) sum");
			System.out.println("(0) cancel");
			
			userInput = scan.nextLine();
			
			if (userInput.equals("1")) {
				return "avg";
			}
			else if (userInput.equals("2")) {
				return "sum";
			}
			else if (userInput.equals("0")) {
				return "0";
			}
			else {
				System.out.println("\n" + "Invalid Input");
			}
		}
		System.out.println("\n" + "Oops! Something went wrong!");
		return "0";
	}


	/* Method responsible for getting the time unit input from the user
	 * Returns a String corresponding to the chosen time unit or a String 0 if the user selects exit of aggregation process
	 */
	public String askForTimeUnit(Scanner scan) {
		
		System.out.println("Please define the type of statistics to be generated.");
		
		String userInput = " ";
		
		while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("0")) {
			
			System.out.println("Select a time unit or cancel:");
			System.out.println("(1) season");
			System.out.println("(2) month");
			System.out.println("(3) week-day");
			System.out.println("(4) period of day");
			System.out.println("(0) cancel");
			
			userInput = scan.nextLine();
			
			if (userInput.equals("1")) {
				return "season";
			}
			else if (userInput.equals("2")) {
				return "month";
			}
			else if (userInput.equals("3")) {
				return "week-day";
			}
			else if (userInput.equals("4")) {
				return "period of day";
			}
			else if (userInput.equals("0")) {
				return "0";
			}
			else {
				System.out.println("\n" + "Invalid input!");
			}
		}
		System.out.println("\n" + "Oops! Something went wrong!");
		return "0";
	}

	
	/* Method responsible for getting the output path where the report will be created
	 * Returns a String corresponding to the path the user entered 
	 * or a String 0 if the user chooses to exit the report creation process
	 */
	@Override
	public String askForOutputFilePath(Scanner scan) {
		
		String userInput;
		String dirPath;
		String fileName;
		boolean validFile = false;
		
		while (validFile == false) {
			
			System.out.println("\n"+"Please enter an output path:");
			System.out.println("NOTE: An absolute directory path is required (e.g. E:\\eclipse-workspace)");
			
			dirPath = scan.nextLine();
			validFile = checkOutputDir(dirPath);
			
			if (validFile == false) {
				
				System.out.println("\n"+"Invalid output path!");
				System.out.println("Please enter:" + "\n" + "(1) to enter another output path" + "\n" + "(0) to cancel");
				
				userInput = scan.nextLine();
				
				while (!userInput.equals("1") && !userInput.equals("0") ) {
					
					System.out.println("\n" + "Invalid input!" + "\n" + "Please enter a correct value:");
					System.out.println("(1) to enter another output path" + "\n" + "(0) to cancel");
					
					userInput = scan.nextLine();
					
					if (userInput.equals("0")) {
						
						return "0";
					}
				}
			}
			else {
				
				System.out.println("\n"+"Please enter the name for your report:");
				System.out.println("NOTE: A file name alone is required (e.g. MyFile)");
				
				fileName = scan.nextLine();
				validFile = checkOutputFile(dirPath + "\\" + fileName);
				
				if (validFile == true) {
					
					return dirPath + "\\" + fileName;
				}
				else {
					
					System.out.println("\n"+"Invalid output path!");
					System.out.println("Please enter:" + "\n" + "(1) to enter another output path" + "\n" + "(0) to cancel");
					
					userInput = scan.nextLine();
					
					while (!userInput.equals("1") && !userInput.equals("0") ) {
						
						System.out.println("\n" + "Invalid input!" + "\n" + "Please enter a correct value:");
						System.out.println("(1) to enter another output path" + "\n" + "(0) to cancel");
						
						userInput = scan.nextLine();
						
						if (userInput.equals("0")) {
							
							return "0";
						}
					}
				}
			}
		}
		System.out.println("Oops! Something went wrong!");
		return "0";
	}

	
	/* Method responsible for checking if a String is a valid file path where a report file can be created
	 * Returns true if so, false otherwise
	 */
	private boolean checkOutputFile(String userInput) {
		
		File filePath = new File(userInput);
		
		try {
			if (filePath.createNewFile()) {
				
				if (filePath.canRead() && filePath.canWrite()) {
					
					filePath.delete();
					
					return true;
				}
				
			}
		} catch (IOException e) {
			
			//e.printStackTrace();
			return false;
		}
		return false;
	}

	/* Method responsible for checking if a String is a readable and writeable directory
	 * Returns true if so, false otherwise
	 */
	private boolean checkOutputDir(String userInput) {
		
		File dirPath = new File(userInput);
		
		if (dirPath.isDirectory() && dirPath.canRead() && dirPath.canWrite()) {
			return true;
		}
		else {
			return false;
		}
	}

	
	/* Method responsible for getting the report type input from the user
	 * Returns a string corresponding to the chosen report type
	 * or a String 0 if the user chooses to exit the report creation process
	 */
	@Override
	public String askForReportType(Scanner scan) {
		
		String userInput = " ";
		
		while (!userInput.equals("1") && !userInput.equals("2")) {
			
			System.out.println("Select a report type or exit:");
			System.out.println("(1) text");
			System.out.println("(2) html");
			System.out.println("(3) markdown");
			System.out.println("(0) cancel");
			
			userInput = scan.nextLine();
			
			if (userInput.equals("1")) {
				return "txt";
			}
			else if (userInput.equals("2")) {
				return "html";
			}
			else if (userInput.equals("3")) {
				return "md";
			}
			else if (userInput.equals("0")) {
				return "0";
			}
			else {
				System.out.println("\n" + "Invalid Input");
			}
		}
		System.out.println("Oops! Something went wrong!");
		return "0";
	}

	
	// Method responsible for showing the report history list to the user
	@Override
	public void viewHistory(ArrayList<ReportHistory> historyList) {
		
		System.out.println("---- Report History ----");
		
		for (int i=0; i < historyList.size(); i++) {
			
			System.out.println("\n" + "Report num." + (i+1));
			System.out.println("Report type: " + historyList.get(i).getReportType());
			System.out.println("Time unit: " + historyList.get(i).getTimeUnit());
			System.out.println("Function type: " + historyList.get(i).getFunctionType());
			System.out.println("Path: " + historyList.get(i).getOutputPath());
			System.out.println("Description: " + historyList.get(i).getDescription() + "\n");
			
		}
		
		System.out.println("-----------------------" + "\n");
	}

	
	/* Method responsible for checking whether an input file has a header line or not
	 * Returns true if so, false otherwise
	 */
	@Override
	public boolean checkForHeaderLine(String fileName) {
		
		Scanner inputStream = null;
		String firstLine;
		
		try {
			
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch (FileNotFoundException e) {
			
			return false;
		}
		if (inputStream.hasNextLine()) {
			
			firstLine = inputStream.nextLine().toString();
			
			if (firstLine.contains("Date")) {
				
				inputStream.close();
				return true;
			}
		}
		
		inputStream.close();
		return false;
	}


	/* Method responsible for checking whether the input file has one of the expected delimeters, \t, space or ";"
	 * Returns a String containing the delimeter or a String 0 if the given file has none of the expected delimeters
	 */
	@Override
	public String findDelimeter(String fileName, boolean hasHeaderLine) {
		
		Scanner inputStream = null;
		String line;
		
		try {
			
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch (FileNotFoundException e) {
			
			return "0";
		}
		
		if (hasHeaderLine ) {
			inputStream.nextLine();
		}
		
		if (inputStream.hasNextLine()) {
			
			line = inputStream.nextLine().toString();
			
			if (line.contains("\t") && !line.contains(" ") && !line.contains(";")) {
				
				inputStream.close();
				return "\t";
			}
			else if (!line.contains("\t") && line.contains(" ") && !line.contains(";")) {
				
				inputStream.close();
				return " ";
			}
			else if (!line.contains("\t") && !line.contains(" ") && line.contains(";")) {
				
				inputStream.close();
				return ";";
			}
			else {
				
				System.out.println("Invalid delimeter in file! Please try another file");
				inputStream.close();
				return "0";
			}
		
		}
		System.out.println("\n" + "Empty or invalid file! Please try another file!");
		inputStream.close();
		return "0";
	}


	/* Method responsible for checking the number of fields in a file
	 * Returns an int corresponding to the number of fields in the file or int 0 otherwise
	 */
	@Override
	public int findNumFields(String fileName, String delimeter, boolean hasHeaderLine) {
		
		Scanner inputStream = null;
		String line;
		
		try {
			
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch (FileNotFoundException e) {
			
			return 0;
		}
		
		if (hasHeaderLine) {
			inputStream.nextLine();
		}
		
		if (inputStream.hasNextLine()) {
			
			line = inputStream.nextLine().toString();
			StringTokenizer tokenizer = new StringTokenizer(line, delimeter);
			
			inputStream.close();
			return tokenizer.countTokens();
		}
		
		inputStream.close();
		return 0;
	}

}
