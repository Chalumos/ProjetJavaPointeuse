package compagny;
import java.util.ArrayList;
import java.util.List;

public class Departement {
    private String name;
    private List<Employee> employes;

    public Departement(String name) {
        this.name = name;
        this.employes = new ArrayList<Employee>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
