package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class PeriodOfDayResult implements IResult {
	
	
	private String description;
	private String aggregateFunction;
	
	private HashMap<String, ArrayList<MeasurementRecord>> detailedResults = new HashMap<>();
	private HashMap<String, Double> aggregateMeterKitchen = new HashMap<>();
	private HashMap<String, Double> aggregateMeterLaundry = new HashMap<>();
	private HashMap<String, Double> aggregateMeterAC = new HashMap<>();
	
	private ArrayList<MeasurementRecord> EARLY_MORNING = new ArrayList<>();
	private ArrayList<MeasurementRecord> MORNING = new ArrayList<>();
	private ArrayList<MeasurementRecord> AFTERNOUN = new ArrayList<>();
	private ArrayList<MeasurementRecord> EVENING = new ArrayList<>();
	private ArrayList<MeasurementRecord> NIGHT = new ArrayList<>();
	
	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		
		
		if (timeUnit.equals("EARLY_MORNING")) {
			EARLY_MORNING.add(record);
			detailedResults.put("EARLY_MORNING", EARLY_MORNING);
		}
		else if (timeUnit.equals("MORNING")) {
			MORNING.add(record);
			detailedResults.put("MORNING", MORNING);
		}
		else if (timeUnit.equals("AFTERNOUN")) {
			AFTERNOUN.add(record);
			detailedResults.put("AFTERNOUN", AFTERNOUN);
		}
		else if (timeUnit.equals("EVENING")) {
			EVENING.add(record);
			detailedResults.put("EVENING", EVENING);
		}
		else if (timeUnit.equals("NIGHT")) {
			NIGHT.add(record);
			detailedResults.put("NIGHT", NIGHT);
		}
		return detailedResults.size();
	}

	
	public String getTimeUnitType() {
		return "Period of day";
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {
		return this.detailedResults;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterKitchen() {
		return this.aggregateMeterKitchen;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {
		return this.aggregateMeterLaundry;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterAC() {
		return this.aggregateMeterAC;
	}

	@Override
	public String getAggregateFunction() {
		return aggregateFunction;
	}

	@Override
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	@Override
	public void setAggregateFunction(String newAggFunction) {
		this.aggregateFunction = newAggFunction;
	}

}
