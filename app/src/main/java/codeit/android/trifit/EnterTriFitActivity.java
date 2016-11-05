package codeit.android.trifit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import codeit.android.trifit.R;

public class EnterTriFitActivity extends AppCompatActivity {

    private DatabaseHandler mDBHandler;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBHandler = DatabaseHandler.getInstance(this);
        mContext = this;

        setContentView(R.layout.activity_enter_trifit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mCurrentDate = (TextView) toolbar.findViewById(R.id.current_date);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mCurrentDate.setText(getCurrentDate());

        TextView runWeekStatus = (TextView) findViewById(R.id.runningWeeklyStatusTv);
        TextView bikeWeekStatus = (TextView) findViewById(R.id.bikingWeeklyStatusTv);
        TextView swimWeekStatus = (TextView) findViewById(R.id.swimmingWeeklyStatusTv);


        final EditText runningActValue = (EditText) findViewById(R.id.runningActValue);
        final EditText bikingActValue = (EditText) findViewById(R.id.bikingActValue);
        final EditText swimmingActValue = (EditText) findViewById(R.id.swimmingActValue);

       Button saveBtn = (Button) findViewById(R.id.saveBtn);

        Button rewardBtn = (Button) findViewById(R.id.rewardBtn);

//        Button notifyBtn = (Button) findViewById(R.id.notifyBtn);
//
//        notifyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String number = "6144049357";  // The number on which you want to send SMS
//                try {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(number, null, "you did it", null, null);
//                    Toast.makeText(getApplicationContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(),
//                            "Sending SMS failed.",
//                            Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
//                }
//                Toast.makeText(getApplicationContext(), "Notified James", Toast.LENGTH_SHORT).show();
//            }
//        });

       rewardBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(mContext, RewardActivity.class);
               startActivity(intent);
           }
       });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                UserActivityModel model = new UserActivityModel();
//                //my be user name, date
//                //take the values from EditText views  & put them in Db table
//                model.setName("James");
//                model.setDate(getCurrentDate());
//                model.setRunning(runningActValue.getText().toString());
//                model.setBiking(bikingActValue.getText().toString());
//                model.setBiking(swimmingActValue.getText().toString());
//
//                float calculatedTotal = Float.valueOf(runningActValue.getText().toString()) +
//                        Float.valueOf(bikingActValue.getText().toString()) +
//                        Float.valueOf(swimmingActValue.getText().toString());
//
//                mDBHandler.addUserActivity(model);


                Toast.makeText(getApplicationContext(), "User Activity is Saved", Toast.LENGTH_SHORT).show();

            }
        });

//
//        Cursor cur = mDBHandler.getUserActivity(getCurrentDate());
//        cur.moveToFirst();
//
//        String todayRun = cur.getString(cur.getColumnIndex(mDBHandler.COLUMN_RUNNING));
//        String todayBike = cur.getString(cur.getColumnIndex(mDBHandler.COLUMN_BIKING));
//        String todaySwim = cur.getString(cur.getColumnIndex(mDBHandler.COLUMN_SWIMMING));

        String todayRun = "10";
        String todayBike = "20";
        String todaySwim = "3";

        runWeekStatus.setText( "Ran " + todayRun + " miles out of 20 miles");
        bikeWeekStatus.setText( "Biked " + todayBike + " miles out of 40 miles");
        swimWeekStatus.setText( "Swam " + todaySwim + " miles out of 5 miles");


    }


    public String getCurrentDate() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = df.format(c.getTime());
        return currentDate;
    }

}
