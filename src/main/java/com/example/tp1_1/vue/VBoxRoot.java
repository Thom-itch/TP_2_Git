package com.example.tp1_1.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import com.example.tp1_1.modele.CalendrierDuMois;
import com.example.tp1_1.modele.ConstantesCalendrier;
import com.example.tp1_1.modele.DateCalendrier;

import java.util.List;


/**
 * La classe VBoxRoot est une vue principale contenant le calendrier annuel de l'année 2024
 * avec des boutons de navigation pour passer d'un mois à l'autre.
 */
public class VBoxRoot extends VBox implements ConstantesCalendrier {

    private int currentIndex; // Index du mois actuellement affiché


    /**
     * Constructeur de la classe VBoxRoot.
     */
    public VBoxRoot() {
        // Création du StackPane pour contenir les calendriers des mois
        StackPane stackPanesMois = new StackPane();

        // Création des boutons de navigation
        HBox boutons = new HBox();
        Button precedent = new Button("<");
        Button apres = new Button(">");
        boutons.getChildren().addAll(precedent, apres);
        boutons.setStyle("-fx-alignment: center-right;");
        boutons.setPadding(new Insets(10, 10, 10, 10));
        boutons.setSpacing(10);

        // Affichage du titre initial
        CalendrierDuMois calendrierHasard = new CalendrierDuMois(1, 2024);
        Label labelTitle = new Label(ConstantesCalendrier.MOIS[calendrierHasard.getMois() - 1] + "  " + calendrierHasard.getAnnee());
        VBox.setMargin(labelTitle, new Insets(14));
        this.getChildren().add(labelTitle);

        // Création des calendriers de tous les mois de l'année 2024
        for (int i = 1; i < 13; i++) {
            VBox boiteDates = new VBox();
            CalendrierDuMois calendrier = new CalendrierDuMois(i, 2024);
            ScrollPane scrollPaneMois = new ScrollPane();
            scrollPaneMois.setAccessibleText(ConstantesCalendrier.MOIS[i - 1]);

            // Ajout des labels pour chaque jour du mois dans le calendrier actuel
            for (DateCalendrier date : calendrier.getDates()) {
                Label labelDate = new Label(date.toString());
                if (date.getChMois() != calendrier.getMois()) {
                    labelDate.setId("dateHorsMois");
                }
                if (date.isToday()) {
                    labelDate.setId("today");
                }
                VBox.setMargin(labelDate, new Insets(8));
                boiteDates.getChildren().add(labelDate);
            }

            // Ajout du calendrier du mois actuel dans le StackPane
            scrollPaneMois.setContent(boiteDates);
            stackPanesMois.getChildren().add(scrollPaneMois);
        }

        // Récupération de tous les nœuds enfants du StackPane
        List<Node> listeNode = stackPanesMois.getChildren();
        DateCalendrier today = new DateCalendrier();
        final int last = listeNode.size() - 1;

        // Masquage des mois précédents par défaut
        while (!listeNode.get(last).getAccessibleText().equals(MOIS[today.getChMois() - 1])) {
            listeNode.get(last).toBack();
        }

        // Mise à jour du titre initial avec le mois actuel
        labelTitle.setText(MOIS[today.getChMois() - 1] + " " + today.getChAnnee());
        currentIndex = today.getChMois() - 1;

        // Ajout des éléments à la VBoxRoot
        this.getChildren().add(stackPanesMois);
        this.getChildren().add(boutons);

        // Action associée au bouton "Mois Suivant"
        apres.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listeNode.get(0).toFront();
                if (currentIndex == 11) {
                    currentIndex = 0;
                } else {
                    currentIndex++;
                }
                labelTitle.setText(MOIS[currentIndex] + " " + today.getChAnnee());
            }
        });

        // Action associée au bouton "Mois Précédent"
        precedent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listeNode.get(last).toBack();
                if (currentIndex == 0) {
                    currentIndex = 11;
                } else {
                    currentIndex--;
                }
                labelTitle.setText(MOIS[currentIndex] + " " + today.getChAnnee());
            }
        });

        // Les attributs id sont utilisés dans la feuille de style
    }
}
