package edu.umich.mentormatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static edu.umich.mentormatcher.R.id.buttonConfirmMentor;

//This page is where the mentor will enter their information "About Yourself," "Services Provided," and a confirm button
//Lizzie will add this 12/6/16

public class MentorRegistration extends Activity implements View.OnClickListener {

    private Button buttonConfirmMentor;
    private EditText editTextAboutYou;
    private EditText editTextServicesProvided;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_registration);

        buttonConfirmMentor = (Button)findViewById(R.id.buttonConfirmMentor);
        editTextAboutYou = (EditText)findViewById(R.id.editTextAboutYou);
        editTextServicesProvided = (EditText)findViewById(R.id.editTextServicesProvided);

        buttonConfirmMentor.setOnClickListener(this);
        editTextAboutYou.setOnClickListener(this);
        editTextServicesProvided.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MentorRegistration.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MentorRegistration.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MentorRegistration.this, CareerFunctions.class);
                    startActivity(intent);
                }
            }
        };

    }
    @Override
    public void onClick(View view) {
            if (view == buttonConfirmMentor) {
                Intent intentLogin = new Intent(MentorRegistration.this, ProfileManagement.class);
                startActivity(intentLogin);
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

        Intent intentMonitor = new Intent(MentorRegistration.this, CareerFunctions.class);

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

//The register button will need to go to the login page. Need to set button listener and set up the intent
//Also need to make sure this page has necessary read-write to Firebase functionality