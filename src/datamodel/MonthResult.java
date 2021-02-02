package datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthResult implements IResult {
	
	
	private String description;
	private String aggregateFunction;
	
	private HashMap<String, ArrayList<MeasurementRecord>> detailedResults = new HashMap<>();
	private HashMap<String, Double> aggregateMeterKitchen = new HashMap<>();
	private HashMap<String, Double> aggregateMeterLaundry = new HashMap<>();
	private HashMap<String, Double> aggregateMeterAC = new HashMap<>();
	
	private ArrayList<MeasurementRecord> JAN = new ArrayList<>();
	private ArrayList<MeasurementRecord> FEB = new ArrayList<>();
	private ArrayList<MeasurementRecord> MAR = new ArrayList<>();
	private ArrayList<MeasurementRecord> APR = new ArrayList<>();
	private ArrayList<MeasurementRecord> MAY = new ArrayList<>();
	private ArrayList<MeasurementRecord> JUN = new ArrayList<>();
	private ArrayList<MeasurementRecord> JUL = new ArrayList<>();
	private ArrayList<MeasurementRecord> AUG = new ArrayList<>();
	private ArrayList<MeasurementRecord> SEP = new ArrayList<>();
	private ArrayList<MeasurementRecord> OCT = new ArrayList<>();
	private ArrayList<MeasurementRecord> NOV = new ArrayList<>();
	private ArrayList<MeasurementRecord> DEC = new ArrayList<>();
	
	
	
	// Adds a new measurement to the result, appropriately placed
	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		

		if (timeUnit.equals("JAN")) {
			JAN.add(record);
			detailedResults.put("JAN", JAN);
		}
		else if (timeUnit.equals("FEB")) {
			FEB.add(record);
			detailedResults.put("FEB", FEB);
		}
		else if (timeUnit.equals("MAR")) {
			MAR.add(record);
			detailedResults.put("MAR", MAR);
		}
		else if (timeUnit.equals("APR")) {
			APR.add(record);
			detailedResults.put("APR", APR);
		}
		else if (timeUnit.equals("MAY")) {
			MAY.add(record);
			detailedResults.put("MAY", MAY);
		}
		else if (timeUnit.equals("JUN")) {
			JUN.add(record);
			detailedResults.put("JUN", JUN);
		}
		else if (timeUnit.equals("JUL")) {
			JUL.add(record);
			detailedResults.put("JUL", JUL);
		}
		else if (timeUnit.equals("AUG")) {
			AUG.add(record);
			detailedResults.put("AUG", AUG);
		}
		else if (timeUnit.equals("SEP")) {
			SEP.add(record);
			detailedResults.put("SEP", SEP);
		}
		else if (timeUnit.equals("OCT")) {
			OCT.add(record);
			detailedResults.put("OCT", OCT);
		}
		else if (timeUnit.equals("NOV")) {
			NOV.add(record);
			detailedResults.put("NOV", NOV);
		}
		else if (timeUnit.equals("DEC")) {
			DEC.add(record);
			detailedResults.put("DEC", DEC);
		}
		return detailedResults.size();
	}

	
	public String getTimeUnitType() {
		return "Month";
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription(String newDescription) {
		this.description = newDescription;
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
	public void setAggregateFunction(String newAggFunction) {
		this.aggregateFunction = newAggFunction;
	}

}
