package edu.umich.mentormatcher;

// This is the Mentor profile list viewed by the mentee.  The mentee chooses which service to use.
// The mentee can then go to the mentor booking page

import android.app.Activity;
import android.os.Bundle;

public class MentorReview extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_review);
    }
}
