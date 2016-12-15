package edu.umich.mentormatcher;

//

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CareerFunctions extends Activity implements View.OnClickListener {

    private Spinner spinner;
    private EditText editTextsearch;
    private Button buttonconsulting;
    private Button buttonfinance;
    private Button buttonoperations;
    private Button buttonmarketing;
    private Button buttonpm;
    private Button buttonplus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_functions);

        spinner = (Spinner)findViewById(R.id.spinner);
        editTextsearch = (EditText)findViewById(R.id.editTextsearch);
        buttonconsulting = (Button)findViewById(R.id.buttonconsulting);
        buttonfinance = (Button)findViewById(R.id.buttonfinance);
        buttonoperations = (Button)findViewById(R.id.buttonoperations);
        buttonmarketing = (Button)findViewById(R.id.buttonmarketing);
        buttonpm = (Button)findViewById(R.id.buttonpm);
        buttonplus = (Button)findViewById(R.id.buttonplus);

        buttonconsulting.setOnClickListener(this);
        buttonfinance.setOnClickListener(this);
        buttonoperations.setOnClickListener(this);
        buttonmarketing.setOnClickListener(this);
        buttonpm.setOnClickListener(this);
        buttonplus.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }
}
