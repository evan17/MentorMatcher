package edu.umich.mentormatcher;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;

public class Registration extends Activity implements View.OnClickListener {

//declare objects

    private Button buttonConfirm;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Spinner ConfirmAspiration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Link to UI
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        ConfirmAspiration = (Spinner) findViewById(R.id.ConfirmAspiration);

        //Start a Listener
        buttonConfirm.setOnClickListener(this);
        editTextEmail.setOnClickListener(this);
        editTextPassword.setOnClickListener(this);
        editTextConfirmPassword.setOnClickListener(this);
        ConfirmAspiration.setOnItemSelectedListener(this);

        //Creating list of items in the spinner
        List<String> categories = new ArrayList<String>();
        categories.add("Product Management");
        categories.add("Business Development");
        categories.add("Finance");
        categories.add("Marketing");
        categories.add("Consulting");
        categories.add("Brand Management");
        categories.add("Banking");

        //Creating adapter for spinner
        ArrayAdapter<String> adapter = ArrayAdapter.createFromResource (this, R.array.split_array, Android.R.simple_spinner_item);

        //Drop down layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        ConfirmAspiration.setAdapter(adapter);

        //Code that gets position of the selected item
        int position = ConfirmAspiration.getSelectedItemPosition();

        //Code that gets the selected text from the selected item
        String selectedText = (String) ConfirmAspiration.getSelectedItem();

        //This does not include the "become a member" text that is in the mockup

    }
    @Override

    public void onClick (View v) {
        //Event handler
        Intent registerIntent = new Intent(this, RegistationActivity);
        setContentView(R.layout.activity_main);
        this.startActivity(registerIntent);

        if (editTextEmail.getText().toString().isEmpty())
        &(editTextPassword.getText().toString().isEmpty())
                & (editTextConfirmPassword.getText().toString().equals(editTextPassword)) {
            //do if true
            //What the hell is it doing if true?
        }
    }else
    //if false, that means there's an entry for e-mail or password, so that's what allows you to click the button

    {
        //What happens if the login doesn't work
    }

    @Override
    public void onItemSelected (AdapterView <?> parent, View v, int position,
                                long id) {
        //What is the event exactly
    }
    @Override
    public void onNothingSelected (AdapterView<?> parent)

}
}
