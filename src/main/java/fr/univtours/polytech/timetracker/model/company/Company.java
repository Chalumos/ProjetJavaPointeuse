package fr.univtours.polytech.timetracker.model.company;

import java.util.ArrayList;

public class Company {
    /**
     * Company name
     */
    private String name;

    /**
     * List of departments of a company
     */
    private ArrayList<Department> departements;

    /**
     * Default constructor
     */
    public Company() {
        this.name = "";
        this.departements = new ArrayList<Department>();
    }

    /**
     * Constructor with company name
     * @param name
     */
    public Company(String name) {
        this.name = name;
        this.departements = new ArrayList<Department>();
    }

    /**
     * @return the name of the company
     */
    public String getName() {
        return name;
    }

    /**
     * this method allows you to change the name of the company
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name of company
     */
    @Override
    public String toString() {
        return "Company: " + this.name;
    }
 }
