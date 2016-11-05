package codeit.android.trifit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import codeit.android.trifit.R;

public class EnterTriFitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_trifit);

        /* Inside the activity */
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.current_date);
        TextView mCurrentDate = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mCurrentDate.setText(getCurrentDate());

        TextView runWeekStatus = (TextView) findViewById(R.id.runningWeeklyStatusTv);
        TextView bikeWeekStatus = (TextView) findViewById(R.id.bikingWeeklyStatusTv);
        TextView swimWeekStatus = (TextView) findViewById(R.id.swimmingWeeklyStatusTv);


        final EditText runningActValue = (EditText) findViewById(R.id.runningActValue);
        EditText bikingActValue = (EditText) findViewById(R.id.bikingActValue);
        EditText swimmingActValue = (EditText) findViewById(R.id.swimmingActValue);

        Button saveBtn = (Button)findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //my be user name, date
                //take the values from EditText views  & put them in Db table
                runningActValue.getText();
            }
        });

    }


    public String getCurrentDate() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = df.format(c.getTime());
        return currentDate;
    }

}
