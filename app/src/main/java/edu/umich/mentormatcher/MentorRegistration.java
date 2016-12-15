package edu.umich.mentormatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static edu.umich.mentormatcher.R.id.buttonConfirmMentor;

//This page is where the mentor will enter their information "About Yourself," "Services Provided," and a confirm button
//Lizzie will add this 12/6/16

public class MentorRegistration extends Activity implements View.OnClickListener {

    private Button buttonConfirmMentor;
    private EditText editTextAboutYou;
    private EditText editTextServicesProvided;


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

    }
    @Override
    public void onClick(View view) {
            if (view == buttonConfirmMentor) {
                Intent intentLogin = new Intent(MentorRegistration.this, ProfileManagement.class);
                startActivity(intentLogin);
            }
    }
}

//The register button will need to go to the login page. Need to set button listener and set up the intent
//Also need to make sure this page has necessary read-write to Firebase functionality