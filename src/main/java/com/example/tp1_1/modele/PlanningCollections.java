package com.example.tp1_1.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Cette classe représente un planning utilisant des collections pour gérer les réservations.
 * Les réservations sont stockées dans une liste, un ensemble trié et une map.
 */
public class PlanningCollections implements ConstantesCalendrier {

    /** Liste des réservations. */
    private ArrayList<Reservation> chReservlist;

    /** Ensemble trié des réservations. */
    private TreeSet<Reservation> chReservset;

    /** Map des réservations par semaine de l'année. */
    private TreeMap<Integer, TreeSet<Reservation>> chReservmap;

    /** Taille maximale du planning. */
    private final int TAILLE;


    /**
     * Constructeur qui initialise les collections de réservations avec une taille par défaut de 20.
     */
    public PlanningCollections() {
        chReservlist = new ArrayList<>(); // Initialise la liste des réservations
        chReservset = new TreeSet<>(); // Initialise l'ensemble trié des réservations
        chReservmap = new TreeMap<>(); // Initialise la map des réservations par semaine
        TAILLE = 20; // Définit la taille maximale du planning à 20
    }


    /**
     * Retourne une représentation sous forme de chaîne des réservations dans la map.
     *
     * @return Une chaîne représentant les réservations par semaine.
     */
    public String toString() {
        return chReservmap.toString(); // Retourne la map des réservations sous forme de chaîne
    }


    /**
     * Ajoute une réservation aux collections de réservations.
     *
     * @param parReservation La réservation à ajouter.
     * @throws ExceptionPlanning Si le planning est plein, la réservation n'est pas valide ou existe déjà.
     */
    public void ajout(Reservation parReservation) throws ExceptionPlanning {
        // Vérifie si la liste est pleine
        if (chReservlist.size() >= TAILLE) {
            throw new ExceptionPlanning(0); // Lève une exception si le planning est plein
        }

        // Vérifie si la réservation est valide
        if (!parReservation.estValide()) {
            throw new ExceptionPlanning(1); // Lève une exception si la réservation n'est pas valide
        }

        // Vérifie si la réservation existe déjà
        for (Reservation reserv : chReservlist) {
            if (parReservation.compareTo(reserv) == 0) {
                throw new ExceptionPlanning(2); // Lève une exception si la réservation existe déjà
            }
        }

        // Ajoute la réservation à la liste et à l'ensemble trié
        chReservlist.add(parReservation);
        chReservset.add(parReservation);

        // Ajoute la réservation à la map des réservations par semaine
        int semaine = parReservation.getDate().getWeekOfYear();
        if (chReservmap.containsKey(semaine)) {
            TreeSet<Reservation> set = chReservmap.get(semaine);
            set.add(parReservation);
        } else {
            TreeSet<Reservation> set = new TreeSet<>();
            set.add(parReservation);
            chReservmap.put(semaine, set);
        }
    }


    /**
     * Retourne les réservations pour une date donnée.
     *
     * @param parDate La date des réservations à rechercher.
     * @return Un ensemble trié des réservations pour la date donnée.
     */
    public TreeSet<Reservation> getReservations(DateCalendrier parDate) {
        TreeSet<Reservation> nouveau = new TreeSet<>();
        Iterator<Reservation> it = chReservset.iterator();
        while (it.hasNext()) {
            Reservation r = it.next();
            if (r.getDate().compareTo(parDate) == 0) {
                nouveau.add(r);
            }
        }
        return nouveau;
    }


    /**
     * Retourne les réservations pour un libellé donné.
     *
     * @param parString Le libellé des réservations à rechercher.
     * @return Un ensemble trié des réservations pour le libellé donné.
     */
    public TreeSet<Reservation> getReservations(String parString) {
        TreeSet<Reservation> nouveau = new TreeSet<>();
        Iterator<Reservation> it = chReservset.iterator();
        while (it.hasNext()) {
            Reservation r = it.next();
            if (r.getChReserv().equals(parString)) {
                nouveau.add(r);
            }
        }
        return nouveau;
    }


    /**
     * Retourne les réservations pour une semaine donnée.
     *
     * @param semaine La semaine des réservations à rechercher.
     * @return Un ensemble trié des réservations pour la semaine donnée.
     */
    public TreeSet<Reservation> getReservationsSemaine(int semaine) {
        return chReservmap.get(semaine);
    }
}
