package com.example.tp1_1.controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import com.example.tp1_1.modele.*;
import com.example.tp1_1.vue.GridPaneFormulaireReservation;
import com.example.tp1_1.vue.HboxRoot;
import com.example.tp1_1.modele.ExceptionPlanning;
import com.example.tp1_1.vue.VBoxAffichagePlanning;


/**
 * Cette classe est le contrôleur qui nous permet de relier les vues entre elles.
 */
public class Controleur implements EventHandler {


    /**
     * Gère les événements déclenchés par les composants de l'interface utilisateur.
     *
     * @param event L'événement à gérer.
     */
    @Override
    public void handle(Event event) {
        // Récupère le planning depuis la vue racine
        PlanningCollections planning = HboxRoot.getPlanning();
        // Récupère le panneau de réservation depuis la vue racine
        GridPaneFormulaireReservation reservationPane = HboxRoot.getReservationPane();
        // Récupère le panneau d'affichage du planning depuis la vue racine
        VBoxAffichagePlanning affichagePlanning = HboxRoot.getAffichagePlanning();

        // Si la source de l'événement est un ToggleButton du calendrier
        if (event.getSource() instanceof ToggleButton) {
            // Cast de la source de l'événement en ToggleButton
            ToggleButton toggleButton = (ToggleButton) event.getSource();
            // Récupère la date associée au ToggleButton
            DateCalendrier date = (DateCalendrier) toggleButton.getUserData();
            // Met à jour le numéro de semaine et l'affichage du planning
            affichagePlanning.setNumSem(date.getWeekOfYear());
            affichagePlanning.update();
            // Met à jour la date du formulaire de réservation
            reservationPane.setDate(date);
        }

        // Si la source de l'événement est le bouton "Enregistrer" du formulaire de réservation
        if (event.getSource() instanceof Button) {
            // Récupère le texte de la zone de texte du formulaire de réservation
            String text = reservationPane.getZoneTexte();
            // Récupère le bouton radio sélectionné du formulaire de réservation
            RadioButton bouton = reservationPane.getRadioButton();
            // Initialise un tableau pour les heures de début et de fin de la plage horaire
            int[] heures = new int[4];
            for (int i = 0; i < 4; i++) {
                // Récupère les valeurs des heures depuis les comboboxes du formulaire
                heures[i] = reservationPane.getCombo()[i].getValue();
            }
            // Récupère la date du formulaire de réservation
            DateCalendrier date = reservationPane.getDate();
            PlageHoraire ph = null;

            // Si le bouton "Enregistrer" est associé à l'annulation
            if (((Button) event.getSource()).getUserData() == "annule") {
                // Réinitialise le formulaire de réservation
                reservationPane.restart();
            } else {
                try {
                    // Crée des objets Horaire pour les heures de début et de fin
                    Horaire horaire1 = new Horaire(heures[0], heures[1]);
                    Horaire horaire2 = new Horaire(heures[2], heures[3]);
                    // Crée une nouvelle plage horaire avec les horaires définis
                    ph = new PlageHoraire(horaire1, horaire2);
                    // Récupère le niveau associé au bouton radio sélectionné
                    String niveau = bouton.getText();
                    // Crée une nouvelle réservation avec les informations fournies
                    Reservation reservation = new Reservation(text, date, ph, niveau);
                    // Ajoute la réservation au planning
                    planning.ajout(reservation);
                    // Met à jour le numéro de semaine et l'affichage du planning
                    affichagePlanning.setNumSem(date.getWeekOfYear());
                    affichagePlanning.update();
                    // Réinitialise le formulaire de réservation
                    reservationPane.restart();
                    // Affiche un message de confirmation dans la console
                    System.out.println("Reservation de niveau " + bouton.getText() + " : " + text + " a ete ajoutee au planning le " + date.toString() + " de " + horaire1.toString() + " à " + horaire2.toString());
                } catch (ExceptionPlanning e) {
                    // Affiche le message d'erreur dans la console
                    System.out.println(e.getErreur());
                    // Met à jour le label d'exception dans le formulaire de réservation
                    reservationPane.setLabelException(e.getErreur());
                }
            }
        }
    }
}
