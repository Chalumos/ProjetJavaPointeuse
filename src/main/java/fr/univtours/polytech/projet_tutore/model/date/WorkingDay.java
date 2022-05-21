package fr.univtours.polytech.projet_tutore.model.date;

/**
 * Represents a working day in a schedule.
 */
public class WorkingDay {
    /**
     * Day of work.
     */
    private Days day;

    /**
     * Time when the employee is supposed to start working.
     */
    private Time startTime;

    /**
     * Time when the employee is supposed to stop working.
     */
    private Time endTime;

    /**
     * Create a working day with attributes defined as null.
     */
    public WorkingDay() {
        setDay(null);
        setStartTime(null);
        setEndTime(null);
    }

    /**
     * Create a working day with arguments.
     * @param day The day of working.
     * @param startTime The time when the employee is supposed to start working.
     * @param endTime The time when the employee is supposed to stop working.
     */
    public WorkingDay(Days day, Time startTime, Time endTime) {
        setDay(day);
        setStartTime(startTime);
        setEndTime(endTime);
    }

    /**
     * Get the day of work.
     * @return Day of work.
     */
    public Days getDay() {
        return day;
    }

    /**
     * Get the time when the employee is supposed to start working.
     * @return The time when the employee is supposed to start working.
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Get the time when the employee is supposed to stop working.
     * @return The time when the employee is supposed to stop working.
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Set the day of work.
     * @param day The day of work.
     */
    public void setDay(Days day) {
        this.day = day;
    }

    /**
     * Set the time when the employee is supposed to start working
     * @param startTime The time when the employee is supposed to start working.
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Set the time when the employee is supposed to stop working
     * @param endTime The time when the employee is supposed to stop working.
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String day = getDay() != null ? getDay().toString() : "Unknown";
        String startTime = getStartTime() != null ? getStartTime().toString() : "Unknown";
        String endTime = getEndTime() != null ? getEndTime().toString() : "Unknown";

        return day + " (" + startTime + " - " + endTime + ")";
    }
}
