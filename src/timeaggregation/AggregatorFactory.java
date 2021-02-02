package timeaggregation;

public class AggregatorFactory {
	
	public IAggregator createAggregator(String timeUnit) {
		
	
		if (timeUnit.equals("season")) {
			return new SeasonAggregator();
		}
		else if (timeUnit.equals("month")) {
			return new MonthAggregator();
		}
		else if (timeUnit.equals("week-day")) {
			return new WeekDayAggregator();
		}
		else if (timeUnit.equals("period of day")) {
			return new PeriodOfDayAggregator();
		}
		else {
			return null;
		}
	}

}
