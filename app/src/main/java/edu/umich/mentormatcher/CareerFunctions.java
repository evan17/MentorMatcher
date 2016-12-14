package edu.umich.mentormatcher;

//

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CareerFunctions extends Activity {

    private Button buttonConsulting;
    private Button buttonFinance;
    private Button buttonOperations;
    private Button buttonMarketing;
    private Button buttonPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_functions);
    }
}
