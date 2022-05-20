package fr.univtours.polytech.timetracker.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLOutput;

public class WorkingDayTest {
    @Test
    void setDay(){
        WorkingDay workingDay= new WorkingDay();
        workingDay.setDay(Days.MONDAY);

        Assertions.assertEquals(Days.MONDAY, workingDay.getDay());
    }

    @Test
    void setStartTime(){
        try{
            WorkingDay workingDay= new WorkingDay();
            Time time = new Time(1,1,1);
            workingDay.setStartTime(time);

            Assertions.assertEquals(01 ,workingDay.getStartTime().getHour());
            Assertions.assertEquals(01 ,workingDay.getStartTime().getMinute());
            Assertions.assertEquals(01 ,workingDay.getStartTime().getSecond());
        }
        catch(Exception exception){
            exception.printStackTrace();
            Assertions.fail();
        }

    }

    @Test
    void setEndTime() {
        try {
            WorkingDay workingDay = new WorkingDay();
            Time time = new Time(1, 1, 1);
            workingDay.setEndTime(time);

            Assertions.assertEquals(01, workingDay.getEndTime().getHour());
            Assertions.assertEquals(01, workingDay.getEndTime().getMinute());
            Assertions.assertEquals(01, workingDay.getEndTime().getSecond());
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void toStr(){
        try{
            WorkingDay workingDay = new WorkingDay();
            Time startTime= new Time(1,1,1);
            Time endTime=new Time(2,2,2);

            workingDay.setDay(Days.MONDAY);
            workingDay.setEndTime(endTime);
            workingDay.setStartTime(startTime);

            Assertions.assertEquals("Date : MONDAY\n\tStarting time : 01:01:01\n\tEnding time : 02:02:02",workingDay.toString());
        }
        catch(Exception exception){
            exception.printStackTrace();
            Assertions.fail();
        }


    }
}
