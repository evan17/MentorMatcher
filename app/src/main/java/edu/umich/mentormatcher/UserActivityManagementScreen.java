package edu.umich.mentormatcher;

// This is the landing page after login.  It shows upcoming events, averge rating, and profile details
// It has a button to navigate to the profile management screen
// Kevin


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivityManagementScreen extends Activity implements View.OnClickListener{

    private Button buttonBack;
    private Button buttonProfile;
    private Button buttonRatings;
    private Button buttonAppointments;
    private TextView textName;
    private TextView textPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management_screen);

        //  Link Java object to widgets
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonRatings = (Button) findViewById(R.id.buttonRatings);
        buttonAppointments = (Button) findViewById(R.id.buttonAppointments);
        textName = (TextView) findViewById(R.id.textName);
        textPosition = (TextView) findViewById(R.id.textPosition);

        // Set listeners

        buttonBack.setOnClickListener(this);
        buttonProfile.setOnClickListener(this);
        buttonRatings.setOnClickListener(this);
        buttonAppointments.setOnClickListener(this);

        // Update Text from Database - placeholder data until databse created / setup
        // Update text
        textName.setText("Panpan in the house");
        textPosition.setText("Amazon Ruler");
        // Update buttons
        buttonRatings.setText("4.7");
        buttonAppointments.setText("5");


    }

    @Override
    public void onClick(View v) {
        // Event handler
        switch (v.getId()) {
            case R.id.buttonBack:
                // Go to prior screen - code forthcoming

                break;
            case R.id.buttonProfile:
                // Go to proile screen - code forthcoming
                Intent intent = new Intent(this, ProfileManagement.class);
                startActivity(intent);
                break;
            case R.id.buttonRatings:
                // Go to ratings screen - code forthcoming

                break;
            case R.id.buttonAppointments:
                // Go to appointments screen - code forthcoming

                break;

        }


    }
}
