package edu.umich.mentormatcher;

import java.util.List;

/**
 * Created by Zhihao on 11/28/2016.
 */

// Use FakeUser class for debugging
public class User {
    // Basic & mentee
    protected String email;
    protected String password;
    protected String name;
    protected int uid;
    protected String careerAspiration;

    private boolean isMentor;

    protected User() {
        // Disable public use of default constructor
    }

    private User(String email, String password, String name, String careerAspiration) {
        // TODO: Get UID
        // TODO: Assign Value
    }

    private int generateNewUid() {
        return 0;
    }

    public static User userRegister(String email, String password, String name, String careerAspiration) {
        // String check
        return new User(email, password, name, careerAspiration);
    }

    public static Mentor fillMentorInfo(int uid, String title, String aboutMe, String service, String reward) {
        // no update when int == 0 or string == null
        return null;
    }

    public static User userLogin(String email, String password) {
        return null;
    }

    public static User getUserById(int uid) {
        return null;
    }
}


