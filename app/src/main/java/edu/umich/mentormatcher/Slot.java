package edu.umich.mentormatcher;

import java.util.Date;

/**
 * Created by Zhihao on 11/28/2016.
 */

public class Slot {
    private int slotId;
    private Date startTime;
    private Date endTime;
    private Mentor mentor;
    private String typeOfService;

    private boolean isBooked;
    private boolean isConfirmed;
    private Mentee mentee;

    public static Slot getSlotById(int slotId) {
        return null;
    }


}
