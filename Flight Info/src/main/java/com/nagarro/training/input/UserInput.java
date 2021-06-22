/**
 * @class  UserInput
 * @author chahat
 * @description UserInput class manages user input, input validation and updates the database
 * periodically for any changes.
 *
 */
package com.nagarro.training.input;

import java.util.Timer;
import java.util.TimerTask;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import com.nagarro.training.constant.OutputPreference;
import com.nagarro.training.updatedata.FlightView;
import com.nagarro.training.updatedata.UpdateDatabase;


public class UserInput {
	/**
	 * Variables to store the user inputs.
	 */
	private String departureLocation;
	private String arrivalLocation;
	private String flightDate;
	private String flightClass;
	private OutputPreference outputPreference;

	public void operations() {
		inputAndValidate();

		/**
		 * Time task to periodically update the database.
		 */
		Timer timer = new Timer();
		class MyTask extends TimerTask {
			int task = 10;

			@Override
			public void run() {
				task -= 1;

				Configuration configuration = new Configuration();
				configuration.configure("hibernate.cfg.xml");
				configuration.addClass(com.nagarro.training.dao.FlightModel.class);
				configuration.addResource("FlightModel.hbm.xml");

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				UpdateDatabase updateDatabase = new UpdateDatabase();
				updateDatabase.getFlight(sessionFactory);
				if (task < 0) {
					timer.cancel();
				} else {
					FlightView flightView = new FlightView();
					flightView.printOutput(departureLocation, arrivalLocation, flightDate, flightClass,
							outputPreference, sessionFactory);
				}

			}
		}

		/**
		 * Initialized the MyTask class and scheduled it to run periodically.
		 */
		TimerTask t = new MyTask();
		timer.schedule(t, 0, 30000);
	}

	/**
	 * Method to validate the user inputs.
	 */
	private void inputAndValidate() {
		InputValidations inputValidate = new InputValidations();
		departureLocation = InputValidations.departureLocationInput();
		arrivalLocation = InputValidations.arrivalLocationInput();
		flightDate = inputValidate.flightDateInput();
		flightClass = InputValidations.flightClassInput();
		outputPreference = inputValidate.outputPreferenceInput();
	}
}
