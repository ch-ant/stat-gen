package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import client.IUserInterface;
import client.UIFactory;
import dataload.ILoader;
import dataload.LoaderFactory;
import datamodel.MeasurementRecord;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;

public class DataLoadTest {
	
	
	private static MainEngineFactory factory;
	private static IMainEngine mainEngine;
	private static ArrayList<MeasurementRecord> objCollection;
	static private String inputFilename;
	private static UIFactory uiFactory;
	private static IUserInterface userInterface;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		uiFactory = new UIFactory();
		userInterface = uiFactory.createUI("UserInterface");
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		objCollection = new ArrayList<MeasurementRecord>();
		
		// ! MUST CHANGE for test to run successfully
		inputFilename = "E:\\eclipse-workspace\\stat-gen\\resources\\TestInput\\household_preview.txt";
		
	}
	
	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<MeasurementRecord>(); 
	}
	
	@Test
	public void testHeaderLine() {
		boolean hasHeaderLine = userInterface.checkForHeaderLine(inputFilename);
		assertEquals(hasHeaderLine, true);
	}

	@Test
	public void testDelimeter() {
		
		boolean hasHeaderLine = true;
		String delimeter = userInterface.findDelimeter(inputFilename, hasHeaderLine);
		assertEquals(delimeter, ";");
	}
	
	@Test
	public void testNumFields() {
		
		boolean hasHeaderLine = true;
		String delimeter = ";";
		int numFields = userInterface.findNumFields(inputFilename, delimeter, hasHeaderLine);
		assertEquals(numFields, 9);
	}
	
	@Test
	public void testLoader() {
		
		boolean hasHeaderLine = true;
		String delimeter = ";";
		int numFields = 9;
		
		LoaderFactory<MeasurementRecord> loaderFactory = new LoaderFactory<MeasurementRecord>();
		ILoader<MeasurementRecord> dataLoader = loaderFactory.createLoader("Loader");
		
		int lineCount = dataLoader.load(inputFilename, delimeter, hasHeaderLine, numFields, objCollection);
		assertEquals(lineCount, 99); // title excluded
	}
	
	@Test
	public void testLoaderFromMain() {
		
		boolean hasHeaderLine = true;
		String delimeter = ";";
		int numFields = 9;
		
		int lineCount = mainEngine.loadData(inputFilename, delimeter, hasHeaderLine, numFields);
		assertEquals(lineCount, 99); // title excluded
	}
}
