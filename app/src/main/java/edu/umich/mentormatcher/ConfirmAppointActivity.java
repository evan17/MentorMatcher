package edu.umich.mentormatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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
    private String currentSlotKey;

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

        setUIValue();

    }

    public void setUIValue() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("slots").child(String.valueOf(Util.getMentorIdFromSlotId(Util.currentSlotId)));

        ref.orderByChild("slotId").equalTo(Util.currentSlotId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String displayMsg = "";
                currentSlotKey = dataSnapshot.getKey();
                Slot slot = dataSnapshot.getValue(Slot.class);
                displayMsg += "Hi " + slot.mentorUid;
                displayMsg += "\n Student " + slot.menteeUid + " just booked your time on " + slot.startTime;
                displayMsg += "Please confirm if this time works for you";

                mentorPromptTextView.setText(displayMsg);
                sendConfirmationTextView.setText("Send message to " + slot.menteeUid);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void confirmAppointment() {
        //store value to DB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("slots").child(String.valueOf(Util.getMentorIdFromSlotId(Util.currentSlotId)));
        if(currentSlotKey.length() == 0) {
            Toast.makeText(ConfirmAppointActivity.this, "cannot find current slot key", Toast.LENGTH_SHORT);
            return;
        }
        if(yesRadioButton.isChecked()) {
            ref.child(currentSlotKey).child("isConfirmed").setValue("true");
        } else if (noRadioButton.isChecked()) {
            ref.child(currentSlotKey).child("isBooked").setValue("false");
            ref.child(currentSlotKey).child("menteeId").setValue(0);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSendConfirmation) {
            confirmAppointment();
        }
    }
}
