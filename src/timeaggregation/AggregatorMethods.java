package timeaggregation;

import java.util.ArrayList;
import java.util.HashMap;

import datamodel.MeasurementRecord;

public class AggregatorMethods {

	
	
	/* Main method responsible for the actual calculation of the statistics of one meter of the given time unit
	 * as well as for storing them in appropriate collections
	 * Returns 0 if the calculation and the storing completes successfully, 1 otherwise
	 */
	public static int calculateStatistics(String aggFunction, String timeUnit, String meter, HashMap<String, ArrayList<MeasurementRecord>> groupedMeasurements,
			HashMap<String, Double> aggregatedCollection) {
		
		double sum;
		sum = calculateSum(timeUnit, meter, groupedMeasurements);
		
		if (aggFunction.equals("avg")) {
			
			double avg;
			avg = calculateAvg(timeUnit, sum, groupedMeasurements);
			aggregatedCollection.put(timeUnit, avg);
			
			return 0;
		}
		else if (aggFunction.equals("sum")) {
			
			aggregatedCollection.put(timeUnit, sum);
			
			return 0;
		}
		return 1;
	}
	
	
	
	/* Calculates the average of one meter of a month, checks exception month has no measurements
	 * returns the average or 0.0 if the given month has no measurements
	 */
	public static double calculateAvg(String timeUnit, double sum, HashMap<String, ArrayList<MeasurementRecord>> groupedMeasurements) {
		
		double avg = 0.0;
		
		if (groupedMeasurements.containsKey(timeUnit)) {
			avg = sum / groupedMeasurements.get(timeUnit).size();
		}
		return avg;
	}
	
	
	
	/* Calculates the sum of one meter for the given time unit, checks exception time unit has not measurements
	 * returns the sum or 0.0 if the given time unit has no measurements
	 */
	public static double calculateSum(String timeUnit, String meter, HashMap<String, ArrayList<MeasurementRecord>> groupedMeasurements) {
		
		double sum = 0.0;
		if (groupedMeasurements.containsKey(timeUnit)) {
			
			for (int i=0; i < groupedMeasurements.get(timeUnit).size(); i++) {
				
				if (meter.equals("kitchen")) {
					sum = sum + groupedMeasurements.get(timeUnit).get(i).getKitchen();
				}
				else if (meter.equals("laundry")) {
					sum = sum + groupedMeasurements.get(timeUnit).get(i).getLaundry();
				}
				else if (meter.equals("AC")) {
					
					sum = sum + groupedMeasurements.get(timeUnit).get(i).getAC();
				}
				
			}
		}
			return sum;
	}
}
