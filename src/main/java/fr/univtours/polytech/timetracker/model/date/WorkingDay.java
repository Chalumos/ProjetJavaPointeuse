package fr.univtours.polytech.timetracker.model.date;

public class WorkingDay
{
    /**
     * Day of work.
     */
    private Days day;

    /**
     * Time when the employee has supposed to start working.
     */
    private Time startTime;

    /**
     * Time when the employee has supposed to stop working.
     */
    private Time endTime;

    /**
     * Return the day of work.
     * @return Day of work.
     */
    public Days getDay() {
        return day;
    }

    /**
     * Set the day of work.
     * @param day The day of work.
     */
    public void setDay(Days day) {
        this.day = day;
    }

    /**
     * Return the time when the employee has supposed to start working.
     * @return The time when the employee has supposed to start working.
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Set the time when the employee has supposed to start working
     * @param startTime The time when the employee has supposed to start working.
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Return the time when the employee has supposed to stop working.
     * @return The time when the employee has supposed to stop working.
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Set the time when the employee has supposed to stop working
     * @param endTime The time when the employee has supposed to stop working.
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * Return the period of time when an employee is supposed to work in the day.
     * @return The period of time when an employee is supposed to work in the day.
     */
    @Override
    public String toString() {
        String message = "Date : " + getDay()
                + "\n\tStarting time : " + getStartTime()
                + "\n\tEnding time : " + getEndTime();
        return message;
    }
}
