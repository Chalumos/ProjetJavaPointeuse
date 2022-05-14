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

/*
class testSchedule{
    public static void main(String[] args){
        Schedule S1 = new Schedule();

        Date today =new Date();
        today.setDay(13);
        today.setYear(2022);
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

*/
