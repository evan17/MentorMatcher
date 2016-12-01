package edu.umich.mentormatcher;

// This page is accessed after searching from the intro page and returns a list of mentors based on search criteria.
// Clicking a mentor takes the user to the mentor profile page
// Panpan

import android.app.Activity;
import android.os.Bundle;

public class MentorListReturn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list_return);
    }
}
