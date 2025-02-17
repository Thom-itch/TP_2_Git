package com.example.tp1_1.modele;

import java.util.Calendar;


/**
 * Représente une date avec des informations supplémentaires telles que le jour de la semaine et la semaine de l'année.
 */
public class DateCalendrier extends Date implements ConstantesCalendrier, Comparable<Date>  {

    /** Le jour de la semaine (1 = lundi, ..., 7 = dimanche). */
    private int jourSemaine;
    /** La semaine de l'année. */
    private int weekOfYear;


    /**
     * Constructeur qui initialise la date avec la date actuelle.
     */
    public DateCalendrier() {
        // Obtient l'instance actuelle du calendrier
        Calendar dateAuj = Calendar.getInstance();
        // Initialise l'année avec l'année actuelle
        chAnnee = dateAuj.get(Calendar.YEAR);
        // Initialise le mois avec le mois actuel (les mois dans Calendar sont de 0 à 11, donc on ajoute 1)
        chMois = dateAuj.get(Calendar.MONTH) + 1;
        // Initialise le jour avec le jour actuel
        chJour = dateAuj.get(Calendar.DAY_OF_MONTH);
        // Initialise le jour de la semaine (Calendar utilise 1 pour dimanche, donc on ajuste)
        jourSemaine = dateAuj.get(Calendar.DAY_OF_WEEK);
        if (jourSemaine == 1)
            jourSemaine = 7;
        else
            jourSemaine -= 1;
        // Initialise la semaine de l'année
        weekOfYear = dateAuj.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * Constructeur qui initialise la date avec des valeurs spécifiques pour le jour, le mois et l'année.
     *
     * @param parJour Le jour de la date.
     * @param parMois Le mois de la date.
     * @param parAnnee L'année de la date.
     */
    public DateCalendrier(int parJour, int parMois, int parAnnee) {
        // Appelle le constructeur de la classe parente (Date)
        super(parJour, parMois, parAnnee);
        // Obtient une instance de calendrier et définit la date avec les valeurs fournies
        Calendar date = Calendar.getInstance();
        date.set(chAnnee, chMois - 1, chJour); // Les mois dans Calendar sont de 0 à 11
        // Initialise le jour de la semaine
        jourSemaine = date.get(Calendar.DAY_OF_WEEK);
        if (jourSemaine == 1)
            jourSemaine = 7;
        else
            jourSemaine -= 1;
        // Initialise la semaine de l'année
        weekOfYear = date.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * Retourne une représentation sous forme de chaîne de la date.
     *
     * @return Une chaîne représentant le jour de la semaine, le jour du mois et le mois.
     */
    public String toString() {
        return JOURS_SEMAINE[jourSemaine - 1] + " " + chJour + " " + MOIS[chMois - 1];
    }


    /**
     * Retourne le jour de la semaine.
     *
     * @return Le jour de la semaine (1 = lundi, ..., 7 = dimanche).
     */
    public int getJourSemaine() {
        return jourSemaine;
    }


    /**
     * Vérifie si la date représente la date d'aujourd'hui.
     *
     * @return true si la date est aujourd'hui, false sinon.
     */
    public boolean isToday() {
        return this.compareTo(new DateCalendrier()) == 0;
    }


    /**
     * Retourne la semaine de l'année.
     *
     * @return La semaine de l'année.
     */
    public int getWeekOfYear() {
        return weekOfYear;
    }


    /**
     * Retourne la date du lendemain.
     *
     * @return Une nouvelle instance de DateCalendrier représentant le jour suivant.
     */
    public DateCalendrier dateDuLendemain() {
        Date dateLendemain = super.dateDuLendemaain();
        return new DateCalendrier(dateLendemain.chJour, dateLendemain.chMois, dateLendemain.chAnnee);
    }


    /**
     * Retourne la date de la veille.
     *
     * @return Une nouvelle instance de DateCalendrier représentant le jour précédent.
     */
    public DateCalendrier dateDeLaVeille() {
        Date dateVeille = super.dateDeLaVeille();
        return new DateCalendrier(dateVeille.chJour, dateVeille.chMois, dateVeille.chAnnee);
    }


    /**
     * Compare cette date avec une autre date.
     *
     * @param o La date à comparer.
     * @return Un entier négatif, zéro ou un entier positif si cette date est respectivement avant, égale ou après la date spécifiée.
     */
    // @Override
    // public int compareTo(Date o) {
    //     return 0;
    // }
}
