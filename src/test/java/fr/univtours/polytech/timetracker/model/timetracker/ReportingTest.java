package fr.univtours.polytech.timetracker.model.timetracker;

import fr.univtours.polytech.timetracker.model.company.Department;
import fr.univtours.polytech.timetracker.model.date.Date;
import fr.univtours.polytech.timetracker.model.date.Time;
import fr.univtours.polytech.timetracker.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Month;

public class ReportingTest {

    @Test
    void setEmployee() {
        try {
            Department administration = new Department("Administration");
            Employee pierreTruc = new Employee("Truc", "Pierre", administration);
            Employee mathildeMachin = new Employee("Machin","Mathilde", administration);
            Reporting reporting = new Reporting(pierreTruc, Date.getCurrentDate(), Time.getCurrentTime());
            reporting.setEmployee(mathildeMachin);

            Assertions.assertEquals(mathildeMachin, reporting.getEmployee());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void setDate() {
        try {
            Department administration = new Department("Administration");
            Employee pierreTruc = new Employee("Truc", "Pierre", administration);
            Reporting reporting = new Reporting(pierreTruc, Date.getCurrentDate(), Time.getCurrentTime());
            Date date = new Date(15, Month.FEBRUARY, 2022);

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
            Department administration = new Department("Administration");
            Employee pierreTruc = new Employee("Truc", "Pierre", administration);
            Reporting reporting = new Reporting(pierreTruc, Date.getCurrentDate(), Time.getCurrentTime());
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
            Department administration = new Department("Administration");
            Employee pierreTruc = new Employee( "Truc", "Pierre", administration);
            Reporting reporting = new Reporting(pierreTruc, Date.getCurrentDate(), Time.getCurrentTime());

            Assertions.assertEquals("[ Pierre TRUC | " + Date.getCurrentDate() + " | " + Time.getCurrentTime() + " ]", reporting.toString());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
