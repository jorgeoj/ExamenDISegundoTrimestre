package com.example.examendisegundotrimestre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private ChoiceBox chSexo;
    @javafx.fxml.FXML
    private TextField txtPeso;
    @javafx.fxml.FXML
    private TextField txtTalla;
    @javafx.fxml.FXML
    private TextField txtEdad;
    @javafx.fxml.FXML
    private ChoiceBox chTipoActividad;
    @javafx.fxml.FXML
    private TextArea txtObservaciones;
    @javafx.fxml.FXML
    private Button btnGuardar;
    @javafx.fxml.FXML
    private Button btnInforme;
    @javafx.fxml.FXML
    private Label lblSolucion;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Valores para los chBox
        ObservableList<String> datos = FXCollections.observableArrayList();
        datos.addAll("Masculino", "Femenino");
        chSexo.setItems(datos);

        ObservableList<String> tipos = FXCollections.observableArrayList();
        tipos.addAll("Muy Activo","Activo", "Moderado", "Sedentario");
        chTipoActividad.setItems(tipos);

    }

    @javafx.fxml.FXML
    public void guardarInformacion(ActionEvent actionEvent) {
        if (txtNombre.getText().isEmpty() || chSexo.getValue() == null || txtPeso.getText().isEmpty() ||
            txtTalla.getText().isEmpty() || txtEdad.getText().isEmpty() || chTipoActividad.getValue() == null ||
                txtObservaciones.getText().isEmpty()) {
            lblSolucion.setText("Error, no ha introducido todos los campos");
        }else {
            String nombreCliente = txtNombre.getText();
            String sexoCliente = chSexo.getValue().toString();
            Double pesoCliente = Double.parseDouble(txtPeso.getText());
            Double tallaCliente = Double.parseDouble(txtTalla.getText());
            Integer edadCliente = Integer.valueOf(txtEdad.getText());
            Double metabolismoBasal;

            // Primer calculo segun el sexo
            if (sexoCliente == "Masculino") {
                metabolismoBasal = 66.473 + 13.751 * pesoCliente + 5.0033 * tallaCliente - 6.755 * edadCliente;
            } else {
                metabolismoBasal = 655.0955 + 9.463 * pesoCliente + 1.8496 * tallaCliente - 4.6756 * edadCliente;
            }
        }
    }

    @javafx.fxml.FXML
    public void descargarInforme(ActionEvent actionEvent) throws SQLException, JRException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientes", "root", "");
        JasperPrint jasperPrint = JasperFillManager.fillReport("informe_clientes.jasper", null, connection);

        // Mostrar el informe en una ventana
        JasperViewer.viewReport(jasperPrint, false);

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("informe_clientes.pdf"));
        exp.setConfiguration(new SimplePdfExporterConfiguration());
        exp.exportReport();
    }


}