package mainengine;

import java.util.ArrayList;
import dataload.ILoader;
import dataload.LoaderFactory;
import datamodel.IResult;
import reporting.IResultReporter;
import reporting.ReporterFactory;
import timeaggregation.IAggregator;
import timeaggregation.AggregatorFactory;
import datamodel.MeasurementRecord;

public class MainEngine implements IMainEngine {
	
	
	ArrayList<MeasurementRecord> measurementRecordList = new ArrayList<>();
	LoaderFactory<MeasurementRecord> loaderFactory = new LoaderFactory<MeasurementRecord>();
	ILoader<MeasurementRecord> dataLoader = loaderFactory.createLoader("Loader");
	
	
	public int loadData(String fileName, String delimeter, Boolean hasHeaderLine, int numFields) {
		
		// Clear the measurement record list in case it was loaded from a previous load attempt
		if (!measurementRecordList.isEmpty()) {
			measurementRecordList.clear();
		}
		
		return dataLoader.load(fileName, delimeter, hasHeaderLine, numFields, measurementRecordList);
	}
	


	public IResult aggregateByTimeUnit(String aggregatorType, String aggFunction, String description) {
		
		AggregatorFactory aggregatorFactory = new AggregatorFactory();
		IAggregator timeAggregator = aggregatorFactory.createAggregator(aggregatorType);
		
		return timeAggregator.aggregateByTimeUnit(measurementRecordList, aggFunction, description);
	}

	
	public int reportResultInFile(IResult result, String reportType, String filename) {
		
		ReporterFactory reporterFactory = new ReporterFactory();
		IResultReporter resultReporter = reporterFactory.createReporter(reportType);
		
		return resultReporter.reportResultInFile(result, filename);
	}
	
}