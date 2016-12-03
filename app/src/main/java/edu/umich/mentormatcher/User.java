package edu.umich.mentormatcher;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Zhihao on 11/28/2016.
 */

// Use FakeUser class for debugging
public class User {
    private static final String DB_NAME_USER = "users";
    protected String email;
    protected String password;
    protected String name;
    protected long uid;
    protected String careerAspiration;

    private boolean isMentor;

    protected User() {
        // Disable public use of default constructor
    }

    public User(String email, String password, String name, String careerAspiration) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.careerAspiration = careerAspiration;

        this.uid = generateNewUid();
        this.isMentor = false;
    }

    private long generateNewUid() {
        if(uid != 0)
            return uid;

        Date date = new Date();
        Random rand = new Random();
        return date.getTime() * 100 + rand.nextInt(99);
    }

    public static DatabaseReference getUserDBRefFromFirebase(long uid) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference(DB_NAME_USER).child(Long.toString(uid));

        return userRef;
    }

    protected boolean saveToFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference commentRef = database.getReference(DB_NAME_USER);
        if(commentRef == null)
            return false;

        commentRef.child(Long.toString(uid)).setValue(this);
        return true;
    }
}


