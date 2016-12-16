package edu.umich.mentormatcher;

// This page is accessed by the mentee from the MentorReview page for the mentee to request booking of the mentor
// Panpan

// Added Firebase Auth
// Added Menu

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MentorBooking extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_booking);



        // Firebase Auth implementation
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MentorBooking.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MentorBooking.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MentorBooking.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentMonitor = new Intent(MentorBooking.this, CareerFunctions.class);

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

}
