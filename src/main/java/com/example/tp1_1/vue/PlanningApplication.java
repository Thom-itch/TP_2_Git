package com.example.tp1_1.vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;


/**
 * Cette classe représente l'application permettant l'affichage de la HBox dans une fenêtre.
 */
public class PlanningApplication extends Application {


    /**
     * Méthode start pour initialiser et afficher l'interface graphique.
     * @param stage la fenêtre principale de l'application
     */
    public void start(Stage stage) {
        // Crée une instance de HboxRoot
        HboxRoot root = new HboxRoot();

        // Crée une scène avec la racine et les dimensions spécifiées
        Scene scene = new Scene(root, 1600, 1000);

        // Charge les fichiers CSS depuis le répertoire "css" et les ajoute à la scène
        File[] filecss = new File("css").listFiles();
        if (filecss != null) {
            for (File fichier : filecss) {
                scene.getStylesheets().add(fichier.toURI().toString());
            }
        }

        // Configure la fenêtre principale
        stage.setResizable(false);
        stage.setTitle("Hello JavaFX");
        stage.centerOnScreen();

        // Affiche la fenêtre avec la scène
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Méthode principale pour démarrer l'application.
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        // Lance l'application JavaFX
        Application.launch(args);
    }
}
