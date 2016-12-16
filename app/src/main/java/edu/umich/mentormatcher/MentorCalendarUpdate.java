package edu.umich.mentormatcher;

// This screen is accessed from the profilemanagement screen and allows a mentor to update their availability
// Panpan

// Added Firebase Auth
// Added Menu

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static edu.umich.mentormatcher.R.id.textViewEndTime;

public class MentorCalendarUpdate extends Activity implements View.OnClickListener {

    private TextView textViewSetdate;
    private TextView textViewSettime;
    private TextView textViewEndTime;
    private EditText editTextService;
    private Button buttonSetdate;
    private Button buttonSettime;
    private Button buttonSetEndTime;
    private Button buttonSlotUpdate;
    int mYear,mMonth,mDay,mHour,mMinutes;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_calendar_update);

        textViewSetdate=(TextView)findViewById(R.id.textViewDate);
        textViewSettime=(TextView) findViewById(R.id.textViewTime);
        textViewEndTime=(TextView) findViewById(R.id.textViewEndTime);
        editTextService=(EditText)findViewById(R.id.editTextService);
        buttonSetdate=(Button)findViewById(R.id.buttonSetdate);
        buttonSettime=(Button)findViewById(R.id.buttonSettime);
        buttonSetEndTime=(Button) findViewById(R.id.buttonSetEndTime);
        buttonSlotUpdate=(Button) findViewById(R.id.buttonSlotUpdate);

        buttonSetdate.setOnClickListener(this);
        buttonSettime.setOnClickListener(this);
        buttonSetEndTime.setOnClickListener(this);
        buttonSlotUpdate.setOnClickListener(this);

        // Firebase Auth implementation
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MentorCalendarUpdate.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MentorCalendarUpdate.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MentorCalendarUpdate.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentMonitor = new Intent(MentorCalendarUpdate.this, CareerFunctions.class);

        if (mAuth.getCurrentUser() != null ) {
            if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();

            } else if (item.getItemId() == R.id.menuCareerFunctions) {
                startActivity(intentMonitor);

            }
        } else {
            Toast.makeText(this, "Gotta log in", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigationmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @TargetApi(Build.VERSION_CODES.N)
    public void datePic(){

        final Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                 if(year<mYear) {
                     view.updateDate(mYear, month, dayOfMonth);
                 }
                month=month+1;
                textViewSetdate.setText(month+"-"+dayOfMonth+"-"+year);
            }
        },mDay,mMonth,mYear);

        datePickerDialog.show();

    }
    @TargetApi(Build.VERSION_CODES.N)
    public void TimePic(View v){

        final View newview=v;
        final Calendar c=Calendar.getInstance();
        mHour=c.get(Calendar.HOUR_OF_DAY);
        mMinutes=c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(newview==buttonSettime){
                    textViewSettime.setText(hourOfDay+":"+minute);
                }
                else if(newview==buttonSetEndTime){
                    textViewEndTime.setText(hourOfDay+":"+minute);
                }
            }
        },mHour,mMinutes,false);

        timePickerDialog.show();
    }


    @Override
    public void onClick(View v) {
        if(v==buttonSetdate){
            datePic();
        }
        else if(v==buttonSettime||v==buttonSetEndTime){
            TimePic(v);
        }
        else if(v==buttonSlotUpdate){

            if(textViewSetdate!=null&textViewSettime!=null&textViewEndTime!=null){

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("slots");

                String start=textViewSettime.getText().toString();
                String end= textViewEndTime.getText().toString();
                String date=textViewSetdate.getText().toString();
                String service=editTextService.getText().toString();
                Slot slot = new Slot(start,end,date,1481250845829L,service);
                DatabaseReference newSlotRef = ref.child("1481250845829").push();
                newSlotRef.setValue(slot);
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
