package edu.umich.mentormatcher;

// This is the landing page after login.  It shows upcoming events, averge rating, and profile details
// It has a button to navigate to the profile management screen
// Kevin

// To Do:
// Connect Firebase and import classes throughout
// Remove text placeolders with firebase calls
// Finish navigation
// Figure out how to get the aggregated rating

//Complete:
// Menu added
// Buttons created and tied
//


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

public class UserActivityManagementScreen extends Activity implements View.OnClickListener{

    private Button buttonProfile;
    private Button buttonRatings;
    private Button buttonAppointments;
    private TextView textName;
    private TextView textPosition;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management_screen);

        //  Link Java object to widgets
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonRatings = (Button) findViewById(R.id.buttonRatings);
        buttonAppointments = (Button) findViewById(R.id.buttonAppointments);
        textName = (TextView) findViewById(R.id.textName);
        textPosition = (TextView) findViewById(R.id.textPosition);

        // Set listeners

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


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(UserActivityManagementScreen.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserActivityManagementScreen.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserActivityManagementScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

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
    public void onClick(View v) {
        // Event handler
        switch (v.getId()) {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentMonitor = new Intent(UserActivityManagementScreen.this, CareerFunctions.class);

        if (mAuth.getCurrentUser() != null ) {
            if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();

            } else if (item.getItemId() == R.id.menuCareerFunctions) {
                startActivity(intentMonitor);

            }
        } else {
            Toast.makeText(this, "Gotta log in", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigationmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }




}
