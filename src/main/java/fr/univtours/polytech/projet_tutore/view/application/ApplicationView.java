package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.controller.Observable;
import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.date.Days;
import fr.univtours.polytech.projet_tutore.model.date.Schedule;
import fr.univtours.polytech.projet_tutore.model.date.WorkingDay;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import fr.univtours.polytech.projet_tutore.view.View;
import fr.univtours.polytech.projet_tutore.view.application.edit.EditClockingTimeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.function.Function;

public class ApplicationView extends View {
    /**
     * Initialize the controller to null.
     */
    public ApplicationView() {
        setController(null);
    }

    /**
     * Get a button to insert into a column of a table view.
     * @param text The text of the button.
     * @param onClick The function called when the button is clicked.
     * @return The button.
     */
    private Callback<TableColumn<ClockingTime, Void>, TableCell<ClockingTime, Void>> getTableViewButton(String text, Function<ClockingTime, Void> onClick) {
        return new Callback<>() {
            @Override
            public TableCell<ClockingTime, Void> call(TableColumn<ClockingTime, Void> clockingTimeVoidTableColumn) {
                final TableCell<ClockingTime, Void> cell = new TableCell<>() {
                    private final Button button = new Button(text);
                    {
                        button.setOnAction((ActionEvent event) -> {
                            ClockingTime clockingTime = getTableView().getItems().get(getIndex());
                            onClick.apply(clockingTime);
                        });

                        button.setCursor(Cursor.HAND);
                        button.setMinWidth(70);
                        button.setPadding(new Insets(2, 5, 2, 5));
                        if (text.equalsIgnoreCase("remove")) {
                            button.setStyle("-fx-background-color: #d63031; -fx-text-fill: white");
                        }
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
    }

    /**
     * Initialize the table view of clocking times.
     */
    private void initializeTableViewClockingTimes() {
        TableColumn<ClockingTime, String> columnEmployee = new TableColumn<>("Employee");
        TableColumn<ClockingTime, String> columnDate = new TableColumn<>("Date");
        TableColumn<ClockingTime, String> columnTime = new TableColumn<>("Time");
        TableColumn<ClockingTime, Void> columnEdit = new TableColumn<>("Edition");
        TableColumn<ClockingTime, Void> columnRemove = new TableColumn<>("Deletion");

        columnEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        Callback<TableColumn<ClockingTime, Void>, TableCell<ClockingTime, Void>> editButton = getTableViewButton("Edit", (clockingTime) -> {
            EditClockingTimeView view = new EditClockingTimeView(getController(), clockingTime);
            view.show();
            return null;
        });

        Callback<TableColumn<ClockingTime, Void>, TableCell<ClockingTime, Void>> removeButton = getTableViewButton("Remove", (clockingTime) -> {
            getController().removeClockingTime(clockingTime);
            return null;
        });

        columnEmployee.setStyle("-fx-alignment: CENTER-LEFT");
        columnDate.setStyle("-fx-alignment: CENTER-LEFT");
        columnTime.setStyle("-fx-alignment: CENTER-LEFT");

        columnEdit.setCellFactory(editButton);
        columnEdit.setStyle("-fx-alignment: CENTER");

        columnRemove.setCellFactory(removeButton);
        columnRemove.setStyle("-fx-alignment: CENTER");

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
        setIcon(stage);
        stage.setResizable(false);
        stage.setScene(scene);

        // Get the controller which controls the elements of the view.
        setViewController(fxmlLoader.getController());
        getViewController().setView(this);

        // Get the controller of the view.
        initialize();
        
        // Close the server when the app is close.
        stage.setOnCloseRequest((event)->{
            getController().getServerMultiThread().setServerOn(false);
            System.exit(0);
        });

        // Show the view.
        stage.show();
    }

    @Override
    public void update(Observable observable, String[] messages) {
        Company company = getController().getCompany();
        ArrayList<Employee> employees = new ArrayList<>(company.getEmployees());
/*
        for (Department department : company.getDepartments()) {
            employees.addAll(department.getEmployees());
        }
*/
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
                    case "clocking_times" -> {
                        // Update the list of clocking times.
                        getViewController().setTableViewClockingTimes(getController().getFilteredClockingTimes());
                        getViewController().getTableViewClockingTimes().refresh();
                        getViewController().setLabelClockingTimeInfo("");
                    }
                    case "employees" -> {
                        // Update the list of employees.
                        getViewController().setTableViewEmployeeList(employees);
                        getViewController().getTableViewEmployeeList().refresh();
                    }
                    case "selected_employee" -> {
                        // Update the information about the selected employee.
                        Employee selectedEmployee = getController().getSelectedEmployee();
                        ArrayList<WorkingDay> workingDays = new ArrayList<>();

                        if (selectedEmployee != null) {
                            Days[] days = {Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY, Days.SATURDAY, Days.SUNDAY};
                            for (Days day : days) {
                                workingDays.add(selectedEmployee.getSchedule().getWorkingDay(day));
                            }

                            // Employee.
                            getViewController().setLabelEmployeeID(selectedEmployee.getId());
                            getViewController().setLabelEmployeeFirstname(selectedEmployee.getFirstName());
                            getViewController().setLabelEmployeeLastname(selectedEmployee.getLastName());
                            getViewController().setLabelEmployeeDepartment(company.getDepartment(selectedEmployee).getName());

                            getViewController().getButtonEmployeeEditSchedule().setDisable(false);
                            getViewController().getButtonEditEmployee().setDisable(false);
                            getViewController().getButtonRemoveEmployee().setDisable(false);
                            getViewController().getButtonAddClockingTime().setDisable(false);
                            getViewController().getButtonAddClockingTimesFromFile().setDisable(false);
                        }
                        else {
                            // Employee.
                            getViewController().setLabelEmployeeID("Unknown");
                            getViewController().setLabelEmployeeFirstname("Unknown");
                            getViewController().setLabelEmployeeLastname("Unknown");
                            getViewController().setLabelEmployeeDepartment("Unknown");

                            getViewController().getButtonEmployeeEditSchedule().setDisable(true);
                            getViewController().getButtonEditEmployee().setDisable(true);
                            getViewController().getButtonRemoveEmployee().setDisable(true);
                            getViewController().getButtonAddClockingTime().setDisable(true);
                            getViewController().getButtonAddClockingTimesFromFile().setDisable(true);
                        }

                        // Schedule.
                        getViewController().setTableViewEmployeeSchedule(workingDays);
                        getViewController().getTableViewEmployeeSchedule().refresh();
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
