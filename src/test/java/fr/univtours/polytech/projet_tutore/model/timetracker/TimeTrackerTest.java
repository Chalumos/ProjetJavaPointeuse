package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.ArrayList;

public class TimeTrackerTest {
    private TimeTracker timeTracker;

    @BeforeEach
    void setUp() {
        timeTracker = new TimeTracker();
    }

    @Test
    void testTimeTracker() {
        Assertions.assertNotNull(timeTracker.getCurrentTime());
        Assertions.assertNotNull(timeTracker.getCurrentDate());
        Assertions.assertNotNull(timeTracker.getEmployees());
        Assertions.assertEquals(0, timeTracker.getEmployees().size());
    }

    @Test
    void testSetCurrentDate() {
        try {
            Date date = new Date(23, Month.MAY, 2022);
            timeTracker.setCurrentDate(date);

            Assertions.assertEquals(date, timeTracker.getCurrentDate());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSetCurrentTime() {
        try {
            Time time = new Time(10, 30, 0);
            timeTracker.setCurrentTime(time);

            Assertions.assertEquals(time, timeTracker.getCurrentTime());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testToString() {
        try {
            Time time = new Time(10, 30, 0);
            Date date = new Date(23, Month.MAY, 2022);
            ArrayList<Employee> employees = new ArrayList<Employee>();

            for (int i = 0; i < 3; i++) {
                employees.add(new Employee());
            }

            timeTracker.setCurrentTime(time);
            timeTracker.setCurrentDate(date);
            timeTracker.setEmployees(employees);

            String message = "Time-tracker (";
            message += date.toString() + " at " + time.toString() + ", ";
            message += employees.size() + " employees)";

            Assertions.assertEquals(message, timeTracker.toString());

            System.out.println(timeTracker.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
