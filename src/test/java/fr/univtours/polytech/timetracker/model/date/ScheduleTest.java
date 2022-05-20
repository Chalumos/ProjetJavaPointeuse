package fr.univtours.polytech.timetracker.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Month;


public class ScheduleTest {
    @Test
    void setWeekNumber(){
        Schedule schedule=new Schedule();
        schedule.setWeekNumber(1);

        Assertions.assertEquals(1,schedule.getWeekNumber());
    }

    @Test
    void setWeekDate(){
        Schedule schedule=new Schedule();
        Date date=new Date();
        schedule.setWeekDate(date);

        Assertions.assertEquals(1, schedule.getWeekDate().getDay());
        Assertions.assertEquals(Month.JANUARY, schedule.getWeekDate().getMonth());
        Assertions.assertEquals(0, schedule.getWeekDate().getYear());
    }

    @Test
    public static void main(String[] args)throws Exception{
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

                System.out.println(S1.toString());
    }
}


