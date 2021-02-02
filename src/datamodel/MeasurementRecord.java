package datamodel;

import java.time.LocalDate;
import java.time.LocalTime;

public class MeasurementRecord {
	
	
	private LocalDate day;
	private LocalTime time;
	private double kitchen;
	private double laundry;
	private double AC;
	
	
	public void setDay(LocalDate dayMeasurement) {
		this.day = dayMeasurement;
	}
	public void setTime(LocalTime timeMeasurement) {
		this.time = timeMeasurement;
	}
	public void setKitchen(double kitchenMeasurement) {
		this.kitchen = kitchenMeasurement;
	}
	public void setLaundry(double laundryMeasurement) {
		this.laundry = laundryMeasurement;
	}
	public void setAC(double ACMeasurement) {
		this.AC = ACMeasurement;
	}
	public LocalDate getDay() {
		return day;
	}
	public LocalTime getTime() {
		return time;
	}
	public double getKitchen() {
		return kitchen;
	}
	public double getLaundry() {
		return laundry;
	}
	public double getAC() {
		return AC;
	}
}
