package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.date.Date;
import fr.univtours.polytech.projet_tutore.model.date.WorkingDay;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.model.timetracker.ClockingTime;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Controller which controls the elements of a ApplicationView.
 */
public class ApplicationViewController extends ViewController {
    /**
     * Combo box to select an employee in the clocking time filters.
     */
    @FXML
    private ComboBox<Employee> comboBoxEmployeeFilters;

    /**
     * Combo box to select a department in the clocking time filters.
     */
    @FXML
    private ComboBox<Department> comboBoxDepartmentFilters;

    /**
     * Combo box to select a min date in the clocking time filters.
     */
    @FXML
    private DatePicker datePickerFromFilters;

    /**
     * Combo box to select a max date in the clocking time filters.
     */
    @FXML
    private DatePicker datePickerToFilters;

    /**
     * List of clocking times according to the filters.
     */
    @FXML
    private TableView<ClockingTime> tableViewClockingTimes;

    /**
     * Button to add a new clocking time manually.
     */
    @FXML
    private Button buttonAddClockingTime;

    /**
     * Button to add clocking times from a file.
     */
    @FXML
    private Button buttonAddClockingTimesFromFile;

    /**
     * List of the employees of the company.
     */
    @FXML
    private TableView<Employee> tableViewEmployeeList;

    /**
     * ID of the selected employee.
     */
    @FXML
    private Label labelEmployeeID;

    /**
     * First name of the selected employee.
     */
    @FXML
    private Label labelEmployeeFirstname;

    /**
     * Last name of the selected employee.
     */
    @FXML
    private Label labelEmployeeLastname;

    /**
     * Department of the selected employee.
     */
    @FXML
    private Label labelEmployeeDepartment;

    /**
     * Button to display the schedule of the previous week of the current one.
     */
    @FXML
    private Button buttonEmployeePreviousSchedule;

    /**
     * Title of the selected schedule.
     */
    @FXML
    private Label labelEmployeeScheduleTitle;

    /**
     * Button to display the schedule of the next week of the current one.
     */
    @FXML
    private Button buttonEmployeeNextSchedule;

    /**
     * List of working days of the current schedule.
     */
    @FXML
    private TableView<WorkingDay> tableViewEmployeeSchedule;

    /**
     * Button to add a new schedule to the selected employee.
     */
    @FXML
    private Button buttonEmployeeNewSchedule;

    /**
     * Button to edit the current schedule of the selected employee.
     */
    @FXML
    private Button buttonEmployeeEditSchedule;

    /**
     * Button to remove the current schedule of the selected employee.
     */
    @FXML
    private Button buttonEmployeeRemoveSchedule;

    /**
     * Button to add a new employee to the company.
     */
    @FXML
    private Button buttonNewEmployee;

    /**
     * Button to edit the selected employee.
     */
    @FXML
    private Button buttonEditEmployee;

    /**
     * Button to remove the selected employee.
     */
    @FXML
    private Button buttonRemoveEmployee;

    /**
     * Update the list of clocking times.
     */
    @FXML
    public void updateClockingTimeList() {}

    /**
     * Add a new clocking time manually.
     */
    @FXML
    public void addClockingTime() {}

    /**
     * Add new clocking times from a file.
     */
    @FXML
    public void addClockingTimesFromFile() {}

    /**
     * Update the current schedule with the previous week's schedule.
     */
    @FXML
    public void updateScheduleWithPreviousWeek() {}

    /**
     * Update the current schedule with the next week's schedule.
     */
    @FXML
    public void updateScheduleWithNextWeek() {}

    /**
     * Open a view to add a new schedule to the selected employee.
     */
    @FXML
    public void newSchedule() {}

    /**
     * Open a view to edit the current schedule of the selected employee.
     */
    @FXML
    public void editSchedule() {}

    /**
     * Remove the current schedule of the selected employee.
     */
    @FXML
    public void removeSchedule() {}

    /**
     * Open a view to add a new employee to the company.
     */
    @FXML
    public void newEmployee() {}

    /**
     * Open a view to edit the selected employee.
     */
    @FXML
    public void editEmployee() {}

