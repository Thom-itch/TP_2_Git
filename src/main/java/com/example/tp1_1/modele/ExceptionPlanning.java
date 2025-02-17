package com.example.tp1_1.modele;


/**
 * Représente une exception spécifique pour la gestion des plannings.
 */
public class ExceptionPlanning extends Exception {

    /** Le code d'erreur associé à l'exception. */
    private int codeErreur;


    /**
     * Constructeur qui initialise l'exception avec un code d'erreur spécifique.
     *
     * @param parcodeErreur Le code d'erreur.
     */
    public ExceptionPlanning(int parcodeErreur) {
        super();
        // Initialise le code d'erreur avec la valeur fournie
        this.codeErreur = parcodeErreur;
    }


    /**
     * Retourne un message d'erreur détaillé basé sur le code d'erreur.
     *
     * @return Une chaîne décrivant l'erreur.
     */
    public String getErreur() {
        // Retourne une chaîne formatée avec le code d'erreur et le message d'erreur correspondant
        return "Erreur " + codeErreur + " : " + ConstantesErreur.ERREURS[codeErreur];
    }
}
