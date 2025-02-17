package com.example.tp1_1.modele;

/**
 * Cette classe représente une plage horaire avec une heure de début et une heure de fin.
 * La plage horaire doit avoir une durée minimale et les heures de début et de fin doivent être valides.
 */
public class PlageHoraire implements Comparable<PlageHoraire> {

    /** Durée minimale de la plage horaire en minutes. */
    private final static int DUREE_MIN = 60;

    /** Heure de début de la plage horaire. */
    private Horaire chDebut;

    /** Heure de fin de la plage horaire. */
    private Horaire chFin;


    /**
     * Constructeur qui initialise la plage horaire avec une heure de début et une heure de fin.
     *
     * @param parDebut Heure de début.
     * @param parFin Heure de fin.
     * @throws ExceptionPlanning Si la plage horaire n'est pas valide (début après la fin ou durée insuffisante).
     */
    public PlageHoraire(Horaire parDebut, Horaire parFin) throws ExceptionPlanning {
        int debutMin = parDebut.toMinutes();
        int finMin = parFin.toMinutes();

        // Vérifie si l'heure de début est après l'heure de fin
        if (debutMin > finMin)
            throw new ExceptionPlanning(4);

        // Vérifie si la durée de la plage horaire est inférieure à la durée minimale
        if (debutMin + DUREE_MIN > finMin)
            throw new ExceptionPlanning(3);

        // Initialise les attributs chDebut et chFin
        chDebut = parDebut;
        chFin = parFin;
    }


    /**
     * Retourne une représentation sous forme de chaîne de la plage horaire.
     *
     * @return Une chaîne représentant la plage horaire.
     */
    public String toString() {
        return chDebut + " à " + chFin;
    }


    /**
     * Vérifie si la plage horaire est valide.
     *
     * @return true si la plage horaire est valide, false sinon.
     */
    public boolean estValide() {
        int debutMin = chDebut.toMinutes();
        int finMin = chFin.toMinutes();

        // Vérifie si la durée de la plage horaire est supérieure ou égale à la durée minimale
        return debutMin + DUREE_MIN <= finMin;
    }


    /**
     * Retourne la durée de la plage horaire en minutes.
     *
     * @return La durée de la plage horaire en minutes.
     */
    public int duree() {
        int debutMin = chDebut.toMinutes();
        int finMin = chFin.toMinutes();
        return finMin - debutMin;
    }


    /**
     * Compare cette plage horaire avec une autre plage horaire.
     *
     * @param parPlage La plage horaire à comparer.
     * @return Un entier négatif, zéro ou un entier positif si cette plage horaire est respectivement avant, égale ou après la plage horaire spécifiée.
     */
    public int compareTo(PlageHoraire parPlage) {
        // Si cette plage horaire se termine avant que l'autre commence
        if (this.chFin.toMinutes() < parPlage.chDebut.toMinutes())
            return -1;

        // Si cette plage horaire commence après que l'autre se termine
        if (this.chDebut.toMinutes() > parPlage.chFin.toMinutes())
            return 1;

        // Les plages horaires se chevauchent
        return 0;
    }
}
