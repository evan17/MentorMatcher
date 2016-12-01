package edu.umich.mentormatcher;

// This page is accessed by the mentee from the MentorReview page for the mentee to request booking of the mentor
// Panpan

import android.app.Activity;
import android.os.Bundle;

public class MentorBooking extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_booking);
    }
}
