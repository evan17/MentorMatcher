package edu.umich.mentormatcher;

// This page is accessed after searching from the intro page and returns a list of mentors based on search criteria.
// Clicking a mentor takes the user to the mentor profile page
// Panpan

// Added Firebase Auth
// Added Menu

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MentorListReturn extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView Test;
    private ListView LV;
    ArrayList<String> names =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list_return);

        Test=(TextView)findViewById(R.id.textViewTest);
        LV=(ListView)findViewById(R.id.ListViewMentor);

        // Firebase Auth implementation
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MentorListReturn.this, "User signed in: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MentorListReturn.this, "Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MentorListReturn.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        //Firebase database search result query return
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("users").orderByChild("careerAspiration").equalTo("consulting").addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        //User userlist=dataSnapshot.getValue(User.class);
                       //String val=Test.getText().toString();
                      //  val=userlist.name;
                      // Test.setText(val);
                       getUpdate(dataSnapshot);
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
                }
        );

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentMonitor = new Intent(MentorListReturn.this, CareerFunctions.class);

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

    private void getUpdate(DataSnapshot ds){

        //names.clear();
        for(DataSnapshot data:ds.getChildren())
        {
            User mentor=new User();
            mentor.setName(data.getValue(User.class).getName());
            names.add(mentor.getName());
           Toast.makeText(this, mentor.name, Toast.LENGTH_SHORT).show();
        }

            ArrayAdapter adapter=new ArrayAdapter(MentorListReturn.this,android.R.layout.simple_list_item_1,names);
            LV.setAdapter(adapter);

    }


}
