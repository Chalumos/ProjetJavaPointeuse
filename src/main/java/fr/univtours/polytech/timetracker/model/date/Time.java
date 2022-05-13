package fr.univtours.polytech.timetracker.model.date;

import java.time.LocalTime;
import java.time.Month;

/**
 * Class that represents and allows to edit a time.
 */
public class Time {
    /**
     * Hour of the day.
     */
    private int hour;

    /**
     * Minute of the hour.
     */
    private int minute;

    /**
     * Create the time '00:00' by default.
     */
    public Time(){
        hour = 0;
        minute = 0;
    }

    /**
     * Create a time with the hour and minute given in the arguments.
     * @param hour The hour of the day.
     * @param minute The minute of the hour.
     * @throws Exception If the time is not valid.
     */
    public Time(int hour, int minute) throws Exception{
        this.hour = hour;
        this.minute = minute;

        // Check if the date is valid.
        checkIfTimeValid();
    }

    /**
     * Check if the time is valid or not.
     * @throws Exception If the time is not valid.
     */
    private void checkIfTimeValid() throws Exception{
        boolean isValid;
        // If the hour is between 0 and 23.
        if (hour >= 0 && hour <= 23) {
            // If the minute is between 0 and 59.
            if (minute >= 0 && minute <= 59){
                isValid = true;
            }
            else{
                isValid = false;
            }
        }
        // If the time is invalid.
        else {
            isValid = false;
        }

        if (isValid != true) {
            throw new Exception("The time '" + toString() + "' is not valid.");
        }
    }

    /**
     * Get the current time.
     * The time may be null if a problem to get the current time has occurred.
     * @return The current time.
     */
    static public Time getCurrentTime() {
        Time currentTime = null;
        LocalTime localTime = LocalTime.now();

        try {
            currentTime = new Time(localTime.getHour(), localTime.getMinute());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return currentTime;
    }

    /**
     * Get the hour of the day.
     * @return The hour of the day.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get the minute of the hour.
     * @return The minute of the hour.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Set the hour of the day.
     * @param hour The new hour.
     * @throws Exception If the time is not valid.
     */
    public void setHour(int hour) throws Exception{
        this.hour = hour;

        // Check if the new date is valid.
        checkIfTimeValid();
    }

    /**
     * Set the minute of the hour.
     * @param minute The new minute.
     * @throws Exception If the time is not valid.
     */
    public void setMinute(int minute) throws Exception{
        this.minute = minute;

        // Check if the new date is valid.
        checkIfTimeValid();
    }

    @Override
    public String toString() {
        String message = "";
        if (getHour() < 10){
            message += "0";
        }
        message += getHour() + ":";
        if (getMinute() < 10){
            message += "0";
        }
        message += getMinute();


        return message;
    }
}
