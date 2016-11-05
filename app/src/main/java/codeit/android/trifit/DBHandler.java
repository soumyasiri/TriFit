package com.example.shruti.mydata;

/**
 * Created by shruti on 11/5/2016.
 */
package com.androidhive.androidsqlite;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler {public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TriFitDB";

    // Contacts table name
    private static final String TABLE_USERACTIVITY = "UserActivity";

    // Contacts Table Columns names
    private static final Integer KEY_DATE = "date";
    private static final String KEY_NAME = "name";
    private static final Float  KEY_RUNNING = "running";
    private static final Float  KEY_BIKING= "biking";
    private static final Float  KEY_SWIMMING = "swimming";
    private static final Float  KEY_TOTAL= "total";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERACTIVITY + "("
                + KEY_DATE + " INTEGER PRIMARY KEY," + KEY_NAME + " STRING,"
                + KEY_RUNNING + " FLOAT" + KEY_BIKING + "FLOAT" + KEY_SWIMMING + "FLOAT" +
                KEY_TOTAL + "FLOAT"")";
        db.execSQL(CREATE_USERACTIVITY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERACTIVITY);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addUserActivity(UserActivity contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // user Name
        values.put(KEY_RUNNING, contact.getRunning()); // Contact Phone
        values.put(KEY_SWIMMING, contact.getSwimming());
        values.put(KEY_BIKING, contact.getBiking());
         // Inserting Row
        db.insert(TABLE_USERACTIVITY, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERACTIVITY, new String[] { KEY_DATE,KEY_NAME,
                        KEY_RUNNING,KEY_SWIMMING,KEY_BIKING }, KEY_DATE + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserActivity useractivity = new useractivity(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return useractivity;
    }

    // Getting All Contacts
    public List<useractivity> getAllUseractivity() {
        List<UserActivity> userActivityList = new ArrayList<UserActivity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERACTIVITY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserActivity userActivity = new userActivity();
                userActivity.setDate(Integer.parseInt(cursor.getString(0)));
                userActivity.setName(cursor.getString(1));
                userActivity.setRunning(cursor.getFloat(2));
                userActivity.setSwmimming(cursor.getFloat(2));
                userActivity.setBiking(cursor.getFloat(2));
                userActivity.setToatl(cursor.getFloat(2));
                // Adding contact to list
                userActivityList.add(userActivity);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userActivityList;
    }

    // Updating single contact
    public int update UserActivity(UserActivity userActivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userActivity.getName());
        values.put(KEY_RUNNING, userActivity.getRunning());
        values.put(KEY_SWIMMING, userActivity.getSwimming());
        values.put(KEY_BIKING, userActivity.getBiking());
        values.put(KEY_TOTAL, userActivity.getTotal());



        // updating row
        return db.update(TABLE_USERACTIVITY, values, KEY_DATE + " = ?",
                new String[] { String.valueOf(userActivity.getDate()) });
    }


}


