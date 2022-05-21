package fr.univtours.polytech.projet_tutore.model.date;

import java.time.LocalTime;

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
     * Second of the hour.
     */
    private int second;

    /**
     * Create the time '00:00:00' by default.
     */
    public Time(){
        hour = 0;
        minute = 0;
        second = 0;
    }

    /**
     * Create a time with the hour and minute given in the arguments.
     * @param hour The hour of the day.
     * @param minute The minute of the hour.
     * @param second The second of the minute.
     * @throws Exception If the time is not valid.
     */
    public Time(int hour, int minute, int second) throws Exception{
        this.hour = hour;
        this.minute = minute;
        this.second = second;

        // Check if the date is valid.
        checkIfTimeValid();
    }

    /**
     * Check if the time is valid or not.
     * @throws Exception If the time is not valid.
     */
    private void checkIfTimeValid() throws Exception{
        boolean isValid = false;

        // If the hour is between 0 and 23.
        if (hour >= 0 && hour <= 23) {
            // If the minute is between 0 and 59.
            if (minute >= 0 && minute <= 59) {
                // If the minute is between 0 and 59.
                if (second >= 0 && second <= 59) {
                    isValid = true;
                }
            }
        }

        if (!isValid) {
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
            currentTime = new Time(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return currentTime;
    }


    /**
     * Round the time given in arguments to the nearest quarter.
     * @return The rounded time.
     */
    public Time getTimeRoundedToQuarter() {
        Time roundedTime = null;

        try {
            roundedTime = new Time(hour, minute, 0);
            int rest = roundedTime.getMinute() % 15;

            // If the hour must be rounded to the previous quarter.
            if (rest <= 7) {
                roundedTime.setMinute(roundedTime.getMinute() - rest);
            }
            // If the hour must be rounded to the next quarter.
            else {
                roundedTime.setMinute((roundedTime.getMinute() + (15 - rest)) % 60);

                if (roundedTime.getMinute() == 0) {
                    if (roundedTime.getHour() < 23) {
                        roundedTime.setHour(roundedTime.getHour() + 1);
                    } else {
                        roundedTime.setHour(0);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return roundedTime;
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
     * Get the second of the hour.
     * @return The second of the hour.
     */
    public int getSecond() {
        return second;
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

    /**
     * Set the second of the minute.
     * @param second The new second.
     * @throws Exception If the time is not valid.
     */
    public void setSecond(int second) throws Exception{
        this.second = second;

        // Check if the new date is valid.
        checkIfTimeValid();
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean showSeconds) {
        String message = "";

        if (getHour() < 10){
            message += "0";
        }

        message += getHour() + ":";

        if (getMinute() < 10){
            message += "0";
        }

        message += getMinute();

        if (showSeconds) {
            message += ":";
            
            if (getSecond() < 10) {
                message += "0";
            }

            message += getSecond();
        }

        return message;
    }
}
