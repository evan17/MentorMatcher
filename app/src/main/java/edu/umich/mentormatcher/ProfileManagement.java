package edu.umich.mentormatcher;

// This page is accessed from the landing page - useractivitymanagement - by mentors and mentees
// to access their upcomcing events.
// The mentor view has two buttons: "Update my Calendar" and "Update my Profile"
// The former goes to the calendar screen.  The latter goes to the update profile screen (to be built by Chidi and Lizzie)
// Kevin

// To Do:
// Connect Firebase and import classes throughout
// Remove text placeolders with firebase calls
// Decide if a menu should be included throughout (and if so, add)
// Finish navigation
// Add in firebase core stuff (done on other screens)

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static edu.umich.mentormatcher.R.id.buttonRatings;

public class ProfileManagement extends Activity implements View.OnClickListener{

    private Button buttonBack;
    private Button buttonUpdateProfile;
    private Button buttonUpdateAvailability;
    private TextView textName;
    private TextView textPosition;
    private TextView textServices;
    private TextView textAbout;
    private TextView textReviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        //  Link Java object to widgets
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonUpdateProfile = (Button) findViewById(R.id.buttonUpdateProfile);
        buttonUpdateAvailability = (Button) findViewById(R.id.buttonUpdateAvailability);
        textName = (TextView) findViewById(R.id.textName);
        textPosition = (TextView) findViewById(R.id.textPosition);
        textServices = (TextView) findViewById(R.id.textServices);
        textAbout = (TextView) findViewById(R.id.textAbout);
        textReviews = (TextView) findViewById(R.id.textReviews);

        // Set listeners
        buttonBack.setOnClickListener(this);
        buttonUpdateProfile.setOnClickListener(this);
        buttonUpdateAvailability.setOnClickListener(this);

        // Update Text from Database - placeholder data until databse created / setup
        // Update text
        textName.setText("Panpan in the house");
        textPosition.setText("Amazon Ruler");
        textServices.setText("E/'erthang");
        textAbout.setText("I now like tea mor than coffee / java");
        textReviews.setText("Stellar");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                // Go to prior screen - code forthcoming
                Intent intent = new Intent(this, UserActivityManagementScreen.class);
                startActivity(intent);
                break;
            case R.id.buttonUpdateProfile:
                // Go to update proile screen - code forthcoming

                break;
            case R.id.buttonUpdateAvailability:
                // Go to update availability (calendar) screen - code forthcoming

                break;

        }


    }
}
