module fr.univtours.polytech.projet_tutore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens fr.univtours.polytech.projet_tutore to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore;

    // Model.
    exports fr.univtours.polytech.projet_tutore.model;
    opens fr.univtours.polytech.projet_tutore.model to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.model.company;
    opens fr.univtours.polytech.projet_tutore.model.company to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.model.data_manager;
    opens fr.univtours.polytech.projet_tutore.model.data_manager to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.model.date;
    opens fr.univtours.polytech.projet_tutore.model.date to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.model.employee;
    opens fr.univtours.polytech.projet_tutore.model.employee to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.model.timetracker;
    opens fr.univtours.polytech.projet_tutore.model.timetracker to javafx.fxml;

    // View.
    exports fr.univtours.polytech.projet_tutore.view;
    opens fr.univtours.polytech.projet_tutore.view to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.view.timetracker;
    opens fr.univtours.polytech.projet_tutore.view.timetracker to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.view.application;
    opens fr.univtours.polytech.projet_tutore.view.application to javafx.fxml;

    exports fr.univtours.polytech.projet_tutore.view.timetracker.settings;
    opens fr.univtours.polytech.projet_tutore.view.timetracker.settings to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.controller.timetracker.settings;
    opens fr.univtours.polytech.projet_tutore.controller.timetracker.settings to javafx.fxml;

    // Controller.
    exports fr.univtours.polytech.projet_tutore.controller;
    opens fr.univtours.polytech.projet_tutore.controller to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.controller.timetracker;
    opens fr.univtours.polytech.projet_tutore.controller.timetracker to javafx.fxml;
    exports fr.univtours.polytech.projet_tutore.controller.application;
    opens fr.univtours.polytech.projet_tutore.controller.application to javafx.fxml;
}