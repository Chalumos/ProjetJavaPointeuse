package fr.univtours.polytech.timetracker.model.company;

import java.util.ArrayList;

public class Company {
    private String name;
    private ArrayList<Department> departements;

    public Company() {
        this.name = "";
        this.departements = new ArrayList<Department>();
    }

    public Company(String name) {
        this.name = name;
        this.departements = new ArrayList<Department>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
