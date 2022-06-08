package fr.univtours.polytech.projet_tutore.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ScheduleTest {
    private Schedule schedule;

    @BeforeEach
    void setUp() {
        schedule = new Schedule();
    }

    @Test
    void testSchedule() {
        try {
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
}


