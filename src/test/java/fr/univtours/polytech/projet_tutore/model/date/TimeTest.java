package fr.univtours.polytech.projet_tutore.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class TimeTest {
    private Time time;

    @BeforeEach
    void setUp() {
        time = new Time();
    }

    @Test
    void testTime() {
        Assertions.assertEquals(0, time.getHour());
        Assertions.assertEquals(0, time.getMinute());
    }

    @Test
    void testCompareTo() {
        try {
            Time time1 = new Time(8, 0, 0);
            Time time2 = new Time(8, 30, 0);
            Time time3 = new Time(8, 30, 30);
            Time time4 = new Time(9, 0, 0);

            Assertions.assertTrue(time1.compareTo(time2) < 0);
            Assertions.assertTrue(time1.compareTo(time3) < 0);
            Assertions.assertTrue(time1.compareTo(time4) < 0);

            Assertions.assertEquals(0, time1.compareTo(time1));
            Assertions.assertEquals(0, time2.compareTo(time2));
            Assertions.assertEquals(0, time3.compareTo(time3));

            Assertions.assertTrue(time2.compareTo(time1) > 0);
            Assertions.assertTrue(time3.compareTo(time2) > 0);
            Assertions.assertTrue(time4.compareTo(time3) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testGetCurrentTime(){
        LocalTime localTime = LocalTime.now();
        Time currentTime = Time.getCurrentTime();

        Assertions.assertEquals(localTime.getHour(), currentTime.getHour());
        Assertions.assertEquals(localTime.getMinute(), currentTime.getMinute());
    }

    @Test
    void testCheckIfTimeValid() {
        Assertions.assertThrows(Exception.class, () -> new Time(-5, 2, 0));
        Assertions.assertThrows(Exception.class, () -> new Time(24, 2, 0));
        Assertions.assertThrows(Exception.class, () -> new Time(5, -2, 0));
        Assertions.assertThrows(Exception.class, () -> new Time(22, 60, 0));
    }

    @Test
    void testGetTimeRoundedToQuarter() {
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
    void testSetHour() {
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
    void testSetMinute() {
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
    void testToString() {
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

