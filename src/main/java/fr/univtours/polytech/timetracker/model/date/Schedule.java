package fr.univtours.polytech.timetracker.model.date;

import java.time.Month;
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
     * @param weekNumber the number pf the week.
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

class testSchedule{
    public static void main(String[] args) throws Exception {
        Schedule S1 = new Schedule();

        Date today =new Date(13, Month.MAY,2022);

        S1.setWeekDate(today);

        Time start =new Time();
        Time end=new Time();

        start.setHour(8);
        start.setMinute(00);

        end.setHour(18);
        end.setMinute(30);



        for (Days day: Days.values()){
            WorkingDay w =new WorkingDay();
            w.setDay(day);
            w.setStartTime(start);
            w.setEndTime(end);
            S1.getWorkingDays().put(day,w);
        }
        //System.out.println(S1.getWorkingDays().get(Days.MONDAY));
        System.out.println(S1.toString());
    }
}

