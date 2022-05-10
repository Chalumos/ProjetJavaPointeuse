module fr.univtours.polytech.timetracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.univtours.polytech.timetracker to javafx.fxml;
    exports fr.univtours.polytech.timetracker;
    exports fr.univtours.polytech.timetracker.controller;
    opens fr.univtours.polytech.timetracker.controller to javafx.fxml;
    exports fr.univtours.polytech.timetracker.view;
    opens fr.univtours.polytech.timetracker.view to javafx.fxml;
}