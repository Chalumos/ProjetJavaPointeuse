package fr.univtours.polytech.timetracker.controller;

import fr.univtours.polytech.timetracker.view.Observer;

import java.util.ArrayList;

public class Observable {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void register(Observer observer)
    {
        observers.add(observer);
    }

    public void unregister(Observer observer)
    {
        observers.remove(observer);
    }

    public void notify(String message)
    {
        for(Observer observer : observers) {
            observer.update(this);
        }
    }
}
