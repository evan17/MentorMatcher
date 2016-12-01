package edu.umich.mentormatcher;

// This screen is accessed from the profile management screen in order to choose availabilities
// Kevin

import android.app.Activity;
import android.os.Bundle;

public class MentorAvailability extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_availability);
    }
}
