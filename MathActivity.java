package mrte.trollalarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MathActivity extends AppCompatActivity {

    //Class Variables
    Button button;
    EditText editText;
    TextView textView;
    Random r;
    int i, x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        //Map onscreen buttons
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView2);

        //Generate random addition equation.
        r = new Random();
        i = r.nextInt(10) + 1;
        x = r.nextInt(10) + 1;
        textView.setText(x + " + " + i);

        //Check for user input, if the answer is correct exit the module, otherwise do not.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String inputLine = editText.getText().toString();
                if(!inputLine.equals("")){
                    int userAnswer = Integer.parseInt(inputLine);
                    if(userAnswer == getAnswer()) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                } else {
                    setResult(RESULT_CANCELED);
                }

            }
        });
    }

    //Return the answer to the equation generated.
    private int getAnswer() {
        int answer;
        answer = x + i;
        return answer;
    }

    //Allow the user to exit...for now...
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
