package fr.univtours.polytech.projet_tutore.model.date;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a schedule.
 */
public class Schedule {
    /**
     * Number of the week in the year.
     */
    private int weekNumber;

    /**
     * The date of the Monday of the week.
     */
    private Date weekDate;

    /**
     * Working days of the week.
     */
    private HashMap<Days, WorkingDay> workingDays;

    /**
     * Create an empty schedule.
     * The date of the week is null and the number is initialized to 0.
     */
    public Schedule() {
        weekNumber = 0;
        weekDate = null;
        workingDays = new HashMap<Days, WorkingDay>();

        for (Days day : Days.values()) {
            workingDays.put(day, new WorkingDay(day, null, null));
        }
    }

    /**
     * Create an empty schedule with week date and number.
     * @param weekNumber The number of the week in the year.
     * @param weekDate The date of the Monday of the week.
     */
    public Schedule(int weekNumber, Date weekDate) {
        this();
        setWeekDate(weekDate);

        try {
            setWeekNumber(weekNumber);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get the week number.
     * @return The number of the week.
     */
    public int getWeekNumber() {
        return weekNumber;
    }

    /**
     * Get the date of the Monday of the week.
     * @return The date of the Monday of the week.
     */
    public Date getWeekDate() {
        return weekDate;
    }

    /**
     * Get the working days of the week.
     * @return The working days of the week.
     */
    public HashMap<Days, WorkingDay> getWorkingDays() {
        return workingDays;
    }

    /**
     * Get the working day specified in argument.
     * @param day The wanted day of the week.
     * @return The corresponding working day.
     */
    public WorkingDay getWorkingDay(Days day) {
        return workingDays.get(day);
    }


    /**
     * Set the number of the week.
     * @param weekNumber The new number of the week.
     * @throws Exception If the week number is incorrect.
     */
    public void setWeekNumber(int weekNumber) throws Exception {
        if (weekNumber >= 1 && weekNumber <= 52) {
            this.weekNumber = weekNumber;
        } else {
            throw new Exception("The number of the week should be between 1 and 52");
        }
    }

    /**
     * Set the date of the Monday of the week.
     * @param weekDate The new date of the Monday of the week.
     */
    public void setWeekDate(Date weekDate) {
        this.weekDate = weekDate;
    }

    /**
     * Set the corresponding working day.
     * @param newWorkingDay The new working day.
     * @throws Exception If the working day is null.
     */
    public void setWorkingDay(WorkingDay newWorkingDay) throws Exception {
        if (newWorkingDay != null) {
            workingDays.replace(newWorkingDay.getDay(), newWorkingDay);
        } else {
            throw new Exception("The working day can't be null.");
        }
    }

    @Override
    public String toString(){
        Days[] days = {Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY,
                Days.THURSDAY, Days.FRIDAY, Days.SATURDAY, Days.SUNDAY};

        String weekNumber = getWeekNumber() != 0 ? String.valueOf(getWeekNumber()) : "?";
        String weekDate = getWeekDate() != null ? getWeekDate().toString() : "Unknown";
        String message = "Week " + weekNumber + " (" + weekDate + ") { \n";

        for (Days day : days) {
            message += "\t- " + workingDays.get(day) + "\n";
        }
        message += "}";

        return message;
    }
}