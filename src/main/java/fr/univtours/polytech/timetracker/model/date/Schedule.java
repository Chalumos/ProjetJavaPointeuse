package fr.univtours.polytech.timetracker.model.date;

public class Schedule {
    private int weekNumber;
    private Date date;
    private WorkingDay workingDays;

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public WorkingDay getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(WorkingDay workingDays) {
        this.workingDays = workingDays;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
