module fr.univtours.polytech.timetracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fr.univtours.polytech.projet_tutore to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore;
    exports fr.univtours.polytech.projet_tutore.controller;
    opens fr.univtours.polytech.projet_tutore.controller to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.view;
    opens fr.univtours.polytech.projet_tutore.view to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.view.timetracker;
    opens fr.univtours.polytech.projet_tutore.view.timetracker to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.controller.timetracker;
    opens fr.univtours.polytech.projet_tutore.controller.timetracker to javafx.fxml;
}