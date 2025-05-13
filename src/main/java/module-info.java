module lk.ijse.memoclips {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.memoclips to javafx.fxml;
    exports lk.ijse.memoclips;
}