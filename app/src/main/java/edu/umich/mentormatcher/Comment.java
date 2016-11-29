package edu.umich.mentormatcher;

/**
 * Created by Zhihao on 11/28/2016.
 */

public class Comment {
    private int commentId;
    private String commentText;
    private int rating;
    private boolean isMentorComment;

    private Comment() {
        // Disable default constructor
    }

    public Comment(int rating, String commentText) {

    }

    public Comment getCommentById(int commentId) {
        return null;
    }
}
