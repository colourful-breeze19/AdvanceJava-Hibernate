/**
 * @class  UpdateDBThread
 * @author chahat
 * @description UpdateDBThread class implements the runnable interface to update each CSV file's data
 * using threads.
 *
 */
package com.nagarro.training.updatedata;

import java.io.File;
import java.io.FileReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nagarro.training.dao.FlightModel;
import com.opencsv.CSVReader;

public class UpdateDBThread implements Runnable {
	
	private File csvFile;
	
	SessionFactory sessionFactory;

	public UpdateDBThread(File csvFile, SessionFactory sessionFactory) {
		this.csvFile = csvFile;
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void run() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try (CSVReader read = new CSVReader(new FileReader(csvFile), '|')) {
			String[] row = read.readNext();
			while ((row = read.readNext()) != null) {
				String validTill = row[3];
				double flightDuration = Double.parseDouble(row[5]);
				double fare = Double.parseDouble(row[6]);
				boolean availability = row[7].equals("Y") ? true : false;

				FlightModel flightModel = new FlightModel(row[0], row[1], row[2], validTill, row[4], flightDuration,
						fare, availability, row[8]);
			 
				session.save(flightModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
	}
}
