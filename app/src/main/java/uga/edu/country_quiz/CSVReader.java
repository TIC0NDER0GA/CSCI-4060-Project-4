package uga.edu.country_quiz;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * A Utility class for reading in the contents
 * of the country_continent.csv file.
 */
public class CSVReader {
    final private static int MAX_COUNTRY = 195; // the maximum number of countries in the file
    private String[][] data; // first index specifies which row second index the column
    final private Random random; // random number generator

    /**
     * The constructor that always points to
     * the csv RAW resource needs to be
     * supplied with App context.
     */
    public CSVReader(Context context) {
        int row = 0; // holds the current row location
        String line; // Used to store the result
        InputStream inputStream = context.getResources().openRawResource(R.raw.country_continent); // Object that can take in the csv data
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); // Object that will go over every newline in file
        data = new String[MAX_COUNTRY][2];
        random = new Random();

        try {
            while ((line = reader.readLine()) != null) {
                // comma as separator
                String[] stuff = line.split(","); // country and continent
                data[row][0] = stuff[0]; // country
                data[row][1] = stuff[1]; // continent

                // print statement to check data
                Log.d(TAG, "Column 1= " + data[row][0] + " , Column 2= " + data[row][1]);
                row++;
            }
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }


    }


    /**
     * Gets the country at the specified index.
     * @param i the index
     * @return the STRING country at the index
     */
    public String getCountry(int i) {
        if (checkRange(i)) {
            return "Country is not in range";
        }
        return data[i][0];
    }

    /**
     * Gets the continent at the specified index.
     * @param i the index
     * @return the STRING continent at the index
     */
    public String getContinent(int i) {
        if (checkRange(i)) {
            return "Continent is not in range";
        }
        return data[i][1];
    }

    /**
     * Returns a random country
     * @return the STRING country at the index
     */
    public String getRandCountry() {
        return data[random.nextInt(MAX_COUNTRY)][0];
    }

    /**
     * Returns a random continent
     * @return the STRING continent at the index
     */
    public String getRandContinent() {
        return data[random.nextInt(MAX_COUNTRY)][1];
    }

    /**
     *
     * @param r the number to be checked
     * @return TRUE if not in range FALSE if in range
     */
    private boolean checkRange(int r) {
        return r < 0 || r > MAX_COUNTRY;
    }

    /**
     * Just in case a reference of the data is needed outside
     * of the class. Keep in mind editing a variable that uses
     * this reference will change it's contents in the original
     * object.
     * @return a reference to the csv list
     */
    public String[][] getData() {
        return data;
    }


}
