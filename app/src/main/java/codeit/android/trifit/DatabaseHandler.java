package codeit.android.trifit;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TriFitDB";

    // Contacts table name
    private static final String TABLE_USERACTIVITY = "user_activity_table";
    private static DatabaseHandler sDBInstance = null;

    // Contacts Table Columns names
    public static final String COLUMN_DATE_KEY = "date";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RUNNING = "running";
    public static final String COLUMN_BIKING = "biking";
    public static final String COLUMN_SWIMMING = "swimming";
    public static final String COLUMN_TOTAL = "total";

    //get singleton instance of database
    public static synchronized DatabaseHandler getInstance(Context context) {
        // Use the application context, to avoid memomory leaks of an activity.
        if (sDBInstance == null) {
            sDBInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sDBInstance;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERACTIVITY_TABLE = "CREATE TABLE " + TABLE_USERACTIVITY + "("
                + COLUMN_DATE_KEY + " INTEGER PRIMARY KEY," + COLUMN_NAME + "TEXT"
                + COLUMN_RUNNING + " FLOAT" + COLUMN_BIKING + "FLOAT" + COLUMN_SWIMMING + "FLOAT" +
                COLUMN_TOTAL + "FLOAT" + ")";
        db.execSQL(CREATE_USERACTIVITY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERACTIVITY);
            // Create tables again
            onCreate(db);
        }
    }


    public boolean addUserActivity(UserActivityModel userActivityModel) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, userActivityModel.getId()); // user Name
        values.put(COLUMN_NAME, userActivityModel.getName()); // user Name
        values.put(COLUMN_DATE_KEY, String.valueOf(userActivityModel.getDate())); // user Name
        values.put(COLUMN_RUNNING, userActivityModel.getRunning()); // Contact Phone
        values.put(COLUMN_SWIMMING, userActivityModel.getSwimming());
        values.put(COLUMN_BIKING, userActivityModel.getBiking());
        values.put(COLUMN_TOTAL, userActivityModel.getTotal());
        db.insert(TABLE_USERACTIVITY, null, values);
        return true;

    }

    // Adding new contact
    public long addorUpdateUserActivity(UserActivityModel userActivityModel) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        long userId = -1;
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, userActivityModel.getId()); // user Name
            values.put(COLUMN_NAME, userActivityModel.getName()); // user Name
            values.put(COLUMN_DATE_KEY, String.valueOf(userActivityModel.getDate())); // user Name
            values.put(COLUMN_RUNNING, userActivityModel.getRunning()); // Contact Phone
            values.put(COLUMN_SWIMMING, userActivityModel.getSwimming());
            values.put(COLUMN_BIKING, userActivityModel.getBiking());
            values.put(COLUMN_TOTAL, userActivityModel.getTotal());

            // update the note in case the user note already exists in the database
            int row = db.update(TABLE_USERACTIVITY, values, COLUMN_DATE_KEY + "= ?", new String[]{String.valueOf(userActivityModel.getDate())});
            Log.d(TAG, " row  " + row);
            // Check if update is success
            if (row == 1) {
                String queryUserMiles = String.format("SELECT %s FROM %s WHERE %s = ?",
                        COLUMN_TOTAL, TABLE_USERACTIVITY, COLUMN_DATE_KEY);
                Log.d(TAG, " userIdSelectQuery  " + row);
                Cursor cursor = db.rawQuery(queryUserMiles, new String[]{String.valueOf(userActivityModel.getTotal())});
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        Log.d(TAG, " Sucesful  ");
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user note did not  exist, insert new user row
                userId = db.insertOrThrow(TABLE_USERACTIVITY, null, values);
                db.setTransactionSuccessful();
                Log.d(TAG, "user note did not  exist, insert new user row  " + userId);
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update user");
        } finally {
            db.endTransaction();
        }
        return userId;
    }

    // Getting single userActivity
    public Cursor getUserActivity(String date) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from user_activity_table where date=" + date + "", null);
        return res;
    }

}