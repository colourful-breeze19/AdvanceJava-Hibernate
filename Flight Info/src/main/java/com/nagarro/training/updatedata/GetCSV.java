/**
 * @class GetCSV
 * @author chahat
 * @description GetCSV implement the getFilesList method which returns the list of CSV files in the resource folder.
 *
 *
 */
package com.nagarro.training.updatedata;

import java.io.File;
import java.io.FileFilter;

public class GetCSV {
    public File[] getFilesList() {
        File folder = new File("src/main/resources");
        FileFilter csvFilter = (File file) -> file.getName().endsWith(".csv");
        File[] listOfFiles = folder.listFiles(csvFilter);
        return listOfFiles;
    }
}
