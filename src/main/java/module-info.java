module com.example.tp1_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.tp1_1 to javafx.fxml;
    exports com.example.tp1_1.modele;
    exports com.example.tp1_1.controleur;
    exports com.example.tp1_1.vue;

}