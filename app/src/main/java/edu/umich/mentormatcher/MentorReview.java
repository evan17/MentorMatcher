package edu.umich.mentormatcher;

// This is the Mentor profile viewed by the mentee.  The mentee chooses which service to use.
// The mentee can then go to the mentor booking page
// Kevin

// To Do:
// Connect Firebase and import classes throughout
// Remove text placeolders with firebase calls
// Decide if a menu should be included throughout (and if so, add)
// Finish navigation


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

public class MentorReview extends Activity implements View.OnClickListener{

    private TextView textName;
    private TextView textPosition;
    private TextView textServices;
    private TextView textAbout;
    private TextView textReviews;
    private Button buttonBack;
    private Button buttonCheckAvailability;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



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
        textServices.setText("E\'erthang");
        textAbout.setText("I now like tea mor than coffee / java");
        textReviews.setText("Stellar");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MentorReview.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MentorReview.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MentorReview.this, CareerFunctions.class);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentMonitor = new Intent(MentorReview.this, CareerFunctions.class);

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



