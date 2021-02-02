package reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import datamodel.IResult;

public class MDReporter implements IResultReporter {

	@Override
	public int reportResultInFile(IResult result, String filename) {
		
		File reportFile = new File(filename + ".md");
		String key;
		Double value;
		
		try {
			
			reportFile.createNewFile();
			
			FileWriter writer = new FileWriter(reportFile);
			BufferedWriter buffWriter = new BufferedWriter(writer);
			
			buffWriter.write("# " + result.getTimeUnitType() + " " + result.getAggregateFunction() + " aggregation");
			buffWriter.newLine();
			buffWriter.write(result.getAggregateFunction() + " consumption over (a) Kitchen, (b) Laundry, (c) A/C");
			buffWriter.newLine();
			buffWriter.write("Description: " + result.getDescription());
			buffWriter.newLine();
			buffWriter.newLine();
			buffWriter.write("## Kitchen");
			
			for (Map.Entry mapElement : result.getAggregateMeterKitchen().entrySet()) {
				
				buffWriter.newLine();
				key = (String)mapElement.getKey();
				value = (Double)mapElement.getValue();
				buffWriter.write("* " + key + "    " + value.toString());
			}
			
			buffWriter.newLine();
			buffWriter.write("## Laundry");
			
			for (Iterator<Entry<String, Double>> iterator = result.getAggregateMeterLaundry().entrySet().iterator(); iterator.hasNext();) {
				
				Entry<String, Double> mapElement = iterator.next();
				
				buffWriter.newLine();
				key = (String)mapElement.getKey();
				value = (Double)mapElement.getValue();
				buffWriter.write("* " + key + "    " + value.toString());
			}
			
			buffWriter.newLine();
			buffWriter.write("## A/C");
			
			for (Map.Entry mapElement : result.getAggregateMeterAC().entrySet()) {
				
				buffWriter.newLine();
				key = (String)mapElement.getKey();
				value = (Double)mapElement.getValue();
				buffWriter.write("* " + key + "    " + value.toString());
			}
			
			buffWriter.close();
			System.out.println("Report created!");
			return 0;
			
		} catch (IOException e) {
			
			System.out.println("Report creation failed! Please try again.");
			return -1;
		}

	}

}
