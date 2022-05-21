package fr.univtours.polytech.projet_tutore.model.timetracker;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TimeTrackerTest {
    @Test
    public static void main(String[] args) throws IOException {
        TimeTracker theTracker = new TimeTracker();

        theTracker.serialisationEmployee();

    }
}
