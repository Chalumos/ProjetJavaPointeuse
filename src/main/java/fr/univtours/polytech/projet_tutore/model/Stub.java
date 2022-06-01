package fr.univtours.polytech.projet_tutore.model;

import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.date.*;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;

import java.time.Month;
import java.util.ArrayList;
import java.util.Random;

public class Stub {
    /** Company */
    private static Company company;

    /** A list of Department in a Company */
    private static ArrayList<Department> departmentList;

    /** A list of Schedule */
    private static ArrayList<Schedule> scheduleList;

    /** A list of Employee of a Department */
    private static ArrayList<Employee> employeeList;

    /** A list of clocking time made by employees */
    private static ArrayList<ClockingTime> clockingTimeList;

    /**
     * Generate a company.
     * @return The company with all the components.
     */
    public static Company generateCompany() {
        company = createCompany();
        departmentList = new ArrayList<Department>();
        createDepartmentList();
        scheduleList = new ArrayList<Schedule>();
        createScheduleList();
        employeeList = new ArrayList<Employee>();
        createEmployeeList();
        clockingTimeList = new ArrayList<ClockingTime>();
        createClockingTimeList();
        return company;
    }

    /**
     * Create a company
     * @return The created company
     */
    private static Company createCompany(){
        Company company = new Company("CompanyName");
        return company;
    }

