package fr.univtours.polytech.projet_tutore.model.date;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Represents a working day in a schedule.
 */
public class WorkingDay implements Externalizable {
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
        startTime = null;
        endTime = null;
    }

    /**
     * Create a working day with arguments.
     * @param day The day of working.
     * @param startTime The time when the employee is supposed to start working.
     * @param endTime The time when the employee is supposed to stop working.
     */
    public WorkingDay(Days day, Time startTime, Time endTime) throws Exception {
        setDay(day);
        this.startTime = startTime;
        this.endTime = endTime;
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
     * @throws Exception If the new starting time is not before the ending time.
     */
    public void setStartTime(Time startTime) throws Exception {
        if (startTime != null && getEndTime() != null && startTime.compareTo(getEndTime()) >= 0) {
            throw new Exception("The starting time should be before the ending time.");
        } else {
            this.startTime = startTime;
        }
    }

    /**
     * Set the time when the employee is supposed to stop working
     * @param endTime The time when the employee is supposed to stop working.
     * @throws Exception If the new starting time is not before the ending time.
     */
    public void setEndTime(Time endTime) throws Exception {
        if (endTime != null && getStartTime() != null && endTime.compareTo(getStartTime()) <= 0) {
            throw new Exception("The ending time should be after the starting time.");
        } else {
            this.endTime = endTime;
        }
    }

    @Override
    public String toString() {
        String day = getDay() != null ? getDay().toString() : "Unknown";
        String startTime = getStartTime() != null ? getStartTime().toString() : "Unknown";
        String endTime = getEndTime() != null ? getEndTime().toString() : "Unknown";

        return day + " (" + startTime + " - " + endTime + ")";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this!=null){
            out.writeObject(day);
            out.writeObject(startTime);
            out.writeObject(endTime);
        }
        else{
            throw new IOException();
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        day = (Days) in.readObject();
        startTime = (Time) in.readObject();
        endTime = (Time) in.readObject();
    }
}
