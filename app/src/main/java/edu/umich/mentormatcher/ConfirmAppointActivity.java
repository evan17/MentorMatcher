package edu.umich.mentormatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmAppointActivity extends Activity implements View.OnClickListener{
    private RadioButton yesRadioButton;
    private RadioButton noRadioButton;
    private TextView mentorPromptTextView;
    private TextView sendConfirmationTextView;
    private EditText confirmationMsgEditText;
    private Button sendConfirmationButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appoint);

        yesRadioButton = (RadioButton) findViewById(R.id.radioButtonYes);
        noRadioButton = (RadioButton) findViewById(R.id.radioButtonNo);
        mentorPromptTextView = (TextView) findViewById(R.id.textViewMentorPrompt);
        sendConfirmationTextView = (TextView) findViewById(R.id.textViewSendConfirmation);
        confirmationMsgEditText = (EditText) findViewById(R.id.editTextConfirmationMsg);
        sendConfirmationButton = (Button) findViewById(R.id.buttonSendConfirmation);

        sendConfirmationButton.setOnClickListener(this);
    }

    public void setUIValue() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("comment");
    }

    public void confirmAppointment() {
        //store value to DB
    }

    @Override
    public void onClick(View v) {

    }
}
