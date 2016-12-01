package edu.umich.mentormatcher;

// This is the landing page after login.  It shows upcoming events, averge rating, and profile details
// It has a button to navigate to the profile management screen
// Kevin


import android.app.Activity;
import android.os.Bundle;

public class UserActivityManagementScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management_screen);
    }
}
