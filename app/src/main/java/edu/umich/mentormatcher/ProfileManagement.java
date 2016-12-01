package edu.umich.mentormatcher;

// This page is accessed from the landing page - useractivitymanagement - by mentors and mentees
// to access their upcomcing events.
// The mentor view has two buttons: "Update my Calendar" and "Update my Profile"
// The former goes to the calendar screen.  The latter goes to the update profile screen (to be built by Chidi and Lizzie)
// Kevin

import android.app.Activity;
import android.os.Bundle;

public class ProfileManagement extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
    }
}
