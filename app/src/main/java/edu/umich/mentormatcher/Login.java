package edu.umich.mentormatcher;
// This is the main Login Page, where the user will sign in

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements View.OnClickListener {

    //Declaring objects?

    private EditText editTextuser;
    private EditText editTextpassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//Linking objects to UI?

        editTextuser = (EditText) findViewById(R.id.editTextuser);
        editTextpassword = (EditText)findViewById(R.id.editTextpassword);
        buttonLogin = (Button)findViewById(R.id.B1);
        buttonRegister =(Button)findViewById(R.id.B2);
        buttonForgot = (Button)findViewById(R.id.B3);


        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
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
}
