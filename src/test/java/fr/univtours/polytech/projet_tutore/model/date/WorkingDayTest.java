package fr.univtours.polytech.projet_tutore.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkingDayTest {
    private WorkingDay workingDay;

    @BeforeEach
    void setUp() {
        workingDay = new WorkingDay();
    }

    @Test
    void testWorkingDay() {
        try {
            Assertions.assertNull(workingDay.getDay());
            Assertions.assertNull(workingDay.getStartTime());
            Assertions.assertNull(workingDay.getEndTime());

            System.out.println(workingDay);

            Time startTime = new Time(9, 0, 0);
            Time endTime = new Time(18, 0, 0);
            workingDay = new WorkingDay(Days.MONDAY, startTime, endTime);

            Assertions.assertEquals(Days.MONDAY, workingDay.getDay());
            Assertions.assertEquals(startTime, workingDay.getStartTime());
            Assertions.assertEquals(endTime, workingDay.getEndTime());
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSetDay(){
        workingDay.setDay(Days.MONDAY);

        Assertions.assertEquals(Days.MONDAY, workingDay.getDay());
    }

    @Test
    void testSetStartTime(){
        try {
            Time endTime = new Time(18, 0, 0);
            workingDay.setEndTime(endTime);

            Assertions.assertDoesNotThrow(() -> workingDay.setStartTime(new Time(9, 30, 0)));
            Assertions.assertEquals(9 ,workingDay.getStartTime().getHour());
            Assertions.assertEquals(30 ,workingDay.getStartTime().getMinute());
            Assertions.assertEquals(0 ,workingDay.getStartTime().getSecond());

            Assertions.assertThrows(Exception.class, () -> workingDay.setStartTime(new Time(20, 0, 0)));
        }
        catch(Exception exception){
            exception.printStackTrace();
            Assertions.fail();
        }

    }

    @Test
    void testSetEndTime() {
        try {
            Time startTime = new Time(8, 0, 0);
            workingDay.setStartTime(startTime);

            Assertions.assertDoesNotThrow(() -> workingDay.setEndTime(new Time(18, 45, 0)));
            Assertions.assertEquals(18, workingDay.getEndTime().getHour());
            Assertions.assertEquals(45, workingDay.getEndTime().getMinute());
            Assertions.assertEquals(0, workingDay.getEndTime().getSecond());

            Assertions.assertThrows(Exception.class, () -> workingDay.setEndTime(new Time(7, 0, 0)));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testToString() {
        try {
            Assertions.assertEquals("Unknown (Unknown - Unknown)", workingDay.toString());

            Time startTime = new Time(9,0,0);
            Time endTime = new Time(17,30,0);
            workingDay = new WorkingDay(Days.MONDAY, startTime, endTime);

            Assertions.assertEquals("MONDAY (09:00:00 - 17:30:00)", workingDay.toString());
        } catch(Exception exception){
            exception.printStackTrace();
            Assertions.fail();
        }
    }
}
