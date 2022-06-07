package fr.univtours.polytech.projet_tutore.view.application;

import fr.univtours.polytech.projet_tutore.controller.application.ApplicationController;
import fr.univtours.polytech.projet_tutore.controller.application.EditEmployeeController;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import fr.univtours.polytech.projet_tutore.model.company.Department;
import fr.univtours.polytech.projet_tutore.model.employee.Employee;
import fr.univtours.polytech.projet_tutore.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditEmployeeViewController extends ViewController {
    /**
     * Title of the view.
     */
    @FXML
    private Label labelTitle;

    /**
     * Text field of the first name of the employee.
     */
    @FXML
    private TextField textFieldFirstName;

    /**
     * Error label of the employee first name.
     */
    @FXML
    private Label labelFirstNameError;

    /**
     * Text field of the last name of the employee.
     */
    @FXML
    private TextField textFieldLastName;

    /**
     * Error label of the employee last name.
     */
    @FXML
    private Label labelLastNameError;

    /**
     * Combo box to select the department of the employee.
     */
    @FXML
    private ComboBox<Department> comboBoxDepartment;

    /**
     * Button to add or edit the employee.
     */
    @FXML
    private Button buttonEditEmployee;

    /**
     * Close the view and cancel the addition or edition of the employee.
     */
    @FXML
    public void closeView() {
        getView().close();
    }

    /**
     * Add a new employee or edit the employee in edition.
     */
    @FXML
    public void editEmployee() {
        Employee employee = getView().getController().getEmployee();
        String firstName = getTextFieldFirstName().getText();
        String lastName = getTextFieldLastName().getText();
        Department department = getComboBoxDepartment().getValue();
        boolean areTextFieldsFilled = true;

        // Check if the text fields are filled.
        if (firstName.length() == 0) {
            setLabelFirstNameError("Please enter a first name");
            areTextFieldsFilled = false;
        } else {
            setLabelFirstNameError("");
            employee.setFirstName(firstName);
        }

        if (lastName.length() == 0) {
            setLabelLastNameError("Please enter a last name");
            areTextFieldsFilled = false;
        } else {
            setLabelLastNameError("");
            employee.setLastName(lastName);
        }

        if (areTextFieldsFilled == true) {
            ApplicationController applicationController = getView().getController().getApplicationController();
            Company company = applicationController.getCompany();
            int departmentIndex = 0;

            getView().getController().setEmployee(employee);

            // Get the index of the department.
            for (int i = 0; i < company.getDepartments().size(); i++) {
                Department d = company.getDepartments().get(i);
                if (d == department) departmentIndex = i;
            }

            // Edition of the employee.
            if (getView().getController().isEdition()) {
                Department oldDepartment = company.getDepartment(employee);

                if (department != oldDepartment) {
                    try {
                        oldDepartment.deleteEmployee(employee);
                        department.addEmployee(employee);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            // Addition of the employee.
            else {
                try {
                    department.addEmployee(getView().getController().getEmployee());
                    applicationController.getCompany().getDepartments().set(departmentIndex, department);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            getView().close();

            String[] messages = {"employees", "selected_employee"};
            applicationController.notifyObservers(messages);
        }
    }

    @Override
    public EditEmployeeView getView() {
        return (EditEmployeeView) super.getView();
    }

    /**
     * Set the text of the tile label.
     * @param newTitle The new title of the view.
     */
    public void setLabelTitle(String newTitle) {
        this.labelTitle.setText(newTitle);
    }

    /**
     * Get the text field to enter the first name of the employee.
     * @return The text field to enter the first name of the employee.
     */
    public TextField getTextFieldFirstName() {
        return textFieldFirstName;
    }

    /**
     * Set the content of the text field of the first name of the employee.
     * @param newFirstName The new content of the text field.
     */
    public void setTextFieldFirstName(String newFirstName) {
        this.textFieldFirstName.setText(newFirstName);
    }

    /**
     * Get the label to print an error for the first name text field.
     * @return The label to print an error for the first name text field.
     */
    public Label getLabelFirstNameError() {
        return labelFirstNameError;
    }

    /**
     * Set the content of the error label of the first name of the employee.
     * @param newFirstNameError The new error.
     */
    public void setLabelFirstNameError(String newFirstNameError) {
        this.labelFirstNameError.setText(newFirstNameError);
    }

    /**
     * Get the text field to enter the last name of the employee.
     * @return The text field to enter the last name of the employee.
     */
    public TextField getTextFieldLastName() {
        return textFieldLastName;
    }

    /**
     * Set the content of the text field of the last name of the employee.
     * @param newLastName The new content of the text field.
     */
    public void setTextFieldLastName(String newLastName) {
        this.textFieldLastName.setText(newLastName);
    }

    /**
     * Get the label to print an error for the last name text field.
     * @return The label to print an error for the last name text field.
     */
    public Label getLabelLastNameError() {
        return labelLastNameError;
    }

    /**
     * Set the content of the error label of the last name of the employee.
     * @param newLastNameError The new error.
     */
    public void setLabelLastNameError(String newLastNameError) {
        this.labelLastNameError.setText(newLastNameError);
    }

    /**
     * Get the combo box to select the department of the employee.
     * @return The combo box to select the department of the employee.
     */
    public ComboBox<Department> getComboBoxDepartment() {
        return comboBoxDepartment;
    }

    /**
     * Set the text of the addition or edition button.
     * @param newText The new text of the button.
     */
    public void setButtonEditEmployee(String newText) {
        this.buttonEditEmployee.setText(newText);
    }
}
