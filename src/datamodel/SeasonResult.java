package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class SeasonResult implements IResult {
	
	
	private String description;
	private String aggregateFunction;
	
	private HashMap<String, ArrayList<MeasurementRecord>> detailedResults = new HashMap<>();
	private HashMap<String, Double> aggregateMeterKitchen = new HashMap<>();
	private HashMap<String, Double> aggregateMeterLaundry = new HashMap<>();
	private HashMap<String, Double> aggregateMeterAC = new HashMap<>();
	
	private ArrayList<MeasurementRecord> WINTER = new ArrayList<>();
	private ArrayList<MeasurementRecord> SPRING = new ArrayList<>();
	private ArrayList<MeasurementRecord> SUMMER = new ArrayList<>();
	private ArrayList<MeasurementRecord> AUTUMN = new ArrayList<>();

	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		
		
		if (timeUnit.equals("WINTER")) {
			WINTER.add(record);
			detailedResults.put("WINTER", WINTER);
		}
		else if (timeUnit.equals("SPRING")) {
			SPRING.add(record);
			detailedResults.put("SPRING", SPRING);
		}
		else if (timeUnit.equals("SUMMER")) {
			SUMMER.add(record);
			detailedResults.put("SUMMER", SUMMER);
		}
		else if (timeUnit.equals("AUTUMN")) {
			AUTUMN.add(record);
			detailedResults.put("AUTUMN", AUTUMN);
		}
		return detailedResults.size();
	}

	
	public String getTimeUnitType() {
		return "Season";
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
