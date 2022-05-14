package fr.univtours.polytech.timetracker.model.date;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a schedule.
 */
public class Schedule {
    /**
     * Number of the week
     */
    private int weekNumber;
    /**
     * The number of the day and the year.
     */
    private Date weekDate;
    /**
     * A hashMap where the key is days and the value is an Object "WorkingDay".
     */
    private Map<Days,WorkingDay> workingDays=new HashMap<Days,WorkingDay>();

    /**
     * Get the week number.
     * @return the number of the week.
     */
    public int getWeekNumber() {
        return weekNumber;
    }
    /**
     * Set the week number.
     */
    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }
    /**
     * Get the WeekDate which contains the number of the day and the number of the year.
     * @return a WeekDate.
     */
    public Date getWeekDate() {
        return weekDate;
    }
    /**
     * Set the WeekDate.
     */
    public void setWeekDate(Date weekDate) {
        this.weekDate = weekDate;
    }
    /**
     * Get the HashMap WorkingDays.
     * @return a workingDays.
     */
    public Map<Days, WorkingDay> getWorkingDays() {
        return workingDays;
    }


    @Override
    public String toString(){
        String message= "this employee is scheduled to work :\n";
        for (Days day:workingDays.keySet()){
            if(day.equals(Days.SATURDAY) || day.equals(Days.SUNDAY)){
                message += "break for "+workingDays.get(day).toString();
            }
            else{
                message += "between "+workingDays.get(day).getStartTime().toString()
                        +" and "+workingDays.get(day).getEndTime().toString();
                message+=" on "+workingDays.get(day).toString()+"\n";
            }

        }
        message+="during the week number "+weekNumber+" of the year "+weekDate.getYear();
        return message;
    }
}