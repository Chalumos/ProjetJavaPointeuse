package fr.univtours.polytech.projet_tutore.model.timetracker;

import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.Schedule;
import fr.univtours.polytech.projet_tutore.model.date.Time;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;

/**
 * Class that represents and allows to edit a ClockingTime.
 */
public class ClockingTime implements Externalizable {
    /**
     * Employee who is reporting.
     */
    private Employee employee;

    /**
     * Date of the clocking time
     */
    private Date date;

    /**
     * Time of clocking time
     */
    private Time time;

    /**
     * Create an empty clocking time.
     */
    public ClockingTime() {
        employee = null;
        date = null;
        time = null;
    }

    /**
     * Create a clocking time with arguments.
     * @param employee The employee of the clocking time.
     * @param date The date of the clocking time.
     * @param time The time of the clocking time.
     */
    public ClockingTime(Employee employee, Date date, Time time) {
        setEmployee(employee);
        setDate(date);
        setTime(time);
    }

    /**
     * Get the employee who is reporting.
     * @return The employee who is reporting.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Get the date of the clocking time.
     * @return The date of the clocking time.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the time of the clocking time.
     * @return The time of the clocking time.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Set the employee who is reporting.
     * @param employee The new employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Set the date of the clocking time.
     * @param date The new date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Set the time of the clocking time.
     * @param time The new time.
     */
    public void setTime(Time time) {
        if (time != null) {
            this.time = time.getTimeRoundedToQuarter();

            // If the time was rounded to the next day.
            if (time.getHour() == 23 && this.time.getHour() == 0 && date != null) {
                LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
                localDate = localDate.plusDays(1);

                try {
                    Date date = new Date(localDate.getDayOfMonth(), localDate.getMonth(), localDate.getYear());
                    setDate(date);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        } else {
            this.time = null;
        }
    }

    @Override
    public String toString() {
        String employee = getEmployee() != null
                ? getEmployee().getFirstName() + " " + getEmployee().getLastName().toUpperCase()
                : "Unknown";
        String date = getDate() != null ? getDate().toString() : "Unknown";
        String time = getTime() != null ? getTime().toString() : "Unknown";

        return "[ " + employee + " | " + date + " | " + time + " ]";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(employee);
        out.writeObject(date);
        out.writeObject(time);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        employee = (Employee) in.readObject();
        date =(Date) in.readObject();
        time = (Time) in.readObject();
    }
}
