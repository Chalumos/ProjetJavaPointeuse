package fr.univtours.polytech.projet_tutore.model;

import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.date.*;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.Reporting;

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

    /** A list of reporting made by employees */
    private static ArrayList<Reporting> reportingList;

    /**
     * Generate a company.
     * @return The company with all the components.
     * @throws Exception
     */
    public static Company generateCompany() throws Exception {
        company = createCompany();
        departmentList = new ArrayList<Department>();
        createDepartmentList();
        scheduleList = new ArrayList<Schedule>();
        createScheduleList();
        employeeList = new ArrayList<Employee>();
        createEmployeeList();
        reportingList = new ArrayList<Reporting>();
        createReportingList();
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
     * @throws Exception
     */
    private static void createDepartmentList() throws Exception {
        departmentList.add(new Department("Executive Management"));
        departmentList.add(new Department("Human Resources"));
        departmentList.add(new Department("Marketing"));
        departmentList.add(new Department("Juridical"));
        departmentList.add(new Department("Logistics"));

        for (Department dep : departmentList){
            company.addDepartment(dep);
        }
    }

    /**
     * Create a list of schedule.
     * @throws Exception
     */
    private static void createScheduleList() throws Exception {
        // Create 3 schedules for 3 weeks.
        for (int week = 18; week < 21; week++){
            for (int numberSchedule = 1; numberSchedule < 4; numberSchedule++){
                Schedule scheduleWeek = new Schedule();
                for (Days days : Days.values()) {

                    // Choose if the day is a day off
                    int nullOrNot = new Random().nextInt(3); // De 0 à 3
                    // If the day is a day off
                    if (nullOrNot == 0){
                        scheduleWeek.setWorkingDay(new WorkingDay(days, null, null));
                    }
                    else {
                        // Create a schedule with random hour and minute
                        int randMinute = new Random().nextInt(4); // De 0 à 3
                        int randHour = -1 + new Random().nextInt(4); // De -1 à 2
                        scheduleWeek.setWorkingDay(new WorkingDay(days, new Time(8 + randHour, 15 * randMinute, 0), new Time(17 + randHour, 15 * randMinute, 0)));
                    }
                    scheduleWeek.setWeekDate(new Date(2 + 7*(week%18), Month.MAY, 2022));
                    scheduleWeek.setWeekNumber(week);
                }
                scheduleList.add(scheduleWeek);
            }
        }
    }

    /**
     * Create a list of employees in a department with their schedules.
     * @throws Exception
     */
    private static void createEmployeeList() throws Exception {
        // We work on a single week
        employeeList.add(new Employee("Chris", "Simmons", scheduleList.get(6)));
        employeeList.add(new Employee("Lila", "Mann", scheduleList.get(6)));
        employeeList.add(new Employee("Pam", "Johnston", scheduleList.get(6)));
        employeeList.add(new Employee("Merle", "Mccarthy", scheduleList.get(6)));
        employeeList.add(new Employee("Jeffery", "Wong", scheduleList.get(6)));
        employeeList.add(new Employee("Simon", "Morton", scheduleList.get(7)));
        employeeList.add(new Employee("Nicolas", "Wilkins", scheduleList.get(7)));
        employeeList.add(new Employee("Howard", "Nguyen", scheduleList.get(7)));
        employeeList.add(new Employee("Kyle", "Henry", scheduleList.get(7)));
        employeeList.add(new Employee("Taylor", "Brewer", scheduleList.get(7)));
        employeeList.add(new Employee("Faye", "Alvarez", scheduleList.get(8)));
        employeeList.add(new Employee("Lori", "Shelton", scheduleList.get(8)));
        employeeList.add(new Employee("Rogelio", "Jenkins", scheduleList.get(8)));
        employeeList.add(new Employee("Kelvin", "Ortiz", scheduleList.get(8)));
        employeeList.add(new Employee("Marvin", "Owen", scheduleList.get(8)));

        int counterEmployee = 0;
        for (Employee employee : employeeList){
            departmentList.get(counterEmployee % 5).addEmployee(employeeList.get(counterEmployee)); // 5 employee per department
            counterEmployee++;
        }
    }

    /**
     * Create a reporting list of the employees.
     * @throws Exception
     */
    private static void createReportingList() throws Exception {
        Time startTimeReporting;
        Time endTimeReporting;
        int numberOfTheDays; // Zero is Monday and 6 is Sunday.
        for (Employee employee : employeeList) {
            numberOfTheDays = 0;
            for (Days day : Days.values()) {
                startTimeReporting = null;
                endTimeReporting = null;
                if (employee.getSchedule().getWorkingDay(day).getStartTime() != null){
                    startTimeReporting = new Time(employee.getSchedule().getWorkingDay(day).getStartTime().getHour(), employee.getSchedule().getWorkingDay(day).getStartTime().getMinute(), employee.getSchedule().getWorkingDay(day).getStartTime().getSecond());
                    // The employee report between -15 and 15 minutes before or after his real starting time.
                    int randStart = -15 + new Random().nextInt(31);

                    // If the minute is between 0 and 59
                    if (startTimeReporting.getMinute() + randStart >= 0 && startTimeReporting.getMinute() + randStart <= 59) {
                        startTimeReporting.setMinute(startTimeReporting.getMinute() + randStart);
                    }
                    // If the minute is before 0
                    else if (startTimeReporting.getMinute() + randStart < 0) {
                        startTimeReporting.setMinute(60 + startTimeReporting.getMinute() + randStart);
                        startTimeReporting.setHour(startTimeReporting.getHour() - 1);
                    }
                    // If the minute is after 59
                    else if (startTimeReporting.getMinute() + randStart > 59) {
                        startTimeReporting.setMinute(startTimeReporting.getMinute() + randStart - 60);
                        startTimeReporting.setHour(startTimeReporting.getHour() + 1);
                    }
                }

                if (employee.getSchedule().getWorkingDay(day).getEndTime() != null) {
                    endTimeReporting = new Time(employee.getSchedule().getWorkingDay(day).getEndTime().getHour(), employee.getSchedule().getWorkingDay(day).getEndTime().getMinute(), employee.getSchedule().getWorkingDay(day).getEndTime().getSecond());

                    // The employee report between -15 and 15 minutes before or after his real starting time.
                    int randEnd = -15 + new Random().nextInt(31);

                    // If the minute is between 0 and 59
                    if (endTimeReporting.getMinute() + randEnd >= 0 && endTimeReporting.getMinute() + randEnd <= 59) {
                        endTimeReporting.setMinute(endTimeReporting.getMinute() + randEnd);
                    }
                    // If the minute is before 0
                    else if (endTimeReporting.getMinute() + randEnd < 0) {
                        endTimeReporting.setMinute(60 + endTimeReporting.getMinute() + randEnd);
                        endTimeReporting.setHour(endTimeReporting.getHour() - 1);
                    }
                    // If the minute is after 59
                    else if (endTimeReporting.getMinute() + randEnd > 59) {
                        endTimeReporting.setMinute(endTimeReporting.getMinute() + randEnd - 60);
                        endTimeReporting.setHour(endTimeReporting.getHour() + 1);
                    }
                }
                Date dateOfReporting = new Date(employee.getSchedule().getWeekDate().getDay() + numberOfTheDays, employee.getSchedule().getWeekDate().getMonth(), employee.getSchedule().getWeekDate().getYear());
                reportingList.add(new Reporting(employee, dateOfReporting, startTimeReporting));
                reportingList.add(new Reporting(employee, dateOfReporting, endTimeReporting));
                numberOfTheDays++;
            }
        }
    }

    /**
     * Return a reporting list.
     * @return The reporting list.
     */
    public static ArrayList<Reporting> getReportingList() {
        return reportingList;
    }
}
