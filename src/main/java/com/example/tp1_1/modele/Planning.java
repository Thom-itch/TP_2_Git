package com.example.tp1_1.modele;

/**
 * Cette classe représente un planning de réservations.
 * Le planning contient un tableau de réservations avec une taille définie.
 */
public class Planning {

    /** Tableau de réservations. */
    private Reservation[] chTabReservations;

    /** Taille du tableau de réservations. */
    private final int TAILLE;


    /**
     * Constructeur qui initialise le planning avec une taille spécifique.
     *
     * @param parTaille Taille du planning.
     */
    public Planning(int parTaille) {
        TAILLE = parTaille; // Initialise la taille du planning
        chTabReservations = new Reservation[TAILLE]; // Crée un tableau de réservations de la taille spécifiée
    }


    /**
     * Constructeur par défaut qui initialise le planning avec une taille de 20.
     */
    public Planning() {
        TAILLE = 20; // Définit la taille par défaut du planning à 20
        chTabReservations = new Reservation[20]; // Crée un tableau de réservations de taille 20
    }


    /**
     * Ajoute une réservation au planning.
     *
     * @param parReservation La réservation à ajouter.
     * @throws ExceptionPlanning Si le planning est plein, la réservation n'est pas valide, ou si elle existe déjà.
     */
    public void ajout(Reservation parReservation) throws ExceptionPlanning {
        // Vérifie si le tableau est plein
        if (chTabReservations[TAILLE - 1] != null) {
            throw new ExceptionPlanning(0); // Lève une exception si le planning est plein
        }

        // Vérifie si la réservation est valide
        if (!parReservation.estValide()) {
            throw new ExceptionPlanning(1); // Lève une exception si la réservation n'est pas valide
        }

        // Parcourt le tableau pour ajouter la réservation
        for (int i = 0; i < chTabReservations.length; i++) {
            // Si la case est vide
            if (chTabReservations[i] == null) {
                chTabReservations[i] = parReservation; // Ajoute la réservation à la première case vide
                break; // Sort de la boucle après avoir ajouté la réservation
            }
            // Si la réservation est déjà présente
            if (chTabReservations[i].compareTo(parReservation) == 0) {
                throw new ExceptionPlanning(2); // Lève une exception si la réservation existe déjà
            }
        }
    }


    /**
     * Retourne une représentation sous forme de chaîne du planning.
     *
     * @return Une chaîne représentant le planning.
     */
    public String toString() {
        String retour = "Voici le planning:"; // Initialise la chaîne de retour
        // Parcourt le tableau de réservations
        for (int i = 0; i < TAILLE - 1; i++) {
            if (chTabReservations[i] != null)
                retour += chTabReservations[i].toString() + "\n"; // Ajoute chaque réservation à la chaîne de retour
        }
        return retour; // Retourne la chaîne de représentation du planning
    }


    /**
     * Retourne la réservation correspondant à une date donnée.
     *
     * @param parDate La date de la réservation à rechercher.
     * @return La réservation correspondant à la date donnée, ou null si aucune réservation ne correspond.
     */
    public Reservation getReservation(Date parDate) {
        // Parcourt le tableau de réservations
        for (int i = 0; i < TAILLE; i++) {
            if (parDate.compareTo(chTabReservations[i].getDate()) == 0)
                return chTabReservations[i]; // Retourne la réservation correspondant à la date donnée
        }
        return null; // Retourne null si aucune réservation ne correspond
    }
}
