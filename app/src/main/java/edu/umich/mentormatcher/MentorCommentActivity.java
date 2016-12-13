package edu.umich.mentormatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MentorCommentActivity extends Activity implements View.OnClickListener{
    private RadioButton score1RadioButton;
    private RadioButton score2RadioButton;
    private RadioButton score3RadioButton;
    private RadioButton score4RadioButton;
    private RadioButton score5RadioButton;
    private TextView mentorCommentPromptTextView;
    private EditText mentorCommentEditText;
    private Button sendMentorCommentButton;

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
    }

    public void setUIValue() {
        //get value from DB
    }

    public void commentMentor() {
        //store value to DB
    }

    @Override
    public void onClick(View v) {

    }
}
