package edu.umich.mentormatcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

//This registration page is coded to write to the User node of the database

public class Registration extends Activity implements View.OnClickListener, OnItemSelectedListener {

//declare objects

    private Button buttonC;
    private Button buttonBecomeMentor;
    private EditText editTextName;
    private Spinner ConfirmAspiration;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Link to UI
        buttonC = (Button)findViewById(R.id.buttonC);
        buttonBecomeMentor = (Button)findViewById(R.id.buttonBecomeMentor);
        editTextName = (EditText) findViewById(R.id.editTextName);
        ConfirmAspiration = (Spinner) findViewById(R.id.ConfirmAspiration);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        buttonC.setOnClickListener(this);
        buttonBecomeMentor.setOnClickListener(this);
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

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Consulting");
        categories.add("Finance");
        categories.add("Operations");
        categories.add("Marketing");
        categories.add("Product Management");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        ConfirmAspiration.setAdapter(dataAdapter);



    }
//End of On Create!

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

    public void onClick (View view) {

        //Code that gets the selected text from the selected item

        String selectedText = (String) ConfirmAspiration.getSelectedItem();

        // add conditional in case user alrady has an account
       /*String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String career = ConfirmAspiration.getItemAtPosition().toString();
        this.createAccount(email,password);

        User user= new User (email,password,name,career);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataUser = database.getReference("Users");

        DatabaseReference dataNewUser = dataUser.push();
        dataNewUser.setValue(user);

        //Also this page does not capture two more elements of the User class which are name and uid

        if (view == buttonC){
                    Intent intentLogin = new Intent (Registration.this, Login.class);
                    startActivity(intentLogin);
        }*/

    }

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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

