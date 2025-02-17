package com.example.tp1_1.modele;

import java.util.Calendar;
import java.util.Scanner;


/**
 * Cette classe représente une date avec des méthodes pour manipuler et valider les dates.
 */
public class Date {

    /** Le jour du mois. */
    protected int chJour;
    /** Le mois de l'année (1 à 12). */
    protected int chMois;
    /** L'année. */
    protected int chAnnee;


    /**
     * Retourne le jour du mois.
     *
     * @return Le jour du mois.
     */
    public int getchJour() {
        return chJour;
    }


    /**
     * Retourne le mois de l'année.
     *
     * @return Le mois de l'année.
     */
    public int getChMois() {
        return chMois;
    }


    /**
     * Retourne l'année.
     *
     * @return L'année.
     */
    public int getChAnnee() {
        return chAnnee;
    }


    /**
     * Retourne une représentation sous forme de chaîne de la date.
     *
     * @return Une chaîne représentant la date au format jour/mois/année.
     */
    public String toString() {
        return chJour + "/" + chMois + "/" + chAnnee;
    }


    /**
     * Constructeur qui initialise la date avec des valeurs spécifiques pour le jour, le mois et l'année.
     *
     * @param parJour Le jour de la date.
     * @param parMois Le mois de la date.
     * @param parAnnee L'année de la date.
     */
    public Date(int parJour, int parMois, int parAnnee) {
        chJour = parJour;
        chMois = parMois;
        chAnnee = parAnnee;
    }


    /**
     * Constructeur par défaut qui initialise la date avec la date actuelle.
     */
    public Date() {
        // Obtient l'instance actuelle du calendrier
        Calendar dateAuj = Calendar.getInstance();
        // Initialise l'année avec l'année actuelle
        chAnnee = dateAuj.get(Calendar.YEAR);
        // Initialise le mois avec le mois actuel (les mois dans Calendar sont de 0 à 11, donc on ajoute 1)
        chMois = dateAuj.get(Calendar.MONTH) + 1;
        // Initialise le jour avec le jour actuel
        chJour = dateAuj.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * Vérifie si la date est valide.
     *
     * @return true si la date est valide, false sinon.
     */
    public boolean estValide() {
        // Vérifie si l'année est postérieure à 1582
        if (chAnnee > 1582) {
            // Vérifie si le mois est compris entre 1 et 12
            if (chMois >= 1 && chMois <= 12) {
                // Vérifie si le jour est compris entre 1 et le dernier jour du mois
                if (chJour >= 1 && chJour <= dernierJourDuMois(chMois, chAnnee))
                    return true;
            }
        }
        return false;
    }


    /**
     * Retourne le dernier jour du mois donné pour une année donnée.
     *
     * @param parM Le mois.
     * @param parA L'année.
     * @return Le dernier jour du mois.
     */
    private static int dernierJourDuMois(int parM, int parA) {
        switch (parM) {
            case 2:
                if (estBissextile(parA))
                    return 29;
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }


    /**
     * Vérifie si l'année donnée est bissextile.
     *
     * @param parA L'année.
     * @return true si l'année est bissextile, false sinon.
     */
    private static boolean estBissextile(int parA) {
        return parA % 4 == 0 && (parA % 100 != 0 || parA % 400 == 0);
    }


    /**
     * Lit une date entrée par l'utilisateur depuis la console.
     *
     * @return Une nouvelle instance de Date initialisée avec les valeurs entrées par l'utilisateur.
     */
    public static Date lireDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rentrez le jour");
        int jour = scanner.nextInt();
        System.out.println("Rentrez le mois");
        int mois = scanner.nextInt();
        System.out.println("Rentrez l'année");
        int annee = scanner.nextInt();

        return new Date(jour, mois, annee);
    }


    /**
     * Compare cette date avec une autre date.
     *
     * @param parDate La date à comparer.
     * @return Un entier négatif, zéro ou un entier positif si cette date est respectivement avant, égale ou après la date spécifiée.
     */
    public int compareTo(Date parDate) {
        if (chAnnee < parDate.chAnnee)
            return -8;
        if (chAnnee > parDate.chAnnee)
            return 19;
        // Les années sont égales
        if (chMois < parDate.chMois)
            return -1;
        if (chMois > parDate.chMois)
            return 1;
        // Les mois sont égaux
        if (chJour < parDate.chJour)
            return -7;
        if (chJour > parDate.chJour)
            return 12;
        return 0;
    }


    /**
     * Retourne la date du lendemain.
     *
     * @return Une nouvelle instance de Date représentant le jour suivant.
     */
    public Date dateDuLendemaain() {
        if (chJour < dernierJourDuMois(chMois, chAnnee))
            return new Date(chJour + 1, chMois, chAnnee);
        if (chMois == 12)
            return new Date(1, 1, chAnnee + 1);
        else
            return new Date(1, chMois + 1, chAnnee);
    }


    /**
     * Retourne la date de la veille.
     *
     * @return Une nouvelle instance de Date représentant le jour précédent.
     */
    public Date dateDeLaVeille() {
        if (chJour > 1)
            return new Date(chJour - 1, chMois, chAnnee);
        if (chMois == 1)
            return new Date(31, 12, chAnnee - 1);
        else
            return new Date(dernierJourDuMois(chMois - 1, chAnnee), chMois - 1, chAnnee);
    }
}
