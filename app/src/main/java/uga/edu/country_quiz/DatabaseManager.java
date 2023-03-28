package uga.edu.country_quiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *Manages database with the tables used in the
 *application.
 */
public class DatabaseManager extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "ZA_WARUDO"; // the database name
    private final static int DATABASE_VERSION = 1; // the version for the super constructor


    /**
     * Creates a database instance
     * and the database file within the android
     * file system.
     * @param context the current app's global context
     */
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the tables for use in the
     * quiz application for later.
     * @param db database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // the countries table
        db.execSQL("CREATE TABLE country_table (id INTEGER PRIMARY KEY, country TEXT, continent TEXT)");
        // the quiz table
        db.execSQL("CREATE TABLE quiz_result (id INTEGER PRIMARY KEY, date TEXT, score REAL)");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Puts in the country and continent
     * when needed.
     * @param ctry the country to insert
     * @param cntient the continent to insert
     */
    public void insertCC(String ctry, String cntient) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "VALUES (" + ctry + "," + cntient + ")";
        db.execSQL("INSERT INTO country_table (country, continent) " + query);
        db.close();
    }

    /**
     * Puts in a quiz's date and score when it finishes.
     * @param dt the date the quiz was taken
     * @param sr the score the user made on the quiz
     */
    public void insertQS(String dt, double sr) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "VALUES (" + dt + "," + sr + ")";
        db.execSQL("INSERT INTO country_table (date, score) " + query);
        db.close();
    }



}
