package com.example.tp1_1.modele;

/**
 * Cette classe représente une réservation avec une date, une plage horaire et un niveau.
 */
public class Reservation implements Comparable<Reservation> {

    /** La date de la réservation. */
    private DateCalendrier chDate;

    /** Le nom de la réservation. */
    private String chReserv;

    /** La plage horaire de la réservation. */
    private PlageHoraire chPH;

    /** Le niveau de la réservation. */
    private String niveau;


    /**
     * Constructeur de la classe Reservation.
     *
     * @param parReserv Le nom de la réservation.
     * @param parDate La date de la réservation.
     * @param parPH La plage horaire de la réservation.
     * @param parNiveau Le niveau de la réservation.
     * @throws ExceptionPlanning Si le nom de la réservation est invalide.
     */
    public Reservation(String parReserv, DateCalendrier parDate, PlageHoraire parPH, String parNiveau) throws ExceptionPlanning {
        // Vérifie si le nom de la réservation est nul ou vide
        if (parReserv == null || parReserv.length() == 0)
            throw new ExceptionPlanning(5); // Lève une exception si le nom est invalide

        chDate = parDate; // Initialise la date de la réservation
        chReserv = parReserv; // Initialise le nom de la réservation
        chPH = parPH; // Initialise la plage horaire de la réservation
        niveau = parNiveau; // Initialise le niveau de la réservation
    }


    /**
     * Retourne une représentation sous forme de chaîne de la réservation.
     *
     * @return Une chaîne représentant la réservation.
     */
    public String toString() {
        return " " + chReserv + " " + chPH.toString() + " " + chDate.toString(); // Retourne les détails de la réservation
    }


    /**
     * Compare cette réservation à une autre réservation.
     *
     * @param parReservation La réservation à comparer.
     * @return Un entier négatif, zéro ou un entier positif si cette réservation est moins que, égale à ou supérieure à la réservation spécifiée.
     */
    public int compareTo(Reservation parReservation) {
        // Compare les dates des réservations
        if (chDate.compareTo(parReservation.chDate) != 0)
            return chDate.compareTo(parReservation.chDate);

        // Compare les plages horaires si les dates sont égales
        return chPH.compareTo(parReservation.chPH);
    }


    /**
     * Vérifie si la réservation est valide.
     *
     * @return true si la réservation est valide, false sinon.
     */
    public boolean estValide() {
        return chDate.estValide() && chPH.estValide(); // Vérifie si la date et la plage horaire sont valides
    }


    /**
     * Retourne le nom de la réservation.
     *
     * @return Le nom de la réservation.
     */
    public String getChReserv() {
        return chReserv;
    }


    /**
     * Retourne la date de la réservation.
     *
     * @return La date de la réservation.
     */
    public DateCalendrier getDate() {
        return chDate;
    }


    /**
     * Retourne la plage horaire de la réservation.
     *
     * @return La plage horaire de la réservation.
     */
    public PlageHoraire getPh() {
        return chPH;
    }


    /**
     * Retourne le niveau de la réservation.
     *
     * @return Le niveau de la réservation.
     */
    public String getNiveau() {
        return niveau;
    }
}
