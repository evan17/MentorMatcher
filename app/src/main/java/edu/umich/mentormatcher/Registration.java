package edu.umich.mentormatcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static edu.umich.mentormatcher.R.id.buttonC;

//This registration page is coded to write to the User node of the database

public class Registration extends Activity implements View.OnClickListener, OnItemSelectedListener {

//declare objects

    private Button buttonC;
    private Button buttonBecomeMentor;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Spinner ConfirmAspiration;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Link to UI
        buttonC = (Button)findViewById(R.id.buttonC);
        buttonBecomeMentor = (Button)findViewById(R.id.buttonBecomeMentor);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        ConfirmAspiration = (Spinner) findViewById(R.id.ConfirmAspiration);

        buttonC.setOnClickListener(this);
        buttonBecomeMentor.setOnClickListener(this);
        editTextEmail.setOnClickListener(this);
        editTextPassword.setOnClickListener(this);
        ConfirmAspiration.setOnItemSelectedListener(this);

        //Add Firebase Auth here

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Registration.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Registration.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registration.this, CareerFunctions.class);
                    startActivity(intent);
                }
            }
        };

        //Add onstart onstop

        //Creating list of items in the spinner
        List<String> categories = new ArrayList<String>();
        categories.add("Consulting");
        categories.add("Finance");
        categories.add("Operations");
        categories.add("Marketing");
        categories.add("Product Management");


        //Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        //Drop down layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        ConfirmAspiration.setAdapter(adapter);


        //Code that gets the selected text from the selected item

        String selectedText = (String) ConfirmAspiration.getSelectedItem();

    }

    private void findViewById(Button buttonC) {
    }

    public void onClick (View view) {

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        //User class does not include something called confirm password. How do we account for that on this page?
        //Also this page does not capture two more elements of the User class which are name and uid

        if (view == buttonC){
                    Intent intentLogin = new Intent (Registration.this, Login.class);
                    startActivity(intentLogin);
        } else {
            if (view == buttonBecomeMentor) {
                new AlertDialog.Builder(Registration.this)
                        .setMessage("Do you want to become a mentor?")
                        .setNegativeButton("Become a Mentor", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Intent intent = new Intent(Registration.this, MentorRegistration.class);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("Do not become a mentor", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Registration.this, Login.class);
                                startActivity(intent);
                            }
                        }).show();

            }
        }


    }

    @Override
    public void onItemSelected (AdapterView <?> parent, View v, int position,
                                long id) {
        String careerAspiration = parent.getItemAtPosition(1).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + careerAspiration, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(Registration.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentMonitor = new Intent(Registration.this, CareerFunctions.class);

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

