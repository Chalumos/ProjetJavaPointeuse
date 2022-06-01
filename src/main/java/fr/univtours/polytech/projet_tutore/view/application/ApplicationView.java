package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.date.Days;
import fr.univtours.polytech.projet_tutore.model.date.Schedule;
import fr.univtours.polytech.projet_tutore.model.date.WorkingDay;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import fr.univtours.polytech.projet_tutore.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ApplicationView extends View {
    /**
     * Initialize the controller to null.
     */
    public ApplicationView() {
        setController(null);
    }

    /**
     * Initialize the table view of clocking times.
     */
    private void initializeTableViewClockingTimes() {
        TableColumn<ClockingTime, String> columnEmployee = new TableColumn<>("Employee");
        TableColumn<ClockingTime, String> columnDate = new TableColumn<>("Date");
        TableColumn<ClockingTime, String> columnTime = new TableColumn<>("Time");
        TableColumn<ClockingTime, String> columnEdit = new TableColumn<>("Edit");
        TableColumn<ClockingTime, String> columnRemove = new TableColumn<>("Remove");

        columnEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        /*columnEdit.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnRemove.setCellValueFactory(new PropertyValueFactory<>("time"));*/

        getViewController().getTableViewClockingTimes().getColumns().setAll(
                columnEmployee, columnDate, columnTime, columnEdit, columnRemove);
    }

    /**
     * Initialize the table view of employees.
     */
    private void initializeTableViewEmployees() {
        TableColumn<Employee, String> columnFirstname = new TableColumn<>("First name");
        TableColumn<Employee, String> columnLastname = new TableColumn<>("Last name");

        columnFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        getViewController().getTableViewEmployeeList().getColumns().setAll(
                columnFirstname, columnLastname);
    }

    /**
     * Initialize the table view of working days.
     */
    private void initializeTableViewEmployeeSchedule() {
        TableColumn<WorkingDay, String> columnDay = new TableColumn<>("Day");
        TableColumn<WorkingDay, String> columnStartingTime = new TableColumn<>("Starting time");
        TableColumn<WorkingDay, String> columnEndingTime = new TableColumn<>("Ending time");

        columnDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        columnStartingTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        columnEndingTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        getViewController().getTableViewEmployeeSchedule().getColumns().setAll(
                columnDay, columnStartingTime, columnEndingTime);
    }

    @Override
    public void initialize() {
        // Initialization of the controller.
        ApplicationController controller = new ApplicationController();
        setController(controller);
        getController().initialize();

        // Initialization of the components.
        initializeTableViewClockingTimes();
        initializeTableViewEmployees();
        initializeTableViewEmployeeSchedule();
    }

    @Override
    public void show() {
        View.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // In the case of the separator for the resources, it's the same for every OS: '/'.
        // So there is no need to use File.separator.
        String fileName = "/fr/univtours/polytech/projet_tutore/view/application/applicationView.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationView.class.getResource(fileName));

        // Creation of the scene.
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Time tracker monitoring");
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller which controls the elements of the view.
        setViewController(fxmlLoader.getController());
        getViewController().setView(this);

        // Get the controller of the view.
        initialize();

        // Show the view.
        stage.show();
    }

    @Override
    public void update(Observable observable, String[] messages) {
        Company company = getController().getCompany();
        ArrayList<Employee> employees = company.getEmployees();

        try {
            for (String message : messages) {
                switch (message) {
                    case "employee_filter" -> {
                        // Update of the employee list in the filters.
                        getViewController().setComboBoxEmployeeFilters(employees);
                    }
                    case "department_filter" -> {
                        // Update of the department list in the filters.
                        getViewController().setComboBoxDepartmentFilters(company.getDepartments());
                    }
                    case "clocking_time" -> {
                        // Update the list of clocking times.
                        getViewController().setTableViewClockingTimes(Stub.getClockingTimeList());

                    }
                    case "employees" -> {
                        // Update the list of employees.
                        getViewController().setTableViewEmployeeList(employees);
                    }
                    case "selected_employee" -> {
                        // Update the information about the selected employee.
                        Employee selectedEmployee = getController().getSelectedEmployee();
                        Schedule schedule = selectedEmployee.getSchedule();
                        ArrayList<WorkingDay> workingDays = new ArrayList<>();
                        Days[] days = {Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY, Days.SATURDAY, Days.SUNDAY};

                        for (Days day : days) {
                            workingDays.add(selectedEmployee.getSchedule().getWorkingDay(day));
                        }

                        // Employee.
                        getViewController().setLabelEmployeeID(selectedEmployee.getId());
                        getViewController().setLabelEmployeeFirstname(selectedEmployee.getFirstName());
                        getViewController().setLabelEmployeeLastname(selectedEmployee.getLastName());
                        getViewController().setLabelEmployeeDepartment(company.getDepartment(selectedEmployee).getName());

                        // Schedule.
                        String scheduleTitle = "Week " + schedule.getWeekNumber() + " (" + schedule.getWeekDate() + ")";
                        getViewController().setLabelEmployeeScheduleTitle(scheduleTitle);
                        getViewController().setTableViewEmployeeSchedule(workingDays);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ApplicationController getController() {
        return (ApplicationController) super.getController();
    }

    @Override
    public ApplicationViewController getViewController() {
        return (ApplicationViewController) super.getViewController();
    }
}
