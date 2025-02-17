package com.example.tp1_1.vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import com.example.tp1_1.modele.DateCalendrier;
import com.example.tp1_1.modele.PlageHoraire;
import com.example.tp1_1.modele.Reservation;

import java.util.TreeSet;


/**
 * La classe VBoxAffichagePlanning est une vue permettant d'afficher
 * toutes les réservations enregistrées en fonction de la semaine.
 */
public class VBoxAffichagePlanning extends VBox {

    // Label affichant le numéro de la semaine du jour sélectionné
    private Label numSem = new Label("");

    // Numéro de la semaine du jour sélectionné
    private int semaine = new DateCalendrier().getWeekOfYear();

    // TableView contenant les réservations enregistrées
    private TableView<Reservation> tableDesReservations;

    // Liste des réservations faites lors d'une semaine donnée
    private TreeSet<Reservation> reservationsDeLaSemaine;


    /**
     * Constructeur de la classe VBoxAffichagePlanning.
     * Initialise la vue avec un label pour le numéro de semaine et une TableView pour afficher les réservations.
     */
    public VBoxAffichagePlanning() {
        // Initialise le numéro de semaine affiché
        this.setNumSem(semaine);
        // Ajoute le label du numéro de semaine à la VBox
        this.getChildren().add(numSem);

        // Initialise la TableView
        tableDesReservations = new TableView<>();

        // Crée les colonnes de la TableView pour afficher les détails des réservations
        TableColumn<Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Reservation, String> coursColumn = new TableColumn<>("Cours");
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("chReserv"));

        TableColumn<Reservation, String> niveauColumn = new TableColumn<>("Niveau");
        niveauColumn.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        TableColumn<Reservation, PlageHoraire> horaireColumn = new TableColumn<>("Horaire");
        horaireColumn.setCellValueFactory(new PropertyValueFactory<>("Ph"));

        // Ajoute les colonnes à la TableView
        tableDesReservations.getColumns().addAll(dateColumn, coursColumn, niveauColumn, horaireColumn);
        // Ajoute la TableView à la VBox
        this.getChildren().add(tableDesReservations);

        // Met à jour la TableView avec les données actuelles
        this.update();
    }


    /**
     * Met à jour le contenu de la TableView avec les réservations de la semaine sélectionnée.
     */
    public void update() {
        // Efface le contenu actuel de la TableView
        tableDesReservations.getItems().clear();
        // Récupère les réservations de la semaine sélectionnée depuis le planning
        reservationsDeLaSemaine = HboxRoot.getPlanning().getReservationsSemaine(semaine);
        // Si des réservations existent pour cette semaine
        if (reservationsDeLaSemaine != null) {
            // Ajoute chaque réservation à la TableView
            for (Reservation reservation : reservationsDeLaSemaine) {
                tableDesReservations.getItems().add(reservation);
            }
        }
    }


    /**
     * Modifie le numéro de la semaine affiché dans le label numSem.
     * @param parNum le nouveau numéro de semaine à afficher
     */
    public void setNumSem(int parNum) {
        // Met à jour le texte du label avec le nouveau numéro de semaine
        numSem.setText("Numéro de semaine : " + parNum);
        // Met à jour la variable semaine avec le nouveau numéro de semaine
        semaine = parNum;
    }


    /**
     * Récupère le label affichant le numéro de la semaine.
     * @return le label affichant le numéro de la semaine
     */
    public Label getNumSem() {
        return numSem;
    }
}
