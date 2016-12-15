package edu.umich.mentormatcher;

//

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CareerFunctions extends Activity implements View.OnClickListener {

    private Button buttonConsulting;
    private Button buttonFinance;
    private Button buttonOperations;
    private Button buttonMarketing;
    private Button buttonPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_functions);

        buttonConsulting=(Button)findViewById(R.id.buttonconsulting);
        buttonFinance=(Button)findViewById(R.id.buttonfiniance);
        buttonMarketing=(Button)findViewById(R.id.buttonfiniance);
        buttonPM=(Button)findViewById(R.id.buttonpm);
        buttonOperations=(Button)findViewById(R.id.buttonoperations);

        buttonPM.setOnClickListener(this);
        buttonFinance.setOnClickListener(this);
        buttonOperations.setOnClickListener(this);
        buttonMarketing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



        if(v==buttonConsulting){
            Util.funtions = "consulting";
        }
        else if(v==buttonFinance){

            Util.funtions = "finance";
        }
        else if(v==buttonPM){
            Util.funtions="product management";
        }

        Intent intentsearch=new Intent(CareerFunctions.this,MentorListReturn.class);
        startActivity(intentsearch);
    }
}
