package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.ResultFactory;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;

public class ReporterTest {
	
	
	private static IMainEngine mainEngine;
	private static MainEngineFactory factory;
	private static ArrayList<MeasurementRecord> objCollection;
	private static IResult result;
	static private String inputFilename;
	static private String outputFilename;
	static private String delimeter;
	static private Boolean hasHeader;
	static private int numFields;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		objCollection = new ArrayList<MeasurementRecord>();
		
		// ! MUST CHANGE for test to run successfully
		inputFilename = "E:\\eclipse-workspace\\stat-gen\\resources/TestInput/2007_sample.tsv";
		delimeter = "\t";
		hasHeader = false;
		numFields = 9;
	}
	
	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<MeasurementRecord>(); 
		result = null;
	}
	
	@Test
	public final void testTextReport() {
		
		// ! MUST CHANGE for test to run successfully
		// File extension is not necessary
		outputFilename = "E:\\eclipse-workspace\\stat-gen/resources/TestOutput/report1";
		int lineCount = mainEngine.loadData(inputFilename, delimeter, hasHeader, numFields);
		System.out.println("Size to process: " + lineCount);
		
		result = mainEngine.aggregateByTimeUnit("period of day", "avg", "Period of day avg aggregation over 2007");
		System.out.println("Time units with measurements: " + result.getDetailedResults().size());
		
		int printOutcome = mainEngine.reportResultInFile(result, "txt", outputFilename);
		assertEquals(printOutcome, 0);
		assertEquals(FileUtilities.countLinesOfAFile(outputFilename + ".txt"), 22);
	}
	
	@Test
	public final void testHtmlReport() {
		
		// File extension is not necessary
		outputFilename = "E:\\eclipse-workspace\\stat-gen/resources/TestOutput/report2";
		int lineCount = mainEngine.loadData(inputFilename, delimeter, hasHeader, numFields);
		System.out.println("Size to process: " + lineCount);
		
		result = mainEngine.aggregateByTimeUnit("week-day", "sum", "Week-day sum aggregation over 2007");
		System.out.println("Time units with measurements: " + result.getDetailedResults().size());
		
		int printOutcome = mainEngine.reportResultInFile(result, "html", outputFilename);
		assertEquals(printOutcome, 0);
		assertEquals(FileUtilities.countLinesOfAFile(outputFilename + ".html"), 32);
	}

}
