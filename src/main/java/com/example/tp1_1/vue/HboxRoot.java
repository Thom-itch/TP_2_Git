package com.example.tp1_1.vue;

import com.example.tp1_1.controleur.Controleur;
import javafx.scene.layout.HBox;
import com.example.tp1_1.modele.*;

/**
 * Cette classe est la HBox comprenant toutes les vues de notre projet
 */
public class HboxRoot extends HBox implements ConstantesCalendrier {

    private static VBoxCalendrier calendrierPane;/** Ce champ est une instance de la vue VBoxCalendrier */;
    private static GridPaneFormulaireReservation formReservationPane;/** Ce champ est une instance de la vue GridPaneFormulaireReservation */;
    private static PlanningCollections planning ;/** Ce champ est une instance de la classe PlanningCollections dans laquelle nous rentrerons les réservations*/;
    private static Controleur controleur ;/** Ce champ est le controleur reliant nos vues*/;
    private static VBoxAffichagePlanning affichagePlanning; /** Ce champ est une instance de la vue VBoxAffichagePlanning*/;


    /**
     * Cette méthode est le constructeur de notre classe
     */
    public HboxRoot(){
        super(80);
        controleur = new Controleur();
        formReservationPane = new GridPaneFormulaireReservation(controleur);
        calendrierPane = new VBoxCalendrier(controleur);
        planning = new PlanningCollections();
        affichagePlanning = new VBoxAffichagePlanning();
        this.getChildren().add(calendrierPane);
        this.getChildren().add(formReservationPane);
        this.getChildren().add(affichagePlanning);

    }


    /**
     * Cette méthode est un accesseur du champ planning
     * @return le planning comprenant toutes les réservations
     */
    public static PlanningCollections getPlanning(){
        return planning;
    }


    /**
     * Cette méthode est un accesseur du champ formReservationPane
     * @return la vue du formulaire de réservation
     */
    public static GridPaneFormulaireReservation getReservationPane(){
        return formReservationPane;
    }


    /**
     * Cette méthode est un accesseur du champ affichagePlanning
     * @return la vue affichant toutes les réservations faites
     */
    public static VBoxAffichagePlanning getAffichagePlanning(){return affichagePlanning;}
}
