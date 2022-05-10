package compagny;
import java.util.ArrayList;
import java.util.List;

public class Compagny {
    private String name;
    private List<Departement> departements;

    public Compagny(String name) {
        this.name = name;
        this.departements = new ArrayList<Departement>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
