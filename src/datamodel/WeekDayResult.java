package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class WeekDayResult implements IResult {
	
	
	private String description;
	private String aggregateFunction;
	
	private HashMap<String, ArrayList<MeasurementRecord>> detailedResults = new HashMap<>();
	private HashMap<String, Double> aggregateMeterKitchen = new HashMap<>();
	private HashMap<String, Double> aggregateMeterLaundry = new HashMap<>();
	private HashMap<String, Double> aggregateMeterAC = new HashMap<>();
	
	private ArrayList<MeasurementRecord> MON = new ArrayList<>();
	private ArrayList<MeasurementRecord> TUE = new ArrayList<>();
	private ArrayList<MeasurementRecord> WED = new ArrayList<>();
	private ArrayList<MeasurementRecord> THU = new ArrayList<>();
	private ArrayList<MeasurementRecord> FRI = new ArrayList<>();
	private ArrayList<MeasurementRecord> SAT = new ArrayList<>();
	private ArrayList<MeasurementRecord> SUN = new ArrayList<>();

	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		
		
		if (timeUnit.equals("MON")) {
			MON.add(record);
			detailedResults.put("MON", MON);
		}
		else if (timeUnit.equals("TUE")) {
			TUE.add(record);
			detailedResults.put("TUE", TUE);
		}
		else if (timeUnit.equals("WED")) {
			WED.add(record);
			detailedResults.put("WED", WED);
		}
		else if (timeUnit.equals("THU")) {
			THU.add(record);
			detailedResults.put("THU", THU);
		}
		else if (timeUnit.equals("FRI")) {
			FRI.add(record);
			detailedResults.put("FRI", FRI);
		}
		else if (timeUnit.equals("SAT")) {
			SAT.add(record);
			detailedResults.put("SAT", SAT);
		}
		else if (timeUnit.equals("SUN")) {
			SUN.add(record);
			detailedResults.put("SUN", SUN);
		}
		return detailedResults.size();
	}

	
	public String getTimeUnitType() {
		return "Week-day";
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
