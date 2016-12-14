package edu.umich.mentormatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class TestActivity extends Activity implements View.OnClickListener{
    private Button addUserButton;
    private Button addSlotButton;
    private Button addCommentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        addCommentButton = (Button) findViewById(R.id.buttonAddCommentTest);
        addSlotButton = (Button) findViewById(R.id.buttonAddSlotTest);
        addUserButton = (Button) findViewById(R.id.buttonAddUserTest);

        addUserButton.setOnClickListener(this);
        addSlotButton.setOnClickListener(this);
        addCommentButton.setOnClickListener(this);
    }

    public void addUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

        User user = new User("a@a.com", "aaaaaa", "userA", "consulting");
        DatabaseReference newBirdRef = ref.push();
        newBirdRef.setValue(user);

        Toast.makeText(TestActivity.this, "User add successfully", Toast.LENGTH_SHORT).show();
    }

    public void addSlot() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("slots");

        Date start = new Date(2016, 12, 30, 18, 30);
        Date end = new Date(2016, 12, 30, 19, 00);

        Slot slot = new Slot(start, end, 1481740845829L, "consulting");
        DatabaseReference newSlotRef = ref.child("1481740845829").push();
        newSlotRef.setValue(slot);

        Toast.makeText(TestActivity.this, "Slot add successfully", Toast.LENGTH_SHORT).show();
    }

    public void addComment() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("comments");

        Comment comment = new Comment(1481740845829L, 4, "very good mentor", new Date(), true);
        DatabaseReference newCommentRef = ref.child("1481740845829").push();
        newCommentRef.setValue(comment);

        Toast.makeText(TestActivity.this, "Comment add successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonAddUserTest:
                addUser();
                break;
            case R.id.buttonAddSlotTest:
                addSlot();
                break;
            case R.id.buttonAddCommentTest:
                addComment();
                break;
        }
    }
}
