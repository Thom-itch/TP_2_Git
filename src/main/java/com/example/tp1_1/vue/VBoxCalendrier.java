package com.example.tp1_1.vue;

import com.example.tp1_1.controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import com.example.tp1_1.modele.CalendrierDuMois;
import com.example.tp1_1.modele.ConstantesCalendrier;
import com.example.tp1_1.modele.Date;
import com.example.tp1_1.modele.DateCalendrier;

import java.util.List;


/**
 * Cette classe est une vue nous permettant d'afficher le calendrier de l'année 2024
 * Nous pourrons à partir de cette vue sélectionner une date.
 */
public class VBoxCalendrier extends VBox implements ConstantesCalendrier {
    private int currentIndex; // Index du calendrier du mois visible sur le StackPane
    private int annee; // Année du calendrier
    private Controleur controleur; // Controleur reliant nos vues


    /**
     * Constructeur de la classe VBoxCalendrier.
     * @param parControleur Le controleur reliant nos vues.
     */
    public VBoxCalendrier(Controleur parControleur) {
        controleur = parControleur;
        Date today = new Date();
        StackPane stackPaneMois = new StackPane();
        ToggleButton button = new ToggleButton(today.toString());
        ToggleGroup buttonGroup = new ToggleGroup();

        // Création du calendrier pour chaque mois de l'année
        for (int numMois = 1; numMois <= 12; numMois++) {
            CalendrierDuMois monthCalendar = new CalendrierDuMois(numMois, today.getChAnnee());
            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(7);
            tilePane.setPrefRows(monthCalendar.getDates().size() % 7 + 1);
            tilePane.setId("opaque");

            // Ajout des libellés des jours de la semaine
            for (String jourAb : JOURS_SEMAINE) {
                Label labelJour = new Label(jourAb);
                tilePane.getChildren().add(labelJour);
            }

            // Ajout des boutons de date pour chaque jour du mois
            for (DateCalendrier date : monthCalendar.getDates()) {
                ToggleButton boutonDate = new ToggleButton(Integer.toString(date.getchJour()));
                boutonDate.setToggleGroup(buttonGroup);
                tilePane.getChildren().add(boutonDate);
                boutonDate.setUserData(date);
                boutonDate.setOnAction(controleur);
                if (date.getChMois() != monthCalendar.getMois()) {
                    boutonDate.setId("dateHorsMois");
                }
                if (date.isToday()) {
                    boutonDate.setId("today");
                }
            }
            tilePane.setAccessibleText(MOIS[numMois - 1]);
            stackPaneMois.getChildren().add(tilePane);
        }

        // Réorganisation des mois pour afficher le mois actuel en premier
        List<Node> listeNode = stackPaneMois.getChildren();
        final int lastIndice = listeNode.size() - 1;
        Node premierMois = listeNode.get(0);
        Node dernierMois = listeNode.get(listeNode.size() - 1);

        while (!listeNode.get(lastIndice).getAccessibleText().equals(MOIS[today.getChMois() - 1])) {
            listeNode.get(lastIndice).toBack();
        }

        // Création des boutons de navigation entre les mois
        HBox boutons = new HBox();
        Button precedent = new Button("<");
        Button apres = new Button(">");
        boutons.getChildren().addAll(precedent, apres);
        boutons.setStyle("-fx-alignment: center-right;");
        boutons.setPadding(new Insets(10, 10, 10, 10));
        boutons.setSpacing(10);
        currentIndex = today.getChMois() - 1;
        annee = 2024;
        Label labelMois = new Label(MOIS[currentIndex] + annee);

        // Action pour passer au mois suivant
        apres.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listeNode.get(0).toFront();
                if (currentIndex == 11) {
                    currentIndex = 0;
                } else {
                    currentIndex++;
                }
                labelMois.setText(MOIS[currentIndex] + annee);
            }
        });

        // Action pour passer au mois précédent
        precedent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listeNode.get(lastIndice).toBack();
                if (currentIndex == 0) {
                    currentIndex = 11;
                } else {
                    currentIndex--;
                }
                labelMois.setText(MOIS[currentIndex] + annee);
            }
        });

        // Assemblage final des éléments de la vue
        VBox bas = new VBox();
        bas.getChildren().addAll(labelMois, boutons);
        this.getChildren().addAll(stackPaneMois, bas);
    }
}

