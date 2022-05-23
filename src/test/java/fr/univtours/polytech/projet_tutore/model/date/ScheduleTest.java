package fr.univtours.polytech.projet_tutore.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Month;


public class ScheduleTest {
    private Schedule schedule;

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
    }

    @Test
    void testSchedule() {
        try {
            Assertions.assertEquals(0, schedule.getWeekNumber());
            Assertions.assertNull(schedule.getWeekDate());

            Date date = new Date(16, Month.MAY, 2022);
            schedule = new Schedule(20, date);

            Assertions.assertEquals(20, schedule.getWeekNumber());
            Assertions.assertEquals(date, schedule.getWeekDate());

            String prefix = " (Unknown - Unknown)";
            Assertions.assertEquals("MONDAY" + prefix, schedule.getWorkingDay(Days.MONDAY).toString());
            Assertions.assertEquals("TUESDAY" + prefix, schedule.getWorkingDay(Days.TUESDAY).toString());
            Assertions.assertEquals("WEDNESDAY" + prefix, schedule.getWorkingDay(Days.WEDNESDAY).toString());
            Assertions.assertEquals("THURSDAY" + prefix, schedule.getWorkingDay(Days.THURSDAY).toString());
            Assertions.assertEquals("FRIDAY" + prefix, schedule.getWorkingDay(Days.FRIDAY).toString());
            Assertions.assertEquals("SATURDAY" + prefix, schedule.getWorkingDay(Days.SATURDAY).toString());
            Assertions.assertEquals("SUNDAY" + prefix, schedule.getWorkingDay(Days.SUNDAY).toString());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSetWeekNumber(){
        try {
            schedule.setWeekNumber(1);

            Assertions.assertEquals(1, schedule.getWeekNumber());
        } catch(Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSetWeekDate(){
        Date date = new Date();
        schedule.setWeekDate(date);

        Assertions.assertEquals(1, schedule.getWeekDate().getDay());
        Assertions.assertEquals(Month.JANUARY, schedule.getWeekDate().getMonth());
        Assertions.assertEquals(0, schedule.getWeekDate().getYear());
    }

    @Test
    void testSetWorkingDay() {
        try {
            Time startTime = new Time(9, 0, 0);
            Time endTime = new Time(18, 0, 0);

            schedule.setWorkingDay(new WorkingDay(Days.MONDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.TUESDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.WEDNESDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.THURSDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.FRIDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.SATURDAY, null, null));
            schedule.setWorkingDay(new WorkingDay(Days.SUNDAY, null, null));

            Assertions.assertNotNull(schedule.getWorkingDay(Days.MONDAY));
            Assertions.assertNotNull(schedule.getWorkingDay(Days.TUESDAY));
            Assertions.assertNotNull(schedule.getWorkingDay(Days.WEDNESDAY));
            Assertions.assertNotNull(schedule.getWorkingDay(Days.THURSDAY));
            Assertions.assertNotNull(schedule.getWorkingDay(Days.FRIDAY));
            Assertions.assertNotNull(schedule.getWorkingDay(Days.SATURDAY));
            Assertions.assertNotNull(schedule.getWorkingDay(Days.SUNDAY));

            System.out.println(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testToString() {
        try {
            schedule = new Schedule(20, new Date());
            Time startTime = new Time(9, 0, 0);
            Time endTime = new Time(18, 0, 0);

            schedule.setWorkingDay(new WorkingDay(Days.MONDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.TUESDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.WEDNESDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.THURSDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.FRIDAY, startTime, endTime));
            schedule.setWorkingDay(new WorkingDay(Days.SATURDAY, null, null));
            schedule.setWorkingDay(new WorkingDay(Days.SUNDAY, null, null));

            String description = "Week 20 (January 1st, 0) { \n" +
                    "\t- MONDAY (09:00:00 - 18:00:00)\n" +
                    "\t- TUESDAY (09:00:00 - 18:00:00)\n" +
                    "\t- WEDNESDAY (09:00:00 - 18:00:00)\n" +
                    "\t- THURSDAY (09:00:00 - 18:00:00)\n" +
                    "\t- FRIDAY (09:00:00 - 18:00:00)\n" +
                    "\t- SATURDAY (Unknown - Unknown)\n" +
                    "\t- SUNDAY (Unknown - Unknown)\n" +
                    "}";

            Assertions.assertEquals(description, schedule.toString());

            schedule = new Schedule();

            description = "Week ? (Unknown) { \n" +
                    "\t- MONDAY (Unknown - Unknown)\n" +
                    "\t- TUESDAY (Unknown - Unknown)\n" +
                    "\t- WEDNESDAY (Unknown - Unknown)\n" +
                    "\t- THURSDAY (Unknown - Unknown)\n" +
                    "\t- FRIDAY (Unknown - Unknown)\n" +
                    "\t- SATURDAY (Unknown - Unknown)\n" +
                    "\t- SUNDAY (Unknown - Unknown)\n" +
                    "}";

            Assertions.assertEquals(description, schedule.toString());
        } catch(Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }
}


