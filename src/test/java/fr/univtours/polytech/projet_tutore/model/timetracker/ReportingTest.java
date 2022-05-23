package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Month;

public class ReportingTest {
    private Reporting reporting;

    @BeforeEach
    void setUp() {
         reporting = new Reporting();
    }

    @Test
    void testReporting() {
        try {
            Assertions.assertNull(reporting.getEmployee());
            Assertions.assertNull(reporting.getDate());
            Assertions.assertNull(reporting.getTime());

            Employee employee = new Employee("John", "Doe");
            Date date = new Date(21, Month.MAY, 2022);
            Time time = new Time(9, 12, 57);
            reporting = new Reporting(employee, date, time);

            Assertions.assertEquals(employee, reporting.getEmployee());
            Assertions.assertEquals(date, reporting.getDate());
            Assertions.assertEquals(9, reporting.getTime().getHour());
            Assertions.assertEquals(15, reporting.getTime().getMinute());
            Assertions.assertEquals(0, reporting.getTime().getSecond());
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSetEmployee() {
        Employee employee1 = new Employee("John", "Doe");
        Employee employee2 = new Employee("Jane","Doe");
        Reporting reporting = new Reporting(employee1, null, null);
        reporting.setEmployee(employee2);

        Assertions.assertEquals(employee2, reporting.getEmployee());
    }

    @Test
    void testSetDate() {
        try {
            Date date = new Date(15, Month.FEBRUARY, 2022);

            reporting.setDate(date);

            Assertions.assertEquals(date, reporting.getDate());
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

            reporting.setTime(time);

            Assertions.assertEquals(15, reporting.getTime().getHour());
            Assertions.assertEquals(30, reporting.getTime().getMinute());
            Assertions.assertEquals(0, reporting.getTime().getSecond());

            time = new Time(23, 59, 59);
            reporting.setDate(date);
            reporting.setTime(time);

            Assertions.assertEquals(0, reporting.getTime().getHour());
            Assertions.assertEquals(0, reporting.getTime().getMinute());
            Assertions.assertEquals(0, reporting.getTime().getSecond());
            Assertions.assertEquals(1, reporting.getDate().getDay());
            Assertions.assertEquals(Month.FEBRUARY, reporting.getDate().getMonth());
            Assertions.assertEquals(2022, reporting.getDate().getYear());
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
            reporting = new Reporting(employee, date, time);

            Assertions.assertEquals("[ John DOE | " + date.toString() + " | " + time.toString() + " ]", reporting.toString());

            reporting = new Reporting();

            Assertions.assertEquals("[ Unknown | Unknown | Unknown ]", reporting.toString());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
