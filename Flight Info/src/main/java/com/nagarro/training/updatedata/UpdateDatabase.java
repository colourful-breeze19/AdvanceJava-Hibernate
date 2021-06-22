/**
 * @class  UpdateDatabase
 * @author chahat
 * @description UpdateDatabase class updates the database for each airline's CSV files in the csvFiles.
 *
 */
package com.nagarro.training.updatedata;

import org.hibernate.SessionFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UpdateDatabase {
    public void getFlight(SessionFactory sessionFactory) {

        GetCSV getFileNames = new GetCSV();
        File[] csv = getFileNames.getFilesList();

        /**
         * Declared ExecutorService to create a fixed thread pool for the CSV files execution.
         */
        ExecutorService es = Executors.newFixedThreadPool(csv.length);

        for (int i = 0; i < csv.length; i++) {
            UpdateDBThread findFlights = new UpdateDBThread(csv[i], sessionFactory);
            es.submit(findFlights);
        }
        es.shutdown();
        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
