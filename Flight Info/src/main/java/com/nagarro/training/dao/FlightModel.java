/**
 * @class  FlightModel
 * @author chahat
 * @description FlightModel class which maps the flight data from the csv files
 * to the database.
 *
 */
package com.nagarro.training.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight_details")
public class FlightModel implements Comparable<FlightModel> {
	/**
	 * Declared FLIGHT_ID as the primary key for the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FLIGHT_ID;

	@Column(name = "FLIGHT_NO")
	private String flightNO;

	@Column(name = "DEP_LOC")
	private String departure;

	@Column(name = "ARR_LOC")
	private String arrival;

	@Column(name = "VALID_TILL")
	private String validTill;

	@Column(name = "FLIGHT_TIME")
	private String flightTime;

	@Column(name = "FLIGHT_DUR")
	private double flightDuration;

	@Column(name = "FARE")
	private double fare;

	@Column(name = "SEAT_AVAILABILITY")
	private boolean availability;

	@Column(name = "CLASS")
	private String flightClass;

	public FlightModel() {

	}

	/**
	 * FLight Model Constructor.
	 *
	 * @param flightNO
	 * @param departure
	 * @param arrival
	 * @param validTill
	 * @param flightTime
	 * @param flightDuration
	 * @param fare
	 * @param availability
	 * @param flightClass
	 */
	public FlightModel(String flightNO, String departure, String arrival, String validTill, String flightTime,
			double flightDuration, double fare, boolean availability, String flightClass) {
		this.flightNO = flightNO;
		this.departure = departure;
		this.arrival = arrival;
		this.validTill = validTill;
		this.flightTime = flightTime;
		this.flightDuration = flightDuration;
		this.fare = fare;
		this.availability = availability;
		this.flightClass = flightClass;
	}

	/**
	 * Method to concat all the flight details to output on the console.
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15.2f|%-15.2f|%-15s|", flightNO, departure, arrival,
				validTill, flightTime, flightDuration, fare, flightClass);
	}

	/**
	 * Overrided the Comparable class's compareTo method for sorting the flights
	 * based on output preference.
	 *
	 * @param flightDescription
	 * @return
	 */
	@Override
	public int compareTo(FlightModel flightDescription) {
		int comp = Double.compare(this.fare, flightDescription.fare);
		if (comp == 0)
			return Double.compare(this.flightDuration, flightDescription.flightDuration);
		return comp;
	}

	public int getFLIGHT_ID() {
		return FLIGHT_ID;
	}

	public void setFLIGHT_ID(int fLIGHT_ID) {
		FLIGHT_ID = fLIGHT_ID;
	}

	public String getFlightNO() {
		return flightNO;
	}

	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public double getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(double flightDuration) {
		this.flightDuration = flightDuration;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	/**
	 * Getters for all the flight model attributes.
	 *
	 * @return
	 */

}
