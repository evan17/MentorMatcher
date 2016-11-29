package edu.umich.mentormatcher;

import java.util.Date;
import java.util.List;

/**
 * Created by Zhihao on 11/29/2016.
 */

public class Mentor extends User {
    // Mentor related
    private String title;
    private String aboutMe;
    private String reward;
    private List<String> serviceList;

    private int mentorRating;
    private List<Comment> commentList;

    public Slot getSlotById(int slotId) {
        return null;
    }

    // Mentor related functions
    public Slot addSlot(Date startTime, Date endTime, String typeOfService) {
        //TODO: parameters
        return null;
    }

    public boolean editSlot(int slotId, Date startTime, Date endTime, String typeOfService) {
        return false;
    }

    public boolean deleteSlot(int slotId) {
        return false;
    }

    public List<Slot> getPendingAppointmentList() {
        return null;
    }

    public List<Slot> getAppointmentList() {
        return null;
    }

    public boolean confirmAppointment(int slotId, String msg) {
        //TODO: firebase
        return false;
    }

    public boolean rejectAppointment(int slotId, String msg) {
        //TODO: firebase
        return false;
    }

    public boolean commentAppointment(int slotId, int rating, String text) {
        return false;
    }
}
