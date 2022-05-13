package fr.univtours.polytech.timetracker.model.date;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private int weekNumber;
    private Date weekDate;
    private Map<Days,WorkingDay> workingDays=new HashMap<Days,WorkingDay>();

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Date getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(Date weekDate) {
        this.weekDate = weekDate;
    }

    public Map<Days, WorkingDay> getWorkingDays() {
        return workingDays;
    }

    public String toString(){
        String message= "this employee is scheduled to work :\n";
        for (Days day:workingDays.keySet()){
            if(day.equals(Days.SATURDAY) || day.equals(Days.SUNDAY)){
                message+="break for "+workingDays.get(day).toString();
            }
            else{
                message+="between "+workingDays.get(day).getStartTime().toString()
                        +" and "+workingDays.get(day).getEndTime().toString();
                message+=" on "+workingDays.get(day).toString()+"\n";
            }

        }
        message+="during the week number "+weekNumber+" of the year "+weekDate.getYear();
        return message;
    }
}
