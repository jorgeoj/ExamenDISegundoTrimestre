module com.example.examendisegundotrimestre {
    requires javafx.controls;
    requires javafx.fxml;

    // Añadido
    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires jasperreports;


    opens com.example.examendisegundotrimestre to javafx.fxml;
    exports com.example.examendisegundotrimestre;
    exports com.example.examendisegundotrimestre.controllers;
    opens com.example.examendisegundotrimestre.controllers;
}