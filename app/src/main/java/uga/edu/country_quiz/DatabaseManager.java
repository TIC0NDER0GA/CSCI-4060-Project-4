package uga.edu.country_quiz;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *Manages database with the tables used in the
 *application.
 */
public class DatabaseManager extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "ZA_WARUDO"; // the database name
    private final static int DATABASE_VERSION = 1; // the version for the super constructor
    private ArrayList<Quiz> quizzes; // used to hold all quiz results from previous quizzes


    /**
     * Creates a database instance
     * and the database file within the android
     * file system.
     * @param context the current app's global context
     */
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        quizzes = new ArrayList<Quiz>();
    }

    /**
     * Creates the tables for use in the
     * quiz application for later.
     * @param db database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // the countries table
        db.execSQL("CREATE TABLE IF NOT EXISTS country_table (id INTEGER PRIMARY KEY, country TEXT, continent TEXT)");
        // the quiz table
        db.execSQL("CREATE TABLE IF NOT EXISTS quiz_result (id INTEGER PRIMARY KEY, date TEXT, score REAL)");
    }

    /**
     * Upgrades the database version internally in the
     * android device. Not applicable for the current
     * application.
     * @param sqLiteDatabase the database
     * @param i current version number
     * @param i1 next version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     *Inserts all the countries and continents into the database
     * on a seperate thread to prevent stalling of app.
     * @param worldData the countries and continents from the csv.
     */
    public void insertCC(String[][] worldData) {
        SQLiteDatabase db = getWritableDatabase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < worldData.length; i++) {
                    ContentValues values = new ContentValues();
                    values.put("country", worldData[i][0]);
                    values.put("continent", worldData[i][1]);
                    db.execSQL("INSERT OR IGNORE INTO country_table (country, continent) VALUES (?, ?)",
                            new String[] { worldData[i][0], worldData[i][1] });
                }
            }
        });
        db.close();
    }

    /**
     * Puts in a quiz's date and score when it finishes.
     * @param dt the date the quiz was taken
     * @param sr the score the user made on the quiz
     */
    public void insertQS(String dt, double sr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        double number = sr;
        DecimalFormat df = new DecimalFormat("#.##");
        double formattedNumber = Double.parseDouble(df.format(number));
        values.put("date", dt);
        values.put("score", formattedNumber);
        db.insert("quiz_result", null, values);
        db.close();
    }

    /**
     * Method that queries the database for all past quizzes
     * @return an ARRAYLIST<QUIZ> of all previous quizzes taken
     */
    public ArrayList<Quiz> getAllQS() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM quiz_result ORDER BY score DESC;", null);
        if (cur.moveToFirst()) {
            do {
                quizzes.add(new Quiz(cur.getInt(0), cur.getString(1), cur.getDouble(2)));
                Log.e(TAG,cur.getInt(0) + " " + cur.getString(1) + " " + cur.getDouble(2));
            } while (cur.moveToNext());
        }
        cur.close();
        return quizzes;
    }



}
