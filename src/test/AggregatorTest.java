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

public class AggregatorTest {
	
	
	private static IMainEngine mainEngine;
	private static MainEngineFactory factory;
	static private String inputFilename;
	private static IResult result;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		
		// ! MUST CHANGE for test to run successfully
		inputFilename = "E:\\eclipse-workspace\\stat-gen\\resources\\TestInput\\household_preview.txt";
	}
	
	@After
	public void tearDown() throws Exception {
		//objCollection = new ArrayList<MeasurementRecord>(); 
		result = null;
	}
	
	@Test
	public void testAggregatorMonthAvg() {
		
		boolean hasHeaderLine = true;
		String delimeter = ";";
		int numFields = 9;
		
		int lineCount = mainEngine.loadData(inputFilename, delimeter, hasHeaderLine, numFields);
		System.out.println("Size to process: " + lineCount);
		
		result = mainEngine.aggregateByTimeUnit("month", "avg", "household preview");
		
		
		assertEquals(result.getAggregateFunction(), "avg");
		assertEquals(result.getTimeUnitType(), "Month");
		assertEquals(result.getDescription(), "household preview");
		
		assertEquals(result.getAggregateMeterKitchen().get("JUL"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("OCT"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("FEB"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("JUN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("APR"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("AUG"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("DEC"), 0.0,2);
		assertEquals(result.getAggregateMeterKitchen().get("MAY"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("NOV"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("JAN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("MAR"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("SEP"), 0.0 ,2);
		
		assertEquals(result.getAggregateMeterLaundry().get("JUL"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("OCT"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("FEB"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("JUN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("APR"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("AUG"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("DEC"), 4.303030303030303,2);
		assertEquals(result.getAggregateMeterLaundry().get("MAY"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("NOV"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("JAN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("MAR"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("SEP"), 0.0 ,2);
		
		assertEquals(result.getAggregateMeterAC().get("JUL"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("OCT"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("FEB"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("JUN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("APR"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("AUG"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("DEC"), 16.858585858585858,2);
		assertEquals(result.getAggregateMeterAC().get("MAY"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("NOV"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("JAN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("MAR"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("SEP"), 0.0 ,2);
	}
	
	@Test
	public void testAggregatorSeasonSum() {
		
		boolean hasHeaderLine = true;
		String delimeter = ";";
		int numFields = 9;
		
		int lineCount = mainEngine.loadData(inputFilename, delimeter, hasHeaderLine, numFields);
		System.out.println("Size to process: " + lineCount);
		
		result = mainEngine.aggregateByTimeUnit("season", "sum", "household preview");
		
		assertEquals(result.getAggregateFunction(), "sum");
		assertEquals(result.getTimeUnitType(), "Season");
		assertEquals(result.getDescription(), "household preview");
		
		assertEquals(result.getAggregateMeterKitchen().get("SPRING"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("AUTUMN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("WINTER"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("SUMMER"), 0.0 ,2);
		
		assertEquals(result.getAggregateMeterLaundry().get("SPRING"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("AUTUMN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("WINTER"), 426.0 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("SUMMER"), 0.0 ,2);
		
		assertEquals(result.getAggregateMeterAC().get("SPRING"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("AUTUMN"), 0.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("WINTER"), 1669.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("SUMMER"), 0.0 ,2);
	}

}
