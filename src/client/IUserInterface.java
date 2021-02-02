package client;

import java.util.ArrayList;
import java.util.Scanner;

public interface IUserInterface {
	
	
	// Terminates the program
	void exitProgram();
	
	
	/* Main method responsible for getting the user input
	 * Returns an int corresponding to the user's choice: 
	 * 1 to load file, 2 to calculate statistics, 3 to create report, 4 to view report history,
	 * 0 if smth goes wrong
	 */
	int askForUserInput(Scanner scan);
	
	
	/* Method responsible for asking the user for the file path of the file to be loaded
	 * Returns a String containing the file path or a String 0 if the given path/file is invalid
	 */
	String askForInputFilePath(Scanner scan);
	
	
	/* Method responsible for getting the time unit input from the user
	 * Returns a String corresponding to the chosen time unit or a String 0 if the user selects exit of aggregation process
	 */
	String askForTimeUnit(Scanner scan);


	/* Method responsible for getting the function type input from the user
	 * Returns a string corresponding to the chosen function type or a String 0 if the user selects exit of aggregation process
	 */
	String askForFunctionType(Scanner scan);

	
	/* Method responsible for getting the description thats complements the statistics from the user
	 * Note that the description can not be larger than one line
	 * Returns a string with the description
	 */
	String askForResultDescription(Scanner scan);


	/* Method responsible for getting the output path where the report will be created
	 * Returns a String corresponding to the path the user entered 
	 * or a String 0 if the user chooses to exit the report creation process
	 */
	String askForOutputFilePath(Scanner scan);

	
	/* Method responsible for getting the report type input from the user
	 * Returns a string corresponding to the chosen report type
	 * or a String 0 if the user chooses to exit the report creation process
	 */
	String askForReportType(Scanner scan);

	
	// Method responsible for showing the report history list to the user
	void viewHistory(ArrayList<ReportHistory> reportHistoryList);


	/* Method responsible for checking whether an input file has a header line or not
	 * Returns true if so, false otherwise
	 */
	boolean checkForHeaderLine(String fileName);


	/* Method responsible for checking whether the input file has one of the expected delimeters, \t, space or ";"
	 * Returns a String containing the delimeter or a String 0 if the given file has none of the expected delimeters
	 */
	String findDelimeter(String fileName, boolean hasHeaderLine);


	/* Method responsible for checking the number of fields in a file
	 * Returns an int corresponding to the number of fields in the file or int 0 otherwise
	 */
	int findNumFields(String fileName, String delimeter, boolean hasHeaderLine);

}
