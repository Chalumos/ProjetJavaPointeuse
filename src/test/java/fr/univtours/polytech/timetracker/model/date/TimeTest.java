package fr.univtours.polytech.timetracker.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class TimeTest {
    @Test
    void Time(){
        Time time = new Time();

        Assertions.assertEquals(0, time.getHour());
        Assertions.assertEquals(0, time.getMinute());
    }

    @Test
    void getCurrentTime(){
        LocalTime localTime = LocalTime.now();
        Time currentTime = Time.getCurrentTime();

        Assertions.assertEquals(localTime.getHour(), currentTime.getHour());
        Assertions.assertEquals(localTime.getMinute(), currentTime.getMinute());
    }

    @Test
    void checkIfTimeValid() {
        Assertions.assertThrows(Exception.class, () -> new Time(-5, 2, 0));
        Assertions.assertThrows(Exception.class, () -> new Time(24, 2, 0));
        Assertions.assertThrows(Exception.class, () -> new Time(5, -2, 0));
        Assertions.assertThrows(Exception.class, () -> new Time(22, 60, 0));
    }

    @Test
    void getTimeRoundedToQuarter() {
        Time time;

        try {
            time = new Time(10, 3, 0);
            Assertions.assertEquals("10:00:00", (time).getTimeRoundedToQuarter().toString());
            time = new Time(10, 7, 0);
            Assertions.assertEquals("10:00:00", (time).getTimeRoundedToQuarter().toString());
            time = new Time(10, 8, 0);
            Assertions.assertEquals("10:15:00", (time).getTimeRoundedToQuarter().toString());
            time = new Time(10, 13, 0);
            Assertions.assertEquals("10:15:00", (time).getTimeRoundedToQuarter().toString());

            time = new Time(23, 58, 0);
            Assertions.assertEquals("00:00:00", (time).getTimeRoundedToQuarter().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void setHour() {
        Time time = new Time();

        try {
            Assertions.assertNotEquals(5, time.getHour());
            time.setHour(5);
            Assertions.assertEquals(5, time.getHour());

            Assertions.assertThrows(Exception.class, () -> time.setHour(24));
            Assertions.assertThrows(Exception.class, () -> time.setHour(-1));

        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void setMinute() {
        Time time = new Time();

        try {
            Assertions.assertNotEquals(15, time.getMinute());
            time.setMinute(15);
            Assertions.assertEquals(15, time.getMinute());

            Assertions.assertThrows(Exception.class, () -> time.setMinute(-1));
            Assertions.assertThrows(Exception.class, () -> time.setMinute(60));

        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void toStr() {
        try {
            Assertions.assertEquals("18:53:00", (new Time(18,53, 0)).toString());

            // Special cases.
            Assertions.assertEquals("09:45:01", (new Time(9,45, 1)).toString());
            Assertions.assertEquals("05:45:29", (new Time(5,45, 29)).toString());
            Assertions.assertEquals("12:02:57", (new Time(12,2, 57)).toString());
            Assertions.assertEquals("01:03:05", (new Time(1,3, 5)).toString());

            // Without seconds
            Assertions.assertEquals("23:30", (new Time(23,30, 50)).toString(false));
            Assertions.assertEquals("23:30:50", (new Time(23,30, 50)).toString(true));
        } catch(Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }
}
