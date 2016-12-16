package edu.umich.mentormatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MentorCommentActivity extends Activity implements View.OnClickListener{
    private RadioButton score1RadioButton;
    private RadioButton score2RadioButton;
    private RadioButton score3RadioButton;
    private RadioButton score4RadioButton;
    private RadioButton score5RadioButton;
    private TextView mentorCommentPromptTextView;
    private EditText mentorCommentEditText;
    private Button sendMentorCommentButton;

    private String currentSlotKey;
    private long currentMentorId;
    private long currentMenteeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_comment);

        score1RadioButton = (RadioButton)findViewById(R.id.radioButtonScore1);
        score2RadioButton = (RadioButton)findViewById(R.id.radioButtonScore2);
        score3RadioButton = (RadioButton)findViewById(R.id.radioButtonScore3);
        score4RadioButton = (RadioButton)findViewById(R.id.radioButtonScore4);
        score5RadioButton = (RadioButton)findViewById(R.id.radioButtonScore5);
        mentorCommentPromptTextView = (TextView)findViewById(R.id.textViewMentorCommentPrompt);
        mentorCommentEditText = (EditText)findViewById(R.id.editTextMentorComment);
        sendMentorCommentButton = (Button)findViewById(R.id.buttonSendMentorComment);

        sendMentorCommentButton.setOnClickListener(this);
        setUIValue();
    }

    public void setUIValue() {
        //get value from DB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("slots").child(String.valueOf(Util.getMentorIdFromSlotId(Util.currentSlotId)));

        ref.orderByChild("slotId").equalTo(Util.currentCommentId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String displayMsg = "";
                currentSlotKey = dataSnapshot.getKey();
                Slot slot = dataSnapshot.getValue(Slot.class);
                displayMsg += "Hi " + slot.menteeUid;
                displayMsg += "\n Please provide your feedback on your appointment with "+ slot.mentorUid + " at " + slot.startTime;

                mentorCommentPromptTextView.setText(displayMsg);
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

    public void commentMentor() {
        //store value to DB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("comments");
        int rating = 0;
        if(score1RadioButton.isChecked())
            rating = 1;
        else if (score2RadioButton.isChecked())
            rating = 2;
        else if (score3RadioButton.isChecked())
            rating = 3;
        else if (score4RadioButton.isChecked())
            rating = 4;
        else
            rating = 5;

        Comment comment = new Comment(currentMentorId, rating, mentorCommentEditText.getText().toString(), new Date(), true);
        DatabaseReference newRef = ref.push();
        newRef.setValue(comment);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSendMentorComment)
            commentMentor();
    }
}
