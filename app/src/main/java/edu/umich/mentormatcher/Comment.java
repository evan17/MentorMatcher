package edu.umich.mentormatcher;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Random;

/**
 * Created by Zhihao on 11/28/2016.
 */

public class Comment {
    private static final String DB_NAME_COMMENT = "comments";
    private String commentId;
    private int receiverUid;
    private String text;
    private int rating;
    private boolean isMentorComment;
    private Date createTime;

    protected Comment() {    } // Protect default constructor

    private Comment(int receiverUid, int rating, String text, Date createTime, boolean isMentorComment) {
        this.receiverUid = receiverUid;
        this.rating = rating;
        this.text = text;
        this.isMentorComment = isMentorComment;
        this.createTime = createTime;
        this.commentId = generateNewCommentId();
    }

    public boolean modifyComment(int rating, String text) {
        this.rating = rating;
        this.text = text;
        return true;
    }

    public boolean saveToFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference commentRef = database.getReference(DB_NAME_COMMENT);
        if(commentRef == null)
            return false;

        commentRef.child(Integer.toString(receiverUid)).child(commentId).setValue(this);
        return true;
    }

    public static DatabaseReference getCommentDBRefFromFirebase(String commentId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference commentRef = database.getReference(DB_NAME_COMMENT).child(commentId.split("-")[0]);

        return commentRef;
    }

    private String generateNewCommentId() {
        if(this.receiverUid == 0)
            return null;
        Random r = new Random();
        return Integer.toString(this.receiverUid) + "-" + this.createTime + Integer.toString(r.nextInt());
    }
}
