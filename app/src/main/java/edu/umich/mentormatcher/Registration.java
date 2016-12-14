package edu.umich.mentormatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import java.util.ArrayList;

//This registration page is coded to write to the User node of the database

public class Registration extends Activity implements View.OnClickListener, OnItemSelectedListener {

//declare objects

    private Button buttonConfirm;
    private Button buttonBecomeMentor;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Spinner ConfirmAspiration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Link to UI
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonBecomeMentor = (Button)findViewById(R.id.buttonBecomeMentor);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        ConfirmAspiration = (Spinner) findViewById(R.id.ConfirmAspiration);

        //Start a Listener
        buttonConfirm.setOnClickListener(this);
        buttonBecomeMentor.setOnClickListener(this);
        editTextEmail.setOnClickListener(this);
        editTextPassword.setOnClickListener(this);
        editTextConfirmPassword.setOnClickListener(this);
        ConfirmAspiration.setOnItemSelectedListener(this);


        //Creating list of items in the spinner
        List<String> categories = new ArrayList<>();
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
    @Override

    public void onClick (View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        //User class does not include something called confirm password. How do we account for that on this page?
        //Also this page does not capture two more elements of the User class which are name and uid
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

}

