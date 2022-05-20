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
}


