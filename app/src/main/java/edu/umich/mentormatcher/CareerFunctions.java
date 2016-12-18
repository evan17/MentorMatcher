package edu.umich.mentormatcher;

//

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static edu.umich.mentormatcher.R.id.buttonconsulting;

public class CareerFunctions extends Activity implements View.OnClickListener {

    private Button buttonConsulting;
    private Button buttonFinance;
    private Button buttonOperations;
    private Button buttonMarketing;
    private Button buttonPM;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_functions);

        buttonConsulting=(Button)findViewById(buttonconsulting);
        buttonFinance=(Button)findViewById(R.id.buttonfiniance);
        buttonMarketing=(Button)findViewById(R.id.buttonfiniance);
        buttonPM=(Button)findViewById(R.id.buttonpm);
        buttonOperations=(Button)findViewById(R.id.buttonoperations);

        buttonPM.setOnClickListener(this);
        buttonFinance.setOnClickListener(this);
        buttonOperations.setOnClickListener(this);
        buttonMarketing.setOnClickListener(this);
        buttonConsulting.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(CareerFunctions.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } /*else {
                    Toast.makeText(CareerFunctions.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CareerFunctions.this, CareerFunctions.class);
                    startActivity(intent);
                }*/
            }
        };

    }

    @Override
    public void onClick(View v) {



        if(v==buttonConsulting){
            Util.funtions = "consulting";

        }
        else if(v==buttonFinance){

            Util.funtions = "finance";
        }
        else if(v==buttonPM){
            Util.funtions="product management";
        }

        Intent intentsearch=new Intent(CareerFunctions.this,MentorListReturn.class);
        startActivity(intentsearch);
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

        Intent intentMonitor = new Intent(CareerFunctions.this, CareerFunctions.class);

        if (mAuth.getCurrentUser() != null ) {
            if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();

            } else if (item.getItemId() == R.id.menuCareerFunctions) {
                Toast.makeText(this, "You're There!", Toast.LENGTH_SHORT).show();

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
