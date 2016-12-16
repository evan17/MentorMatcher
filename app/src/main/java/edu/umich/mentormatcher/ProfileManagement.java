package edu.umich.mentormatcher;

// This page is accessed from the landing page - useractivitymanagement - by mentors and mentees
// to access their upcomcing events.
// The mentor view has two buttons: "Update my Calendar" and "Update my Profile"
// The former goes to the calendar screen.  The latter goes to the update profile screen (to be built by Chidi and Lizzie)
// Kevin

// To Do:
// Connect Firebase and import classes throughout
// Remove text placeolders with firebase calls
// Finish navigation


//Complete:
// Menu added
// Buttons created and tied
// Firebase Auth configured and added

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import static edu.umich.mentormatcher.R.id.buttonRatings;

public class ProfileManagement extends Activity implements View.OnClickListener{

    private Button buttonUpdateProfile;
    private Button buttonUpdateAvailability;
    private TextView textName;
    private TextView textPosition;
    private TextView textAbout;
    private TextView textReviews;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        //  Link Java object to widgets
        buttonUpdateProfile = (Button) findViewById(R.id.buttonUpdateProfile);
        buttonUpdateAvailability = (Button) findViewById(R.id.buttonUpdateAvailability);
        textName = (TextView) findViewById(R.id.textName);
        textPosition = (TextView) findViewById(R.id.textPosition);
        textAbout = (TextView) findViewById(R.id.textAbout);
        textReviews = (TextView) findViewById(R.id.textReviews);

        // Set listeners
        buttonUpdateProfile.setOnClickListener(this);
        buttonUpdateAvailability.setOnClickListener(this);

        // Update Text from Database - placeholder data until databse created / setup
        // Update text
        textName.setText("Panpan in the house");
        textPosition.setText("Amazon Ruler");
        textAbout.setText("I now like tea mor than coffee / java");
        textReviews.setText("Stellar");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(ProfileManagement.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileManagement.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileManagement.this, CareerFunctions.class);
                    startActivity(intent);
                }
            }
        };


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonUpdateProfile:
                // Go to update proile screen - code forthcoming
                Intent intent = new Intent(this, Registration.class);
                startActivity(intent);
                break;
            case R.id.buttonUpdateAvailability:
                // Go to update availability (calendar) screen - code forthcoming
                Intent intent2 = new Intent(this, MentorCalendarUpdate.class);
                startActivity(intent2);
                break;

        }


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentMonitor = new Intent(ProfileManagement.this, CareerFunctions.class);

        if (mAuth.getCurrentUser() != null ) {
            if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();

            } else if (item.getItemId() == R.id.menuCareerFunctions) {
                startActivity(intentMonitor);

            }
        } else {
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigationmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }
}
