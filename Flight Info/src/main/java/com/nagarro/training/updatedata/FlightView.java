/**
 * @class  FlightView
 * @author chahat
 * @description FlightView class manages the flight search result's console output to the user.
 *
 */
package com.nagarro.training.updatedata;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nagarro.training.constant.OutputPreference;

public class FlightView {
	public void printOutput(String departure, String arrival, String flightDate, String flightClass,
			OutputPreference outputPreference, SessionFactory sessionFactory) {
		/**
		 * uses the sessionFactory passed by the main class.
		 */
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		/**
		 *custom query to get the flight data for the user inputs. Passed the
		 * user parameters in the query using the setParameter() method.
		 */
		String userQuery = "FROM FlightModel  WHERE departure = :departure AND arrival = :arrival "
				+ "AND validTill >= :flightDate AND flightClass LIKE :flightClass AND availability = :availability ";

		switch (outputPreference) {
		case A:
			userQuery += "GROUP BY flightNO ORDER BY fare";
			break;
		case B:
			userQuery += "GROUP BY flightNO ORDER BY flightDuration";
			break;
		}

		Query query = session.createQuery(userQuery);
		query.setParameter("departure", departure);
		query.setParameter("arrival", arrival);
		query.setParameter("flightDate", flightDate);
		query.setParameter("flightClass", "%" + flightClass + "%");
		query.setParameter("availability", true);
		List<?> list = query.getResultList();

		/**
		 * Print the flight data in a tabular form on the console.
		 */
		System.out.println(
				"****************************************************AVAILABLE FLIGHTS****************************************************\n");
		System.out.println(
				"|Flight Number  |Departure      |Arrival        |Valid Till     |Flight Time    |Flight Duration|Flight Fare    |Flight Class   |\n"
						+ "|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|");
		
		for(Object obj:list) {
			
			System.out.println(obj);
			
		}
		System.out.println();
		session.getTransaction().commit();
		session.close();
	}
}
