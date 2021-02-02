package timeaggregation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.ResultFactory;

public class SeasonAggregator implements IAggregator {

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		
		
		ResultFactory resultFactory = new ResultFactory();
		IResult result = resultFactory.createResult("season");
		
		result.setDescription(description);
		result.setAggregateFunction(aggFunction);
		
		LocalDate day;
		String month;
		String season;
		HashMap<String, ArrayList<MeasurementRecord>> groupedMeasurements = new HashMap<>();
		HashMap<String, Double> aggregatedKitchen = new HashMap<>();
		HashMap<String, Double> aggregatedLaundry = new HashMap<>();
		HashMap<String, Double> aggregatedAC = new HashMap<>();
		
		
		int measurementGroupFlag = 0;
		for (int i=0; i < inputMeasurements.size(); i++) {
			
			
			// Find the month of the current measurements
			day = inputMeasurements.get(i).getDay();
			month = day.getMonth().toString();
			
			// Find the season of the current measurements
			if (month.equals("DECEMBER") || month.equals("JANUARY") || month.equals("FEBRUARY")) {
				season = "WINTER";
			}
			else if (month.equals("MARCH") || month.equals("APRIL") || month.equals("MAY")) {
				season = "SPRING";
			}
			else if (month.equals("JUNE") || month.equals("JULY") || month.equals("AUGUST")) {
				season = "SUMMER";
			}
			else if (month.equals("SEPTEMBER") || month.equals("OCTOBER") || month.equals("NOVEMBER")) {
				season = "AUTUMN";
			}
			else {
				System.out.println("\n" + "Something went wrong with the data aggregation");
				return null;
			}
			
			// Group the measurements based on their season
			measurementGroupFlag = result.add(season, inputMeasurements.get(i));
			
		}
		
		if (measurementGroupFlag == 0) {
			System.out.println("\n" + "Something went wrong with the data aggregation");
			return null;
		}
		
		
		// Process the grouped measurements based on the aggregate function and store them in separate collections
		groupedMeasurements = result.getDetailedResults();
		aggregatedKitchen = result.getAggregateMeterKitchen();
		aggregatedLaundry = result.getAggregateMeterLaundry();
		aggregatedAC = result.getAggregateMeterAC();
		
		
		int statCalcErrorCode = 0;
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "WINTER", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "WINTER", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "WINTER", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SPRING", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SPRING", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SPRING", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SUMMER", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SUMMER", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SUMMER", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AUTUMN", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AUTUMN", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AUTUMN", "AC", groupedMeasurements, aggregatedAC);
		
		if (statCalcErrorCode != 0) {
			System.out.println("\n" + "Something went wrong with the data aggregation");
			return null;
		}
		
		System.out.println("\n" + "Statistics successfully generated!");
		return result;
	}
	
	
	
	@Override
	public String getTimeUnitType() {
		return "season";
	}

}
