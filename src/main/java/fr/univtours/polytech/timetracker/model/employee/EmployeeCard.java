package fr.univtours.polytech.timetracker.model.employee;

public class EmployeeCard
{
    /**
     * Employee
     */
    private Employee employee;

    /**
     * Create an employee card which initialize the attributes to null.
     */
    public EmployeeCard()
    {
        this.employee.setId(null);
        this.employee.setFirstName(null);
        this.employee.setLastName(null);
        this.employee.setDepartment(null);
    }

    /**
     * Create an employee card which initialize the attribute by the attributes of the employee in parameter.
     * @param employee An employee.
     */
    public EmployeeCard(Employee employee)
    {
        this.employee.setId(employee.getId());
        this.employee.setFirstName(employee.getFirstName());
        this.employee.setLastName(employee.getLastName());
        this.employee.setDepartment(employee.getDepartment());
    }

    /**
     * Return a description of the owner card.
     * @return A description of the owner card.
     */
    @Override
    public String toString() {
        String message = "The card belong to " + employee.getFirstName() + " " + employee.getLastName() + " [ " + employee.getId() + " ]" ;
        return message;
    }
}
