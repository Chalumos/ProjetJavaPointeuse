package fr.univtours.polytech.projet_tutore.model.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class DateTest {
    @Test
    void constructors() {
        Date date = new Date();

        Assertions.assertEquals(1, date.getDay());
        Assertions.assertEquals(Month.JANUARY, date.getMonth());
        Assertions.assertEquals(0, date.getYear());

        Assertions.assertDoesNotThrow(() -> new Date(13, Month.MAY, 2022));
    }

    @Test
    void getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.getCurrentDate();

        Assertions.assertEquals(localDate.getDayOfMonth(), currentDate.getDay());
        Assertions.assertEquals(localDate.getMonth(), currentDate.getMonth());
        Assertions.assertEquals(localDate.getYear(), currentDate.getYear());
    }

    @Test
    void isYearLeap() {
        for (int year = 1980; year <= 2096; year += 4) {
            int finalYear = year;
            Assertions.assertDoesNotThrow(() -> new Date(29, Month.FEBRUARY, finalYear));
        }
    }

    @Test
    void checkIfDateValid() {
        Assertions.assertThrows(Exception.class, () -> new Date(0, Month.JANUARY, 2022));
        Assertions.assertThrows(Exception.class, () -> new Date(32, Month.JANUARY, 2022));

        // Check months of 30 days.
        Assertions.assertThrows(Exception.class, () -> new Date(31, Month.APRIL, 2022));
        Assertions.assertThrows(Exception.class, () -> new Date(31, Month.JUNE, 2022));
        Assertions.assertThrows(Exception.class, () -> new Date(31, Month.SEPTEMBER, 2022));
        Assertions.assertThrows(Exception.class, () -> new Date(31, Month.NOVEMBER, 2022));

        // Check February
        Assertions.assertThrows(Exception.class, () -> new Date(29, Month.FEBRUARY, 2022));
    }

    @Test
    void setDay() {
        Date date = new Date();

        try {
            Assertions.assertNotEquals(23, date.getDay());
            date.setDay(23);
            Assertions.assertEquals(23, date.getDay());

            Assertions.assertThrows(Exception.class, () -> date.setDay(32));

            date.setDay(1);
            date.setMonth(Month.FEBRUARY);
            date.setYear(2022);
            Assertions.assertThrows(Exception.class, () -> date.setDay(29));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void setMonth() {
        Date date;

        try {
            date = new Date(1, Month.JANUARY, 2022);

            date.setMonth(Month.DECEMBER);
            Assertions.assertEquals(Month.DECEMBER, date.getMonth());

            date.setDay(29);
            Assertions.assertThrows(Exception.class, () -> date.setMonth(Month.FEBRUARY));
            Assertions.assertThrows(Exception.class, () -> date.setMonth(null));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void setYear() {
        Date date;

        try {
            date = new Date(29, Month.FEBRUARY, 2020);

            date.setYear(2000);
            Assertions.assertEquals(2000, date.getYear());

            Assertions.assertThrows(Exception.class, () -> date.setYear(2022));
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void toStr() {
        try {
            Assertions.assertEquals("May 13th, 2022", (new Date(13, Month.MAY, 2022)).toString());

            // Special cases.
            Assertions.assertEquals("May 1st, 2022", (new Date(1, Month.MAY, 2022)).toString());
            Assertions.assertEquals("May 2nd, 2022", (new Date(2, Month.MAY, 2022)).toString());
            Assertions.assertEquals("May 3rd, 2022", (new Date(3, Month.MAY, 2022)).toString());
        } catch(Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }
}
