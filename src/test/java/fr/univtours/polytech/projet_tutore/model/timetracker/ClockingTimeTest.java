package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Month;

public class ClockingTimeTest {
    private ClockingTime clockingTime;

    @BeforeEach
    void setUp() {
         clockingTime = new ClockingTime();
    }

    @Test
    void testClockingTime() {
        try {
            Assertions.assertNull(clockingTime.getEmployee());
            Assertions.assertNull(clockingTime.getDate());
            Assertions.assertNull(clockingTime.getTime());

            Employee employee = new Employee("John", "Doe");
            Date date = new Date(21, Month.MAY, 2022);
            Time time = new Time(9, 12, 57);
            clockingTime = new ClockingTime(employee, date, time);

            Assertions.assertEquals(employee, clockingTime.getEmployee());
            Assertions.assertEquals(date, clockingTime.getDate());
            Assertions.assertEquals(9, clockingTime.getTime().getHour());
            Assertions.assertEquals(15, clockingTime.getTime().getMinute());
            Assertions.assertEquals(0, clockingTime.getTime().getSecond());
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSetEmployee() {
        Employee employee1 = new Employee("John", "Doe");
        Employee employee2 = new Employee("Jane","Doe");
        ClockingTime clockingTime = new ClockingTime(employee1, null, null);
        clockingTime.setEmployee(employee2);

        Assertions.assertEquals(employee2, clockingTime.getEmployee());
    }

    @Test
    void testSetDate() {
        try {
            Date date = new Date(15, Month.FEBRUARY, 2022);

            clockingTime.setDate(date);

            Assertions.assertEquals(date, clockingTime.getDate());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testSetTime() {
        try {
            Date date = new Date(31, Month.JANUARY, 2022);
            Time time = new Time(15, 27, 26);

            clockingTime.setTime(time);

            Assertions.assertEquals(15, clockingTime.getTime().getHour());
            Assertions.assertEquals(30, clockingTime.getTime().getMinute());
            Assertions.assertEquals(0, clockingTime.getTime().getSecond());

            time = new Time(23, 59, 59);
            clockingTime.setDate(date);
            clockingTime.setTime(time);

            Assertions.assertEquals(0, clockingTime.getTime().getHour());
            Assertions.assertEquals(0, clockingTime.getTime().getMinute());
            Assertions.assertEquals(0, clockingTime.getTime().getSecond());
            Assertions.assertEquals(1, clockingTime.getDate().getDay());
            Assertions.assertEquals(Month.FEBRUARY, clockingTime.getDate().getMonth());
            Assertions.assertEquals(2022, clockingTime.getDate().getYear());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void testToString() {
        try {
            Employee employee = new Employee("John", "Doe");
            Date date = new Date(21, Month.MAY, 2022);
            Time time = new Time(9, 0, 0);
            clockingTime = new ClockingTime(employee, date, time);

            Assertions.assertEquals("[ John DOE | " + date.toString() + " | " + time.toString() + " ]", clockingTime.toString());

            clockingTime = new ClockingTime();

            Assertions.assertEquals("[ Unknown | Unknown | Unknown ]", clockingTime.toString());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
