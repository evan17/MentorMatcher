package edu.umich.mentormatcher;
// This is the main Login Page, where the user will sign in

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.attr.password;

public class Login extends Activity implements View.OnClickListener {

    //Declaring objects

    private EditText editTextuser;
    private EditText editTextpassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonForgot;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Login.this, "User Logged In:" + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Nobody Logged In", Toast.LENGTH_SHORT).show();
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

//Linking objects to UI

        editTextuser = (EditText) findViewById(R.id.editTextuser);
        editTextpassword = (EditText)findViewById(R.id.editTextpassword);
        buttonLogin = (Button)findViewById(R.id.B1);
        buttonRegister =(Button)findViewById(R.id.B2);
        buttonForgot = (Button)findViewById(R.id.B3);


        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        buttonForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public Button getButtonForgot() {
        return buttonForgot;
    }

    public void setButtonForgot(Button buttonForgot) {
        this.buttonForgot = buttonForgot;
    }

    //Sign up new Users
    public void createAccount (String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //Sign in existing users
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
