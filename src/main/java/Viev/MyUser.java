package Viev;

import java.util.Calendar;
import java.sql.Date;

public class MyUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String languageCode;
    private SubTypes subType;
    private String startDate;
    private int timeShift;

    private boolean isAdmin;
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public SubTypes getSubType() {
        return subType;
    }

    public String getStartDate() {
        return startDate;
//        return startDate.get(Calendar.YEAR) + "-"
//                + (startDate.get(Calendar.MONTH) + 1) + "-"
//                + startDate.get(Calendar.DAY_OF_MONTH);
    }

    public int getTimeShift() {
        return timeShift;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


    public MyUser(Long id, String firstName, String lastName, String languageCode, SubTypes subType, String startDate, int timeShift, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.languageCode = languageCode;
        this.lastName = lastName;
        this.startDate = startDate;
        this.subType = subType;
        this.timeShift = timeShift;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "id: " + id +
                " firstName: " + firstName +
                " lastName: " + lastName +
                " languageCode: " + languageCode +
                " startDate: " + startDate +
                " subType: " + subType +
                " timeShift: " + timeShift +
                " isAdmin: " + isAdmin;
    }
}
