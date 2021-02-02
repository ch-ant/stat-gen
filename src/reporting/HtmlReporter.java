package reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import datamodel.IResult;

public class HtmlReporter implements IResultReporter {

	@Override
	public int reportResultInFile(IResult result, String filename) {
		
		File reportFile = new File(filename + ".html");
		String key;
		Double value;
		
		try {
			
			reportFile.createNewFile();
			
			FileWriter writer = new FileWriter(reportFile);
			BufferedWriter buffWriter = new BufferedWriter(writer);
			
			buffWriter.write("<html>");
			buffWriter.newLine();
			buffWriter.write("<head>");
			buffWriter.newLine();
			buffWriter.write("</head>");
			buffWriter.newLine();
			buffWriter.write("<body>");
			buffWriter.newLine();
			buffWriter.write("<h1> =====  " + result.getTimeUnitType() + " " + result.getAggregateFunction() + " aggregation  ===== </h1>");
			buffWriter.newLine();
			buffWriter.write("<p>" + result.getAggregateFunction() + " consumption over (a) Kitchen, (b) Laundry, (c) A/C </p>");
			buffWriter.newLine();
			buffWriter.write("<p> Description: " + result.getDescription() + "</p>");
			buffWriter.newLine();
			buffWriter.newLine();
			buffWriter.write("<h2> Kitchen </h2>");
			
			for (Map.Entry mapElement : result.getAggregateMeterKitchen().entrySet()) {
				
				buffWriter.newLine();
				key = (String)mapElement.getKey();
				value = (Double)mapElement.getValue();
				buffWriter.write("<li>" + key + "   " + value.toString() + "</li>");
			}
			
			buffWriter.newLine();
			buffWriter.write("<h2> Laundry </h2>");
			
			for (Iterator<Entry<String, Double>> iterator = result.getAggregateMeterLaundry().entrySet().iterator(); iterator.hasNext();) {
				
				Entry<String, Double> mapElement = iterator.next();
				
				buffWriter.newLine();
				key = (String)mapElement.getKey();
				value = (Double)mapElement.getValue();
				buffWriter.write("<li>" + key + "   " + value.toString() + "</li>");
			}
			
			buffWriter.newLine();
			buffWriter.write("<h2> A/C </h2>");
			
			for (Map.Entry mapElement : result.getAggregateMeterAC().entrySet()) {
				
				buffWriter.newLine();
				key = (String)mapElement.getKey();
				value = (Double)mapElement.getValue();
				buffWriter.write("<li>" + key + "   " + value.toString() + "</li>");
				
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
