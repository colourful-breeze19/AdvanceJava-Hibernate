package com.nagarro.training.input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import com.nagarro.training.constant.Constants;
import com.nagarro.training.constant.OutputPreference;

public class InputValidations {
	/**
	 * Declared scanner class for the user input.
	 */
	static Scanner scan = new Scanner(System.in);

	/**
	 * Takes input from user and validates it for the CountryCode.
	 *
	 * @return
	 */
	public static String departureLocationInput() {
		String location = "";
		while (true) {
			System.out.print("Enter departure location-> ");
			location = scan.nextLine().toUpperCase();
			try {
				if (location == null || location.isEmpty())  {
					throw new Exception(Constants.EMPTY_LOC);
				} else if (location.length() != 3) {
					throw new Exception(Constants.LOC_CODE_ERROR);
				}
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return location;
	}

	/**
	 * Takes input from user and validates it for the CountryCode.
	 *
	 * @return
	 */
	public static String arrivalLocationInput() {
		String location = "";
		while (true) {
			System.out.print("Enter arrival location->");
			location = scan.nextLine().toUpperCase();
			try {
				if (location == null || location.isEmpty())  {
					throw new Exception(Constants.EMPTY_LOC);
				} else if (location.length() != 3) {
					throw new Exception(Constants.LOC_CODE_ERROR);
				}
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return location;
	}

	/**
	 * Takes input from user and validates it for the Date.
	 *
	 * @return
	 */
	public String flightDateInput() {
		LocalDate flightDate;
		String flightDateStr;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		while (true) {
			System.out.print("Enter travelling date-> ");
			flightDateStr = scan.nextLine();
			try {
				flightDate = LocalDate.parse(flightDateStr, formatter);
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format.[Required: DD-MM-YYY]");
			}
		}
		return flightDateStr;
	}

	/**
	 * Takes input from user and validates it for the Flight class.
	 *
	 * @return
	 */
	public static String flightClassInput() {
		String flightClass;
		while (true) {
			System.out.print("Enter flight class->  \nEB for Business E for Economic ");
			try {
				flightClass = scan.nextLine().toUpperCase();
				if (!flightClass.equals(Constants.FLIGHT_BUSINESS) && !flightClass.equals(Constants.FLIGHT_ECONOMY)) {
					throw new Exception(Constants.INVALID_CHOICE);
				}
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return flightClass;
	}

	/**
	 * Takes input from user and validates it for the choice of output preference.
	 *
	 * @return
	 */
	public OutputPreference outputPreferenceInput() {
		OutputPreference outputPreference;
		while (true) {
			System.out.print(
					"Enter Output Preference->  \nA - Fare \t B - Flight Duration ");
			String outputPreferenceInput = scan.nextLine();
			outputPreferenceInput = outputPreferenceInput.toUpperCase();
			try {
				outputPreference = OutputPreference.valueOf(outputPreferenceInput);
				break;
			} catch (IllegalArgumentException exception) {
				System.out.println(Constants.INVALID_CHOICE);
			}
		}
		return outputPreference;
	}
}
