package datamodel;

import java.time.LocalDate;
import java.time.LocalTime;

public class MeasurementRecordFactory {

	public MeasurementRecord createMeasurementRecord(LocalDate day, LocalTime time, double kitchen, double laundry, double AC) {
		
		MeasurementRecord m = new MeasurementRecord();
		m.setDay(day);
		m.setTime(time);
		m.setKitchen(kitchen);
		m.setLaundry(laundry);
		m.setAC(AC);
		return m;
	}

}
