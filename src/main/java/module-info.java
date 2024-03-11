module com.example.examendisegundotrimestre {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examendisegundotrimestre to javafx.fxml;
    exports com.example.examendisegundotrimestre;
    exports com.example.examendisegundotrimestre.controllers;
    opens com.example.examendisegundotrimestre.controllers to javafx.fxml;
}