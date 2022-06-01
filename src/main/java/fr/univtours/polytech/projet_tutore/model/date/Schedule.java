package fr.univtours.polytech.projet_tutore.model.date;

import java.util.HashMap;

/**
 * Class that represents a schedule.
 */
public class Schedule {
    /**
     * Working days of the week.
     */
    private HashMap<Days, WorkingDay> workingDays;

    /**
     * Create an empty schedule.
     * The date of the week is null and the number is initialized to 0.
     */
    public Schedule() {
        workingDays = new HashMap<Days, WorkingDay>();

        for (Days day : Days.values()) {
            workingDays.put(day, new WorkingDay());
            workingDays.get(day).setDay(day);
        }
    }

    /**
     * Get the days of the week in the order.
     * @return The days of the week in the order.
     */
    public static Days[] getDaysOfWeek() {
        Days[] days = {Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY, Days.SATURDAY, Days.SUNDAY};

        return days;
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
}