package edu.umich.mentormatcher;

// This screen is accessed from the profilemanagement screen and allows a mentor to update their availability
// Panpan

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MentorCalendarUpdate extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_calendar_update);
    }
}
