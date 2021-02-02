package timeaggregation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.ResultFactory;

public class MonthAggregator implements IAggregator {

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		
		
		ResultFactory resultFactory = new ResultFactory();
		IResult result = resultFactory.createResult("month");
		
		result.setDescription(description);
		result.setAggregateFunction(aggFunction);
		
		
		LocalDate day;
		String month;
		HashMap<String, ArrayList<MeasurementRecord>> groupedMeasurements = new HashMap<>();
		HashMap<String, Double> aggregatedKitchen = new HashMap<>();
		HashMap<String, Double> aggregatedLaundry = new HashMap<>();
		HashMap<String, Double> aggregatedAC = new HashMap<>();
		
		
		int measurementGroupFlag = 0;
		for (int i=0; i < inputMeasurements.size(); i++) {
			
			
			// Find the month of the current measurements,convert it to a String and keep the first 3 characters
			day = inputMeasurements.get(i).getDay();
			month = day.getMonth().toString();
			month = month.substring(0,3);
			
			// Group the measurements based on their month
			measurementGroupFlag = result.add(month, inputMeasurements.get(i));
			
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
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JAN", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JAN", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JAN", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "FEB", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "FEB", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "FEB", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MAR", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MAR", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MAR", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "APR", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "APR", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "APR", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MAY", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MAY", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "MAY", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JUN", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JUN", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JUN", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JUL", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JUL", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "JUL", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AUG", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AUG", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "AUG", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SEP", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SEP", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "SEP", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "OCT", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "OCT", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "OCT", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "NOV", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "NOV", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "NOV", "AC", groupedMeasurements, aggregatedAC);
		
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "DEC", "kitchen", groupedMeasurements, aggregatedKitchen);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "DEC", "laundry", groupedMeasurements, aggregatedLaundry);
		statCalcErrorCode += AggregatorMethods.calculateStatistics(aggFunction, "DEC", "AC", groupedMeasurements, aggregatedAC);
		
		if (statCalcErrorCode != 0) {
			System.out.println("\n" + "Something went wrong with the data aggregation");
			return null;
		}
		
		
		System.out.println("\n" + "Statistics successfully generated!");
		return result;
	}

	

	@Override
	public String getTimeUnitType() {
		return "month";
	}

}
