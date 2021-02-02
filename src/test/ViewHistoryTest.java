package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import client.ReportHistory;

public class ViewHistoryTest {
	
	
	private static ReportHistory reportHistory;
	private static ArrayList<ReportHistory> reportHistoryList;
	
	
	@After
	public void tearDown() throws Exception {
		
		reportHistoryList.clear();
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		reportHistoryList = new ArrayList<>();
		reportHistory = new ReportHistory();
		
		reportHistory.setTimeUnit("season");
		reportHistory.setFunctionType("avg");
		reportHistory.setDescription("desc1");
		reportHistory.setOutputPath("E:\\eclipse-workspace\\stat-gen\\resources\\TestOutput\\report1.txt");
		reportHistory.setReportType("txt");
		reportHistoryList.add(reportHistory);
		
		reportHistory = new ReportHistory();
		
		reportHistory.setTimeUnit("week-day");
		reportHistory.setFunctionType("sum");
		reportHistory.setDescription("desc2");
		reportHistory.setOutputPath("E:\\eclipse-workspace\\stat-gen\\resources\\TestOutput\\report2.html");
		reportHistory.setReportType("html");
		reportHistoryList.add(reportHistory);
		
		reportHistory = new ReportHistory();
		
		reportHistory.setTimeUnit("month");
		reportHistory.setFunctionType("avg");
		reportHistory.setDescription("desc3");
		reportHistory.setOutputPath("E:\\eclipse-workspace\\stat-gen\\resources\\TestOutput\\report3.md");
		reportHistory.setReportType("md");
		reportHistoryList.add(reportHistory);
	}
	
	@Test
	public void testViewHistory() {
		
		assertEquals(reportHistoryList.get(0).getTimeUnit(), "season");
		assertEquals(reportHistoryList.get(0).getFunctionType(), "avg");
		assertEquals(reportHistoryList.get(0).getDescription(), "desc1");
		assertEquals(reportHistoryList.get(0).getOutputPath(), "E:\\eclipse-workspace\\stat-gen\\resources\\TestOutput\\report1.txt");
		assertEquals(reportHistoryList.get(0).getReportType(), "txt");
		
		assertEquals(reportHistoryList.get(1).getTimeUnit(), "week-day");
		assertEquals(reportHistoryList.get(1).getFunctionType(), "sum");
		assertEquals(reportHistoryList.get(1).getDescription(), "desc2");
		assertEquals(reportHistoryList.get(1).getOutputPath(), "E:\\eclipse-workspace\\stat-gen\\resources\\TestOutput\\report2.html");
		assertEquals(reportHistoryList.get(1).getReportType(), "html");
		
		assertEquals(reportHistoryList.get(2).getTimeUnit(), "month");
		assertEquals(reportHistoryList.get(2).getFunctionType(), "avg");
		assertEquals(reportHistoryList.get(2).getDescription(), "desc3");
		assertEquals(reportHistoryList.get(2).getOutputPath(), "E:\\eclipse-workspace\\stat-gen\\resources\\TestOutput\\report3.md");
		assertEquals(reportHistoryList.get(2).getReportType(), "md");
	}

}