    /**
     * Create a list of Department in a company.
     */
    private static void createDepartmentList() {
        try {
            departmentList.add(new Department("Executive Management"));
            departmentList.add(new Department("Human Resources"));
            departmentList.add(new Department("Marketing"));
            departmentList.add(new Department("Juridical"));
            departmentList.add(new Department("Logistics"));

            for (Department dep : departmentList) {
                company.addDepartment(dep);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * Create a list of schedule.
     */
    private static void createScheduleList() {
        try {
            // Create 3 schedules for 3 weeks.
            for (int week = 18; week < 21; week++) {
                for (int numberSchedule = 1; numberSchedule <= 15; numberSchedule++) {
                    Schedule scheduleWeek = new Schedule();
                    for (Days days : Days.values()) {
                        // If the day is a day off (weekEnd)
                        if (days == Days.SATURDAY || days == Days.SUNDAY) {
                            scheduleWeek.setWorkingDay(new WorkingDay(days, null, null));
                        } else {
                            // Create a schedule with random hour and minute
                            int randMinute = new Random().nextInt(4); // De 0 à 3
                            int randHour = -1 + new Random().nextInt(4); // De -1 à 2
                            scheduleWeek.setWorkingDay(new WorkingDay(days, new Time(8 + randHour, 15 * randMinute, 0), new Time(17 + randHour, 15 * randMinute, 0)));
                        }
                        scheduleWeek.setWeekDate(new Date(2 + 7 * (week % 18), Month.MAY, 2022));
                        scheduleWeek.setWeekNumber(week);
                    }
                    scheduleList.add(scheduleWeek);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Create a list of employees in a department with their schedules.
     */
    private static void createEmployeeList(){
        try {
            // We work on a single week
            employeeList.add(new Employee("Chris", "Simmons", scheduleList.get(0)));
            employeeList.add(new Employee("Lila", "Mann", scheduleList.get(1)));
            employeeList.add(new Employee("Pam", "Johnston", scheduleList.get(2)));
            employeeList.add(new Employee("Merle", "Mccarthy", scheduleList.get(3)));
            employeeList.add(new Employee("Jeffery", "Wong", scheduleList.get(4)));
            employeeList.add(new Employee("Simon", "Morton", scheduleList.get(5)));
            employeeList.add(new Employee("Nicolas", "Wilkins", scheduleList.get(6)));
            employeeList.add(new Employee("Howard", "Nguyen", scheduleList.get(7)));
            employeeList.add(new Employee("Kyle", "Henry", scheduleList.get(8)));
            employeeList.add(new Employee("Taylor", "Brewer", scheduleList.get(9)));
            employeeList.add(new Employee("Faye", "Alvarez", scheduleList.get(10)));
            employeeList.add(new Employee("Lori", "Shelton", scheduleList.get(11)));
            employeeList.add(new Employee("Rogelio", "Jenkins", scheduleList.get(12)));
            employeeList.add(new Employee("Kelvin", "Ortiz", scheduleList.get(13)));
            employeeList.add(new Employee("Marvin", "Owen", scheduleList.get(14)));

            int counterEmployee = 0;
            for (Employee employee : employeeList) {
                departmentList.get(counterEmployee % 5).addEmployee(employeeList.get(counterEmployee)); // 5 employee per department
                counterEmployee++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Create a clocking time list of the employees.
     */
    private static void createClockingTimeList() {
        try {
            Time startTimeClockingTime;
            Time endTimeClockingTime;
            int numberOfTheDays; // Zero is Monday and 6 is Sunday.
            for (Employee employee : employeeList) {
                numberOfTheDays = 0;
                for (Days day : Days.values()) {
                    startTimeClockingTime = null;
                    endTimeClockingTime = null;
                    if (employee.getSchedule().getWorkingDay(day).getStartTime() != null) {
                        startTimeClockingTime = new Time(employee.getSchedule().getWorkingDay(day).getStartTime().getHour(), employee.getSchedule().getWorkingDay(day).getStartTime().getMinute(), employee.getSchedule().getWorkingDay(day).getStartTime().getSecond());
                        // The employee report between -15 and 15 minutes before or after his real starting time.
                        int randStart = -15 + new Random().nextInt(31);

                        // If the minute is between 0 and 59
                        if (startTimeClockingTime.getMinute() + randStart >= 0 && startTimeClockingTime.getMinute() + randStart <= 59) {
                            startTimeClockingTime.setMinute(startTimeClockingTime.getMinute() + randStart);
                        }
                        // If the minute is before 0
                        else if (startTimeClockingTime.getMinute() + randStart < 0) {
                            startTimeClockingTime.setMinute(60 + startTimeClockingTime.getMinute() + randStart);
                            startTimeClockingTime.setHour(startTimeClockingTime.getHour() - 1);
                        }
                        // If the minute is after 59
                        else if (startTimeClockingTime.getMinute() + randStart > 59) {
                            startTimeClockingTime.setMinute(startTimeClockingTime.getMinute() + randStart - 60);
                            startTimeClockingTime.setHour(startTimeClockingTime.getHour() + 1);
                        }
                    }

                    if (employee.getSchedule().getWorkingDay(day).getEndTime() != null) {
                        endTimeClockingTime = new Time(employee.getSchedule().getWorkingDay(day).getEndTime().getHour(), employee.getSchedule().getWorkingDay(day).getEndTime().getMinute(), employee.getSchedule().getWorkingDay(day).getEndTime().getSecond());

                        // The employee report between -15 and 15 minutes before or after his real starting time.
                        int randEnd = -15 + new Random().nextInt(31);

                        // If the minute is between 0 and 59
                        if (endTimeClockingTime.getMinute() + randEnd >= 0 && endTimeClockingTime.getMinute() + randEnd <= 59) {
                            endTimeClockingTime.setMinute(endTimeClockingTime.getMinute() + randEnd);
                        }
                        // If the minute is before 0
                        else if (endTimeClockingTime.getMinute() + randEnd < 0) {
                            endTimeClockingTime.setMinute(60 + endTimeClockingTime.getMinute() + randEnd);
                            endTimeClockingTime.setHour(endTimeClockingTime.getHour() - 1);
                        }
                        // If the minute is after 59
                        else if (endTimeClockingTime.getMinute() + randEnd > 59) {
                            endTimeClockingTime.setMinute(endTimeClockingTime.getMinute() + randEnd - 60);
                            endTimeClockingTime.setHour(endTimeClockingTime.getHour() + 1);
                        }
                    }
                    if (startTimeClockingTime != null && endTimeClockingTime != null) {
                        Date dateOfClockingTime = new Date(employee.getSchedule().getWeekDate().getDay() + numberOfTheDays, employee.getSchedule().getWeekDate().getMonth(), employee.getSchedule().getWeekDate().getYear());
                        clockingTimeList.add(new ClockingTime(employee, dateOfClockingTime, startTimeClockingTime));
                        clockingTimeList.add(new ClockingTime(employee, dateOfClockingTime, endTimeClockingTime));
                    }
                    numberOfTheDays++;
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * Return a clocking time list.
     * @return The clocking time list.
     */
    public static ArrayList<ClockingTime> getClockingTimeList() {
        return clockingTimeList;
    }
}
