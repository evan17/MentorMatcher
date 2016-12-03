package edu.umich.mentormatcher;

// This is the Mentor profile viewed by the mentee.  The mentee chooses which service to use.
// The mentee can then go to the mentor booking page
// Kevin

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MentorReview extends Activity implements View.OnClickListener{

    private TextView textName;
    private TextView textPosition;
    private TextView textServices;
    private TextView textAbout;
    private TextView textReviews;
    private Button buttonBack;
    private Button buttonCheckAvailability;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_review);

        // Tie items to interface
        textName = (TextView) findViewById(R.id.textName);
        textPosition = (TextView) findViewById(R.id.textPosition);
        textServices = (TextView) findViewById(R.id.textServices);
        textAbout = (TextView) findViewById(R.id.textAbout);
        textReviews = (TextView) findViewById(R.id.textReviews);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonCheckAvailability = (Button) findViewById(R.id.buttonCheckAvailability);

        // Set  Listeners
        buttonCheckAvailability.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        // Update Text from Database - placeholder data until databse created / setup
        textName.setText("Panpan");
        textPosition.setText("Amazon Ruler");
        textServices.setText("E/'erthang");
        textAbout.setText("I now like tea mor than coffee / java");
        textReviews.setText("Stellar");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonBack:
                // Go to prior screen - code forthcoming once mentor list in place - need to look into saving the prior search as well
                //Intent intent = new Intent(this, mentorlist.class);
                //startActivity(intent);

                break;
            case R.id.buttonCheckAvailability:
                // Go to check availability screen - code forthcoming
                //Intent intent = new Intent(this, checkavailability.class);
                //startActivity(intent);
                break;
        }




    }
}
