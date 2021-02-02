package client;

import java.util.ArrayList;
import java.util.Scanner;

import datamodel.IResult;
import datamodel.ResultFactory;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;

public class MainUI {
	
	
	public static void main(String[] args) {
		
		
		System.out.println("-----  Household Electric Power Consumption Statistics Generator  -----"+"\n");
		
		
		// Starting the user interface
		UIFactory userInterfaceFactory = new UIFactory();
		IUserInterface userInterface = userInterfaceFactory.createUI("UserInterface");
		
		
		// Starting the main engine
		MainEngineFactory mainEngineFactory = new MainEngineFactory();
		IMainEngine mainEngine = mainEngineFactory.createMainEngine("MainEngine");
		
		
		// Starting the IResult factory
		ResultFactory resultFactory = new ResultFactory();
		IResult result = null;
		
		
		// ReportHistory object and list to store the metadata of each and of all the reports respectively
		ReportHistory reportHistory = new ReportHistory();
		ArrayList<ReportHistory> reportHistoryList = new ArrayList<>();
		
		
		// Opening scanner to get user input
		Scanner scan = new Scanner(System.in);
		
		
		int userInput;
		String fileName;
		boolean hasHeaderLine;
		String delimeter;
		int numFields;
		int loadFlag = 0;
		int reportFlag = 1;
		String timeUnit;
		String functionType;
		String resultDescription;
		String reportType;
		String timeUnitBackup = " ";
		String functionTypeBackup = " ";
		String resultDescBackup = " ";
		
		
		while (true) {
			
			userInput = userInterface.askForUserInput(scan);
			
			// Load file scenario
			if (userInput == 1) {
				
				fileName = userInterface.askForInputFilePath(scan);
				
				// The given file path is a valid file
				if (!fileName.equals("0")) {
					
					// Check the file for the loading parameters
					hasHeaderLine = userInterface.checkForHeaderLine(fileName);
					delimeter = userInterface.findDelimeter(fileName, hasHeaderLine);
					numFields = userInterface.findNumFields(fileName, delimeter, hasHeaderLine);
					
					// The given file has one of the expected delimeters
					if (!delimeter.equals("0")) {
						
						// Attempt to load file
						loadFlag = mainEngine.loadData(fileName, delimeter, hasHeaderLine, numFields);
					}		
				}
			}
			
			// Calculate statistics scenario
			else if (userInput == 2) {
				
				// Measurements successfully loaded
				if (loadFlag != 0) {
					
					// Get the aggregation preferences from the user
					timeUnit = userInterface.askForTimeUnit(scan);
					
					// The user didn't select to exit the aggregation process
					if (!timeUnit.equals("0")) {
						
						functionType = userInterface.askForFunctionType(scan);
						
						// The user didn't select to exit the aggregation process
						if (!functionType.equals("0")) {
							
							resultDescription = userInterface.askForResultDescription(scan);
							
							// Create a new IResult
							result = resultFactory.createResult(timeUnit);
							
							// Attempt to aggregate data
							result = mainEngine.aggregateByTimeUnit(timeUnit, functionType, resultDescription);
							
							// Successful aggregation 
							if (result != null) {
								
								// Keep the report metadata
								timeUnitBackup = timeUnit;
								functionTypeBackup = functionType;
								resultDescBackup = resultDescription;
								
							}
						}
					}	
				}
				
				// No measurements loaded
				else {
					
					System.out.println("\n" + "Must load a file first!");
				}
			}
			
			// Create report scenario
			else if (userInput == 3) {
				
				// Data successfully aggregated
				if (result != null) {
					
					// Get the report preferences from the user
					fileName = userInterface.askForOutputFilePath(scan);
					
					// The user didn't select to exit the report creation process
					if (!fileName.equals("0")) {
						
						reportType = userInterface.askForReportType(scan);
						
						if (!reportType.equals("0")) {
							
							// Attempt to create a report
							reportFlag = mainEngine.reportResultInFile(result, reportType, fileName);
							
							// Successful report creation
							if (reportFlag == 0) {
								
								// Create a new report history object
								reportHistory = new ReportHistory();
								
								// Add the report metadata to the history list
								reportHistory.setTimeUnit(timeUnitBackup);
								reportHistory.setFunctionType(functionTypeBackup);
								reportHistory.setDescription(resultDescBackup);
								reportHistory.setOutputPath(fileName);
								reportHistory.setReportType(reportType);
								reportHistoryList.add(reportHistory);
								
							}
						}
					}
				}
				
				// No data aggregated
				else {
					
					System.out.println("\n" + "Must calculate statistics first!");
				}		
			}
			
			// View report history scenario
			else if (userInput == 4) {
				
				// At least one report has been created 
				if (!reportHistoryList.isEmpty()) {
					
					userInterface.viewHistory(reportHistoryList);
					
				}
				
				// Empty history
				else {
					
					System.out.println("\n" + "Nothing to show yet!");
					
				}
			}
			
			// Exit program scenario
			else if (userInput == 0) {
				
				scan.close();
				userInterface.exitProgram();
				
			}
			
		}

	}

}