    /**
     * Remove the selected employee.
     */
    @FXML
    public void removeEmployee() {}

    /**
     * Get the combo box of the employee filter.
     * @return The combo box of the employee filter.
     */
    public ComboBox<Employee> getComboBoxEmployeeFilters() {
        return comboBoxEmployeeFilters;
    }

    /**
     * Set the employee list of the employee filter.
     * @param newEmployeeList The new employee list.
     */
    public void setComboBoxEmployeeFilters(ArrayList<Employee> newEmployeeList) {
        this.comboBoxEmployeeFilters.getItems().setAll(newEmployeeList);
    }

    /**
     * Get the combo box of the department filter.
     * @return The combo box of the department filter.
     */
    public ComboBox<Department> getComboBoxDepartmentFilters() {
        return comboBoxDepartmentFilters;
    }

    /**
     * Set the department list of the department filter.
     * @param newDepartmentList The new department list.
     */
    public void setComboBoxDepartmentFilters(ArrayList<Department> newDepartmentList) {
        this.comboBoxDepartmentFilters.getItems().setAll(newDepartmentList);
    }

    /**
     * Get the date picker of the starting date filter.
     * @return The date picker of the starting date filter.
     */
    public DatePicker getDatePickerFromFilters() {
        return datePickerFromFilters;
    }

    /**
     * Set the date of the date picker of the starting date filter.
     * @param newStartingDate The new starting date.
     */
    public void setDatePickerFromFilters(Date newStartingDate) {
        LocalDate date = LocalDate.of(newStartingDate.getYear(), newStartingDate.getMonth(), newStartingDate.getDay());
        this.datePickerFromFilters.setValue(date);
    }

    /**
     * Get the date picker of the ending date filter.
     * @return The date picker of the ending date filter.
     */
    public DatePicker getDatePickerToFilters() {
        return datePickerToFilters;
    }

    /**
     * Set the date of the date picker of the ending date filter.
     * @param newEndingDate The new ending date.
     */
    public void setDatePickerToFilters(Date newEndingDate) {
        LocalDate date = LocalDate.of(newEndingDate.getYear(), newEndingDate.getMonth(), newEndingDate.getDay());
        this.datePickerToFilters.setValue(date);
    }

    /**
     * Get the table view of the clocking time list.
     * @return The table view of the clocking time list.
     */
    public TableView<ClockingTime> getTableViewClockingTimes() {
        return tableViewClockingTimes;
    }

    /**
     * Set the clocking time list.
     * @param newClockingTimeList The new clocking time list.
     */
    public void setTableViewClockingTimes(ArrayList<ClockingTime> newClockingTimeList) {
        this.tableViewClockingTimes.getItems().setAll(newClockingTimeList);
    }

    /**
     * Get the button to add a new clocking time.
     * @return The button to add a new clocking time.
     */
    public Button getButtonAddClockingTime() {
        return buttonAddClockingTime;
    }

    /**
     * Get the button to add clocking times from a file.
     * @return The button to add clocking times from a file.
     */
    public Button getButtonAddClockingTimesFromFile() {
        return buttonAddClockingTimesFromFile;
    }

    /**
     * Get the table view of the employee list.
     * @return The table view of the employee list.
     */
    public TableView<Employee> getTableViewEmployeeList() {
        return tableViewEmployeeList;
    }

    /**
     * Set the employee list of the table view of employees.
     * @param newEmployeeList The new employee list.
     */
    public void setTableViewEmployeeList(ArrayList<Employee> newEmployeeList) {
        this.tableViewEmployeeList.getItems().setAll(newEmployeeList);
    }

    /**
     * Get the label of the ID of the selected employee.
     * @return The label of the ID of the selected employee.
     */
    public Label getLabelEmployeeID() {
        return labelEmployeeID;
    }

    /**
     * Set the ID of the selected employee.
     * @param newEmployeeID The new ID of the selected employee.
     */
    public void setLabelEmployeeID(String newEmployeeID) {
        this.labelEmployeeID.setText(newEmployeeID);
    }

