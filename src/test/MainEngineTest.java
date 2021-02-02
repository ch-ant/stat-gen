package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;


public class MainEngineTest {
	
	
	private static IMainEngine mainEngine;
	private static MainEngineFactory factory;
	private static ArrayList<MeasurementRecord> objCollection;
	private static IResult result;
	static private String inputFilename;
	static private String outputFilename;
	static private String delimeter;
	static private Boolean hasHeader;
	static private int numFields;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		objCollection = new ArrayList<MeasurementRecord>();
		
		// ! MUST CHANGE for test to run successfully
		inputFilename = "E:\\eclipse-workspace\\stat-gen\\resources\\TestInput\\2007_sample.tsv";
		delimeter = "\t";
		hasHeader = false;
		numFields = 9;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<MeasurementRecord>(); 
		result = null;
	}

	/**
	 * Test method for {@link mainengine.MainEngine#loadData(java.lang.String, java.lang.String, java.lang.Boolean, int, java.util.ArrayList)}.
	 */
	@Test
	public final void testLoadData() {
		// ! MUST CHANGE for test to run successfully
		int numRows = mainEngine.loadData("E:\\eclipse-workspace\\stat-gen\\resources/TestInput/hld_with_emptyCells.txt", ";", true, numFields);	
		assertEquals(numRows, 6); // title excluded
	}

	/**
	 * Test method for {@link mainengine.MainEngine#aggregateByTimeUnit(java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testAggregateByTimeUnit() {

		int numRows = mainEngine.loadData(inputFilename, delimeter, hasHeader, numFields);
		System.out.println("Size to process: " + numRows);
		
		result = mainEngine.aggregateByTimeUnit("week-day", "avg", "Day of week avg aggregation over 2007 sample");
		
		//result.reportAggregates();
		assertEquals(result.getAggregateMeterKitchen().get("MON"), 1.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("TUE"), 1.9473684210526316 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("WED"), 0.041666666666666664 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("THU"), 1.7999999999999998 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("FRI"), 0.30769230769230765 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("SAT"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("SUN"), 0.07142857142857144,2);

		assertEquals(result.getAggregateMeterLaundry().get("MON"), 0.5625 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("TUE"), 0.42105263157894735 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("WED"), 2.25 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("THU"), 0.25 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("FRI"), 0.4615384615384615 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("SAT"), 0.14285714285714288 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("SUN"), 0.8571428571428571,2);


		assertEquals(result.getAggregateMeterAC().get("MON"), 2.1875 ,2);
		assertEquals(result.getAggregateMeterAC().get("TUE"), 3.7368421052631584 ,2);
		assertEquals(result.getAggregateMeterAC().get("WED"), 5.125 ,2);
		assertEquals(result.getAggregateMeterAC().get("THU"), 3.8 ,2);
		assertEquals(result.getAggregateMeterAC().get("FRI"), 6.769230769230769 ,2);
		assertEquals(result.getAggregateMeterAC().get("SAT"), 9.857142857142858 ,2);
		assertEquals(result.getAggregateMeterAC().get("SUN"), 4.857142857142857,2);
		
	}

	/**
	 * Test method for {@link mainengine.MainEngine#reportResultInFile(datamodel.IResult, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testReportResultInFile() {
		
		// ! MUST CHANGE for test to run successfully
		// File extension is not necessary
		outputFilename = "E:\\eclipse-workspace\\stat-gen/resources/TestOutput/tt";
		int numRows = mainEngine.loadData(inputFilename, delimeter, hasHeader, numFields);
		System.out.println("Size to process: " + numRows);
		
		result = mainEngine.aggregateByTimeUnit("month", "avg", "Monthly avg aggregation over 2007");
		System.out.println("Time units with measurements: " + result.getDetailedResults().size());
		
		int printOutcome = mainEngine.reportResultInFile(result, "md", outputFilename);
		assertEquals(printOutcome, 0);
		assertEquals(FileUtilities.countLinesOfAFile(outputFilename + ".md"),43);

	}

}
