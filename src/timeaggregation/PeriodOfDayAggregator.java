package timeaggregation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.ResultFactory;

public class PeriodOfDayAggregator implements IAggregator {

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		
		
		ResultFactory resultFactory = new ResultFactory();
		IResult result = resultFactory.createResult("period of day");
		
		result.setDescription(description);
		result.setAggregateFunction(aggFunction);
		
		LocalTime time;
		int hour;
		String dayPeriod;
		HashMap<String, ArrayList<MeasurementRecord>> groupedMeasurements = new HashMap<>();
		HashMap<String, Double> aggregatedKitchen = new HashMap<>();
		HashMap<String, Double> aggregatedLaundry = new HashMap<>();
		HashMap<String, Double> aggregatedAC = new HashMap<>();
		
		
		int measurementGroupFlag = 0;
		for (int i=0; i < inputMeasurements.size(); i++) {
			
			
			// Find the hour of the current measurements
			time = inputMeasurements.get(i).getTime();
			hour = time.getHour();
			
			// Find the day period of the current measurements
			if (hour >= 5 && hour < 9) {
				dayPeriod = "EARLY_MORNING";
			}
			else if (hour >= 9 && hour < 13) {
				dayPeriod = "MORNING";
			}
			else if (hour >= 13 && hour < 17) {
				dayPeriod = "AFTERNOUN";
			}
			else if (hour >= 17 && hour < 21) {
				dayPeriod = "EVENING";
			}
			else if ( hour >= 21 || hour < 5) {
				dayPeriod = "NIGHT";
			}
			else {
				System.out.println("\n" + "Something went wrong with the data aggregation");
				return null;
			}
			
			// Group the measurements based on their day period
			measurementGroupFlag = result.add(dayPeriod, inputMeasurements.get(i));
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
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "EARLY_MORNING", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "EARLY_MORNING", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "EARLY_MORNING", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MORNING", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MORNING", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MORNING", "AC", groupedMeasurements, aggregatedAC);

		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AFTERNOUN", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AFTERNOUN", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AFTERNOUN", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "EVENING", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "EVENING", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "EVENING", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "NIGHT", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "NIGHT", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "NIGHT", "AC", groupedMeasurements, aggregatedAC);
		
		if (statCalcErrorCode != 0) {
			System.out.println("\n" + "Something went wrong with the data aggregation");
			return null;
		}
		
		System.out.println("\n" + "Statistics successfully generated!");
		return result;
	}

	@Override
	public String getTimeUnitType() {
		return "period of day";
	}

}
