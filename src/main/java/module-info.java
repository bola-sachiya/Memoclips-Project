module lk.ijse.memoclips {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.memoclips.contraller to javafx.fxml;
    opens lk.ijse.memoclips to javafx.graphics;

    exports lk.ijse.memoclips;
    exports lk.ijse.memoclips.controller;
}