package datamodel;

public class ResultFactory {
	
	public IResult createResult(String timeUnit) {
		
		if (timeUnit.equals("month")) {
			return new MonthResult();
		}
		else if (timeUnit.equals("season")) {
			return new SeasonResult();
		}
		else if (timeUnit.equals("week-day")) {
			return new WeekDayResult();
		}
		else if (timeUnit.equals("period of day")) {
			return new PeriodOfDayResult();
		}
		else {
			return null;
		}
	}

}
