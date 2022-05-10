package compagny;

public class Employee {
    private String id;
    private String lastName;
    private String firstName;
    private Departement departement;
    private EmployeeCard employeCard;
    //private Schedule schedule;

    public Employee(String id, String lastName, String firstName, Departement departement) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.departement = departement;
        this.employeCard = new EmployeeCard(this.id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}