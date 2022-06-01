package fr.univtours.polytech.projet_tutore.model.date;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

/**
 * Class that represents and allows to edit a date.
 */
public class Date implements Externalizable {
     /**
     * Day (number of the month) of the date.
     */
    private int day;

    /**
     * Month of the date
     */
    private Month month;

    /**
     * Year of the date
     */
    private int year;

    /**
     * Create the date 'January 1st, 0' by default.
     */
    public Date() {
        day = 1;
        month = Month.JANUARY;
        year = 0;
    }

    /**
     * Create a date with the day, month and year given in the arguments.
     * @param day The day of the date.
     * @param month The month of the date.
     * @param year The year of the date.
     * @throws Exception If the date is not valid.
     */
    public Date(int day, Month month, int year) throws Exception {
        this.day = day;
        this.month = month;
        this.year = year;

        checkIfDateValid();
    }

    /**
     * Get the current date.
     * The date may be null if a problem to get the current date has occurred.
     * @return The current date.
     */
    static public Date getCurrentDate() {
        Date currentDate = null;
        LocalDate localDate = LocalDate.now();

        try {
            currentDate = new Date(localDate.getDayOfMonth(), localDate.getMonth(), localDate.getYear());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return currentDate;
    }

    /**
     * Indicate if the year of the date is leap or not.
     * @param year The year to test.
     * @return If the year is leap or not.
     */
    private boolean isYearLeap(int year) {
        boolean isLeap = false;

        if (year % 4 == 0)
        {
            if (year % 100 == 0)
            {
                if (year % 400 == 0) {
                    isLeap = true;
                }
            } else {
                isLeap = true;
            }
        }

        return isLeap;
    }

    /**
     * Check if the date is valid or not.
     * @throws Exception If the date is not valid.
     */
    private void checkIfDateValid() throws Exception {
        boolean isValid;

        // If the day isn't between 0 and 31.
        if (day < 1 || day > 31) {
            isValid = false;
        }
        // If the day is 31 but the month have only 30 days.
        else if (day == 31 &&
                (month == Month.APRIL || month == Month.JUNE || month == Month.SEPTEMBER || month == Month.NOVEMBER)) {
            isValid = false;
        }
        // If the day is bigger than 28 but the month is February and the year is not leap.
        else if (day > 28 && month == Month.FEBRUARY && !isYearLeap(year)) {
            isValid = false;
        }
        // If the day is bigger than 29 but the month is February and the year is leap.
        else if (day > 29 && month == Month.FEBRUARY && isYearLeap(year)) {
            isValid = false;
        }
        // If the date is valid.
        else {
            isValid = true;
        }

        if (!isValid) {
            throw new Exception("The date '" + toString() + "' is not valid.");
        }
    }

    /**
     * Get the day of the date.
     * @return The day of the date.
     */
    public int getDay() {
        return day;
    }

    /**
     * Get the month of the date.
     * @return The month of the date.
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Get the year of the date.
     * @return The year of the date.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the day of the date.
     * @param day The new day.
     * @throws Exception If the date is not valid.
     */
    public void setDay(int day) throws Exception {
        this.day = day;

        // Check if the new date is valid.
        checkIfDateValid();
    }

    /**
     * Set the month of the date.
     * @param month The new month.
     * @throws Exception If the date is not valid.
     */
    public void setMonth(Month month) throws Exception {
        if (month == null) {
            throw new Exception("The month can't be null.");
        }

        this.month = month;

        // Check if the new date is valid.
        checkIfDateValid();
    }

    /**
     * Set the year of the date.
     * @param year The new year.
     * @throws Exception If the date is not valid.
     */
    public void setYear(int year) throws Exception {
        this.year = year;

        // Check if the new date is valid.
        checkIfDateValid();
    }

    @Override
    public String toString() {
        String dayExtension = switch (getDay()) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };

        String month = getMonth().toString().toLowerCase(Locale.ROOT);
        month = month.substring(0, 1).toUpperCase() + month.substring(1);

        return month + " " + getDay() + dayExtension + ", " + getYear();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this!=null){
            out.writeObject(day);
            out.writeObject(month);
            out.writeObject(year);
        }
        else{
            throw new IOException();
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        day = (int) in.readObject();
        month =(Month) in.readObject();
        year = (int) in.readObject();
    }
}
