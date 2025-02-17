package com.example.tp1_1.vue;

import com.example.tp1_1.controleur.Controleur;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import com.example.tp1_1.modele.DateCalendrier;


/**
 * La classe GridPaneFormulaireReservation est une vue qui permet de faire une réservation.
 * Cette vue permet de choisir l'intitulé, le niveau et la plage horaire de la réservation.
 */
public class GridPaneFormulaireReservation extends GridPane {

    /** Compteur de ligne pour le placement des éléments dans la grille. */
    public int ligne = 0;

    /** La date sélectionnée pour la réservation. */
    private DateCalendrier date;

    /** Un ToggleGroup regroupant tous les RadioButton des niveaux. */
    private ToggleGroup group;

    /** La zone de texte permettant de rentrer l'intitulé de la réservation. */
    private TextField zoneTexte;

    /** Un tableau comprenant le choix des horaires. */
    private ComboBox<Integer>[] listeCombo = new ComboBox[4];

    /** Un label affichant la date sélectionnée. */
    private Label labelTitle;

    /** Le contrôleur mis en paramètre. */
    private Controleur controleur;

    /** Un label affichant l'exception levée. */
    private Label labelException = new Label("");


    /**
     * Constructeur de la classe GridPaneFormulaireReservation.
     * @param parControleur le contrôleur reliant nos vues.
     */
    public GridPaneFormulaireReservation(Controleur parControleur) {
        super();
        controleur = parControleur;

        // Configure la grille
        this.setGridLinesVisible(false);
        this.setHgap(9);
        this.setVgap(14);

        // Initialise la date et le titre
        date = new DateCalendrier();
        labelTitle = new Label(date.toString());
        this.add(labelTitle, 2, ligne, 5, 1);

        // Ajoute le champ pour le nom du cours
        Label labelField = new Label("Cours");
        zoneTexte = new TextField();
        ligne++;
        this.add(labelField, 0, ligne);
        this.add(zoneTexte, 1, ligne, 5, 1);

        // Ajoute les options pour le niveau
        Label labelNiv = new Label("Niveau");
        group = new ToggleGroup();
        RadioButton radio1 = new RadioButton("Débutant");
        radio1.setSelected(true);
        radio1.setToggleGroup(group);
        RadioButton radio2 = new RadioButton("Moyen");
        radio2.setToggleGroup(group);
        RadioButton radio3 = new RadioButton("Avancé");
        radio3.setToggleGroup(group);
        RadioButton radio4 = new RadioButton("Expert");
        radio4.setToggleGroup(group);

        ligne++;
        this.add(labelNiv, 0, ligne);
        this.add(radio1, 2, ligne);
        this.add(radio2, 4, ligne);
        ligne++;
        this.add(radio3, 2, ligne);
        this.add(radio4, 4, ligne);
        ligne++;

        // Ajoute les options pour les horaires
        Label labelHoraire = new Label("Horaire");
        Label LabelDe = new Label("de");
        this.add(labelHoraire, 0, ligne);
        this.add(LabelDe, 1, ligne);

        ComboBox<Integer> heures1 = new ComboBox<>();
        listeCombo[0] = heures1;
        ComboBox<Integer> heures2 = new ComboBox<>();
        listeCombo[2] = heures2;
        ComboBox<Integer> minutes1 = new ComboBox<>();
        listeCombo[1] = minutes1;
        ComboBox<Integer> minutes2 = new ComboBox<>();
        listeCombo[3] = minutes2;

        for (int i = 0; i < 60; i += 15) {
            minutes1.getItems().add(i);
            minutes2.getItems().add(i);
        }
        for (int i = 6; i < 21; i++) {
            heures1.getItems().add(i);
            heures2.getItems().add(i);
        }
        heures1.getSelectionModel().selectFirst();
        heures2.getSelectionModel().selectFirst();
        heures2.getSelectionModel().selectNext();
        minutes2.getSelectionModel().selectFirst();
        minutes1.getSelectionModel().selectFirst();

        this.add(heures1, 2, ligne);
        this.add(minutes1, 4, ligne);

        Label labelH = new Label("h");
        Label labelMN = new Label("mn");
        this.add(labelH, 3, ligne);
        this.add(labelMN, 5, ligne);

        ligne++;
        this.add(heures2, 2, ligne);
        this.add(minutes2, 4, ligne);

        Label labelA = new Label("à");
        this.add(labelA, 1, ligne);
        Label labelH2 = new Label("h");
        Label labelMN2 = new Label("mn");
        this.add(labelH2, 3, ligne);
        this.add(labelMN2, 5, ligne);
        ligne++;

        // Ajoute les boutons d'annulation et d'enregistrement
        Button annule = new Button("A_nnuler");
        Button save = new Button("_Enregistrer");
        this.add(annule, 2, ligne, 2, 1);
        this.add(save, 4, ligne, 2, 1);
        ligne++;

        // Ajoute le label pour les exceptions
        this.add(labelException, 2, ligne, 2, 1);

        // Configure les actions des boutons
        save.setOnAction(controleur);
        annule.setUserData("annule");
        annule.setOnAction(controleur);

        // Focus sur la zone de texte
        Platform.runLater(() -> zoneTexte.requestFocus());
    }


    /**
     * Accesseur du champ date.
     * @return la date de la réservation en cours.
     */
    public DateCalendrier getDate() {
        return date;
    }


    /**
     * Modificateur du champ date.
     * @param parDate la nouvelle date de la réservation.
     */
    public void setDate(DateCalendrier parDate) {
        date = parDate;
        labelTitle.setText(date.toString());
    }


    /**
     * Accesseur du champ group.
     * @return le bouton sélectionné du ToggleGroup (niveau sélectionné).
     */
    public RadioButton getRadioButton() {
        return (RadioButton) group.getSelectedToggle();
    }


    /**
     * Accesseur du champ zoneTexte.
     * @return le texte entré dans la zone de texte.
     */
    public String getZoneTexte() {
        return zoneTexte.getText();
    }


    /**
     * Accesseur du champ listeCombo.
     * @return une liste comportant tous les ComboBox.
     */
    public ComboBox<Integer>[] getCombo() {
        return listeCombo;
    }


    /**
     * Réinitialise le formulaire.
     */
    public void restart() {
        int compt = 0;
        for (ComboBox<Integer> combo : listeCombo) {
            combo.getSelectionModel().selectFirst();
            if (compt == 2)
                combo.getSelectionModel().selectNext();
            compt++;
        }
        zoneTexte.setText("");
        Platform.runLater(() -> zoneTexte.requestFocus());
        group.selectToggle(group.getToggles().get(0));
        this.setLabelException("");
    }


    /**
     * Modificateur du champ labelException.
     * Permet de connaître l'exception si la réservation n'a pas fonctionné.
     * @param parException le message d'exception.
     */
    public void setLabelException(String parException) {
        labelException.setText(parException);
    }
}
