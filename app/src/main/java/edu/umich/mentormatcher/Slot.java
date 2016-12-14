package edu.umich.mentormatcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Random;

/**
 * Created by Zhihao on 11/28/2016.
 */

public class Slot {
    private static final String DB_NAME_SLOT = "slots";
    public String slotId;
    public Date startTime;
    public Date endTime;
    public long mentorUid;
    public String typeOfService;

    public boolean isBooked;
    public boolean isConfirmed;
    public long menteeUid;

    protected Slot() {} // Protect default constructor
    public Slot(Date startTime, Date endTime, long mentorUid, String typeOfService) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.mentorUid = mentorUid;
        this.typeOfService = typeOfService;
        this.isBooked = false;
        this.isConfirmed = false;
        this.menteeUid = 0;
        this.slotId = generateNewSlotId();
    }

    public boolean setAsBooked(long menteeUid) {
        if(this.isBooked)
            return false;

        this.isBooked = true;
        this.menteeUid = menteeUid;
        return true;
    }

    public boolean setAsConfirmed(long mentorUid) {
        if(this.isBooked && mentorUid == this.mentorUid) {
            this.isConfirmed = true;
            return true;
        }
        return false;
    }

    public static DatabaseReference getSlotDBRefFromFirebase(String slotId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference slotRef = database.getReference(DB_NAME_SLOT).child(slotId.split("-")[0]);

        return slotRef;
    }

    private boolean saveToFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference slotRef = database.getReference(DB_NAME_SLOT);
        if(slotRef == null)
            return false;

        slotRef.child(Long.toString(mentorUid)).child(slotId).setValue(this);
        return true;
    }

    private String generateNewSlotId() {
        if(this.mentorUid == 0)
            return null;
        Random r = new Random();
        return Long.toString(this.mentorUid) + "-" + this.startTime.toString() + Integer.toString(r.nextInt());
    }
}
