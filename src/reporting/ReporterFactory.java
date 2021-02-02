package reporting;

public class ReporterFactory {
	
	public IResultReporter createReporter(String reportType) {
		
		if (reportType.equals("txt")) {
			return new TextReporter();
		}
		else if (reportType.equals("html")) {
			return new HtmlReporter();
		}
		else if (reportType.equals("md")) {
			return new MDReporter();
		}
		else {
			return null;
		}
	}
}
