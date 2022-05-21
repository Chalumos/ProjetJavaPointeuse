package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Month;

public class ReportingTest {
    @Test
    void constructors() {
        try {
            Reporting reporting = new Reporting();

            Assertions.assertNull(reporting.getEmployee());
            Assertions.assertNull(reporting.getDate());
            Assertions.assertNull(reporting.getTime());

            Employee employee = new Employee("John", "Doe");
            Date date = new Date(21, Month.MAY, 2022);
            Time time = new Time(9, 0, 0);
            reporting = new Reporting(employee, date, time);

            Assertions.assertEquals(employee, reporting.getEmployee());
            Assertions.assertEquals(date, reporting.getDate());
            Assertions.assertEquals(time, reporting.getTime());
        } catch (Exception exception) {
            exception.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void setEmployee() {
        Employee employee1 = new Employee("John", "Doe");
        Employee employee2 = new Employee("Jane","Doe");
        Reporting reporting = new Reporting(employee1, null, null);
        reporting.setEmployee(employee2);

        Assertions.assertEquals(employee2, reporting.getEmployee());
    }

    @Test
    void setDate() {
        try {
            Date date = new Date(15, Month.FEBRUARY, 2022);
            Reporting reporting = new Reporting();

            reporting.setDate(date);

            Assertions.assertEquals(date, reporting.getDate());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void setTime() {
        try {
            Reporting reporting = new Reporting();
            Time time = new Time(15, 10, 50);

            reporting.setTime(time);

            Assertions.assertEquals(time, reporting.getTime());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void toStr() {
        try {
            Employee employee = new Employee("John", "Doe");
            Date date = new Date(21, Month.MAY, 2022);
            Time time = new Time(9, 0, 0);
            Reporting reporting = new Reporting(employee, date, time);

            Assertions.assertEquals("[ John DOE | " + date.toString() + " | " + time.toString() + " ]", reporting.toString());

            reporting = new Reporting();

            Assertions.assertEquals("[ Unknown | Unknown | Unknown ]", reporting.toString());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
