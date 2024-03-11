module com.example.examendisegundotrimestre {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examendisegundotrimestre to javafx.fxml;
    exports com.example.examendisegundotrimestre;
}