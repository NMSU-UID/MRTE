package mrte.trollalarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

import static mrte.trollalarm.AlarmReceiver.ringtone;


public class MainActivity extends AppCompatActivity {
    public int temp;

    //Class Variables
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private TextView alarmTextView;
    Calendar calendar = null;
    Button MathBtn;
    TextView textView;


    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    //Create Alarm and Math instances
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
         alarmTextView = (TextView) findViewById(R.id.alarmText);

        MathBtn = (Button) findViewById(R.id.MathBtn);
        textView = findViewById(R.id.resultTextView);
        MathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), MathActivity.class), 1000);
//                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
//                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
//
//                int correctAnswer = 100;
//                int inputAnswer = Integer.parseInt(firstNumEditText.getText().toString());
//                temp = inputAnswer;
//                inst.temp = inputAnswer;
//
//
//                if (inputAnswer == correctAnswer) {
//                    resultTextView.setText("Correct!");
//                    inst.temp = inputAnswer;
//                    ringtone.stop();
//
//
//                }
//                else {
//                    resultTextView.setText("Incorrect!");
//                    inst.temp = inputAnswer;
//                }



            }
        });

    }

    //TODO: figure out how to get this to override the result and display if the user answer is correct.
    protected void onActivityResult(int request, int result, Nullable Intent) {
        if(request == 1000) {
            if(result == RESULT_OK) {
                textView.setText("Correct!");
            } else {
                textView.setText("Incorrect.");
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("MainActivity", "Alarm On");

            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, myIntent, 0);

            calendar = Calendar.getInstance();


            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());


            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);

        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MainActivity", "Alarm Off");
        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

}