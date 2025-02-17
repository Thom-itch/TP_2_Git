package com.example.tp1_1.modele;

import java.util.Collection;
import java.util.TreeSet;


/**
 * Représente un calendrier pour un mois donné d'une année donnée.
 */
public class CalendrierDuMois {

    /** Le mois représenté par ce calendrier (1 = janvier, ..., 12 = décembre). */
    private int mois;
    /** L'année représentée par ce calendrier. */
    private int annee;
    /** Une collection de dates (jours) dans le mois, stockées dans un TreeSet pour les trier. */
    private Collection<DateCalendrier> treeSetDate;


    /**
     * Constructeur qui initialise le calendrier pour un mois et une année spécifiques.
     *
     * @param mois Le mois du calendrier.
     * @param annee L'année du calendrier.
     */
    public CalendrierDuMois(int mois, int annee) {
        this.mois = mois;
        this.annee = annee;
        treeSetDate = new TreeSet<DateCalendrier>();

        // Initialise la première date du mois
        DateCalendrier date = new DateCalendrier(1, mois, annee);
        // Obtient le jour de la semaine de cette date
        int indiceJour = date.getJourSemaine();

        // Ajoute les jours précédents du mois précédent jusqu'à ce que le premier jour de la semaine soit atteint
        for (int x = indiceJour; x != 0; x--) {
            treeSetDate.add(date);
            date = date.dateDeLaVeille();
        }

        // Réinitialise la date au deuxième jour du mois
        date = new DateCalendrier(2, mois, annee);
        // Remet à jour l'indice du jour de la semaine
        indiceJour = indiceJour % 7;

        // Ajoute les dates du mois courant jusqu'à la fin du mois
        while (date.chMois == mois) {
            while (indiceJour < 7) {
                treeSetDate.add(date);
                date = date.dateDuLendemain();
                indiceJour++;
            }
            indiceJour = 0;
        }
    }


    /**
     * Retourne la collection de dates dans le calendrier.
     *
     * @return La collection de dates dans le calendrier.
     */
    public Collection<DateCalendrier> getDates() {
        return treeSetDate;
    }


    /**
     * Retourne une représentation sous forme de chaîne du calendrier.
     *
     * @return Une chaîne représentant le nombre de dates et les dates elles-mêmes.
     */
    @Override
    public String toString() {
        return treeSetDate.size() + " " + treeSetDate.toString();
    }


    /**
     * Retourne le mois du calendrier.
     *
     * @return Le mois du calendrier.
     */
    public int getMois() {
        return mois;
    }


    /**
     * Retourne l'année du calendrier.
     *
     * @return L'année du calendrier.
     */
    public int getAnnee() {
        return annee;
    }
}
