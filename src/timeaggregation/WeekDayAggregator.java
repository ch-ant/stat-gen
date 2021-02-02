package timeaggregation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.ResultFactory;

public class WeekDayAggregator implements IAggregator {

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		
		
		ResultFactory resultFactory = new ResultFactory();
		IResult result = resultFactory.createResult("week-day");
		
		result.setDescription(description);
		result.setAggregateFunction(aggFunction);
		
		LocalDate day;
		String weekDay;
		HashMap<String, ArrayList<MeasurementRecord>> groupedMeasurements = new HashMap<>();
		HashMap<String, Double> aggregatedKitchen = new HashMap<>();
		HashMap<String, Double> aggregatedLaundry = new HashMap<>();
		HashMap<String, Double> aggregatedAC = new HashMap<>();
		
		
		int measurementGroupFlag = 0;
		for (int i=0; i < inputMeasurements.size(); i++) {
			
			// Find the week-day of the current measurements, convert it to a String and keep the first 3 characters
			day = inputMeasurements.get(i).getDay();
			weekDay = day.getDayOfWeek().toString();
			weekDay = weekDay.substring(0,3);
			
			// Group the measurements based on their week-day
			measurementGroupFlag = result.add(weekDay, inputMeasurements.get(i));
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
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MON", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MON", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MON", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "TUE", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "TUE", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "TUE", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "WED", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "WED", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "WED", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "THU", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "THU", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "THU", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "FRI", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "FRI", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "FRI", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SAT", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SAT", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SAT", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SUN", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SUN", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SUN", "AC", groupedMeasurements, aggregatedAC);
		
		if (statCalcErrorCode != 0) {
			System.out.println("\n" + "Something went wrong with the data aggregation");
			return null;
		}
		
		System.out.println("\n" + "Statistics successfully generated!");
		return result;
	}

	@Override
	public String getTimeUnitType() {
		return "week-day";
	}

}
