package edu.umich.mentormatcher;

/**
 * Created by Zhihao on 12/15/2016.
 */

public class Util {
    public static long currentUid = 0;
    public static String currentSlotId = "";
    public static String currentCommentId = "";

    public void resetAllValues() {
        currentUid = 0;
        currentSlotId = "";
        currentCommentId = "";
    }
}
