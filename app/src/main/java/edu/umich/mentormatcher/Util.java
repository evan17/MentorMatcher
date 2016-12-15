package edu.umich.mentormatcher;

/**
 * Created by Zhihao on 12/15/2016.
 */

public class Util {
    public static long currentUid = 0;
    public static String currentSlotId = "";
    public static String currentCommentId = "";

    public static void resetAllValues() {
        currentUid = 0;
        currentSlotId = "";
        currentCommentId = "";
    }

    public static long getMentorIdFromSlotId(String slotId) {
        String[] words = slotId.split("-");
        return Long.parseLong(words[0]);
    }

    public static long getReceiverIDFromCommentId(String commentId) {
        String[] words = commentId.split("-");
        return Long.parseLong(words[0]);
    }
}