    /**
     * Get the label of the first name of the selected employee.
     * @return The label of the first name of the selected employee.
     */
    public Label getLabelEmployeeFirstname() {
        return labelEmployeeFirstname;
    }

    /**
     * Set the first name of the selected employee.
     * @param newEmployeeFirstname The new first name of the selected employee.
     */
    public void setLabelEmployeeFirstname(String newEmployeeFirstname) {
        this.labelEmployeeFirstname.setText(newEmployeeFirstname);
    }

    /**
     * Get the label of the last name of the selected employee.
     * @return The label of the last name of the selected employee.
     */
    public Label getLabelEmployeeLastname() {
        return labelEmployeeLastname;
    }

    /**
     * Set the last name of the selected employee.
     * @param newEmployeeLastname The new last name of the selected employee.
     */
    public void setLabelEmployeeLastname(String newEmployeeLastname) {
        this.labelEmployeeLastname.setText(newEmployeeLastname);
    }

    /**
     * Get the label of the department of the selected employee.
     * @return The label of the department of the selected employee.
     */
    public Label getLabelEmployeeDepartment() {
        return labelEmployeeDepartment;
    }

    /**
     * Set the department of the selected employee.
     * @param newEmployeeDepartment The new department of the selected employee..
     */
    public void setLabelEmployeeDepartment(String newEmployeeDepartment) {
        this.labelEmployeeDepartment.setText(newEmployeeDepartment);
    }

    /**
     * Get the button to display the schedule of the previous week.
     * @return The button to display the schedule of the previous week.
     */
    public Button getButtonEmployeePreviousSchedule() {
        return buttonEmployeePreviousSchedule;
    }

    /**
     * Get the label of the title of the schedule.
     * @return The label of the title of the schedule.
     */
    public Label getLabelEmployeeScheduleTitle() {
        return labelEmployeeScheduleTitle;
    }

    /**
     * Set the title of the schedule.
     * @param newScheduleTitle The new schedule title.
     */
    public void setLabelEmployeeScheduleTitle(String newScheduleTitle) {
        this.labelEmployeeScheduleTitle.setText(newScheduleTitle);
    }

    /**
     * Get the button to display the schedule of the next week.
     * @return The button to display the schedule of the next week.
     */
    public Button getButtonEmployeeNextSchedule() {
        return buttonEmployeeNextSchedule;
    }

    /**
     * Get the table view of working days of the current schedule.
     * @return The table view of working days of the current schedule.
     */
    public TableView<WorkingDay> getTableViewEmployeeSchedule() {
        return tableViewEmployeeSchedule;
    }

    /**
     * Set the working day list of the table view of working days of the current schedule.
     * @param newWorkingDayList The new working day list.
     */
    public void setTableViewEmployeeSchedule(ArrayList<WorkingDay> newWorkingDayList) {
        this.tableViewEmployeeSchedule.getItems().setAll(newWorkingDayList);
    }

    /**
     * Get the button to add a new schedule to the selected employee.
     * @return The button to add a new schedule to the selected employee.
     */
    public Button getButtonEmployeeNewSchedule() {
        return buttonEmployeeNewSchedule;
    }

    /**
     * Get the button to edit the current schedule of the selected employee.
     * @return The button to edit the current schedule of the selected employee.
     */
    public Button getButtonEmployeeEditSchedule() {
        return buttonEmployeeEditSchedule;
    }

    /**
     * Get the button to remove the current schedule of the selected employee.
     * @return The button to remove the current schedule of the selected employee.
     */
    public Button getButtonEmployeeRemoveSchedule() {
        return buttonEmployeeRemoveSchedule;
    }

    /**
     * Get the button to add a new employee.
     * @return The button to add a new employee.
     */
    public Button getButtonNewEmployee() {
        return buttonNewEmployee;
    }

    /**
     * Get the button to edit the selected employee.
     * @return The button to edit the selected employee.
     */
    public Button getButtonEditEmployee() {
        return buttonEditEmployee;
    }

    /**
     * Get the button to remove the selected employee.
     * @return The button to remove the selected employee.
     */
    public Button getButtonRemoveEmployee() {
        return buttonRemoveEmployee;
    }
}