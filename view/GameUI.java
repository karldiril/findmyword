package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Word;
import model.ResultatPartie;


public class GameUI {
    private Scanner scanner;

    public GameUI() {
        this.scanner = new Scanner(System.in);
    }

    public void afficherAccueil() {
        System.out.println("=====================================");
        System.out.println("       FIND MY WORD - BUT1           ");
        System.out.println("      Créé par Iyad et Karl          ");
        System.out.println("=====================================");
        System.out.println();
    }

    public String demanderNomJoueur(int numeroJoueur) {
        System.out.print("Entrez le nom du joueur " + numeroJoueur + " : ");
        return scanner.nextLine();
    }

    public String demanderSaisieMot(String nomJoueur) {
        System.out.print("\n" + nomJoueur + ", proposez un mot : ");
        return scanner.nextLine().toUpperCase();
    }

    public void afficherErreurValidation(int codeErreur) {
        if (codeErreur == Word.ERREUR_LONGUEUR) {
            System.out.println("Erreur : le mot doit faire " + Word.TAILLE_MOT + "lettres.");
        }
        else if (codeErreur == Word.ERREUR_CARACTERE) {
            System.out.println("Erreur : Seules les lettres sont autorisées.");
        }
        else if (codeErreur == Word.ERREUR_DOUBLON) {
            System.out.println("Erreur : Pas de lettres en double dans le mot.");
        }
    }

    public void afficherGrille(ArrayList<Word> tentatives, ArrayList<String[]> analyses) {
        System.out.println("\n--- Etat de la partie ---");
        for (int i = 0; i < tentatives.size(); i++) {
            String mot = tentatives.get(i).getTexte().toUpperCase();
            String[] analyse = analyses.get(i);

            for (int j = 0; j < mot.length(); j++) {
                System.out.print("[ " + mot.charAt(j) + " ]");
            }

            System.out.print(" --> ");

            for (String etat : analyse) {
                System.out.printf("%-10s", etat);
            }
            System.out.println();
        }
    }

    public void afficherTempsRestant(long tempsRestantMs) {
        long secondes = tempsRestantMs / 1000;
        System.out.println("Temps restant : " + secondes + " secondes.");
    }

    public void afficherFinPartie(boolean gagne, Word motSecret) {
        System.out.println("\n--------------------------------");
        if (gagne) {
            System.out.println("Bravo !! Le mot était bien : " + motSecret.getTexte().toUpperCase() + " !!!");
        }
        else {
            System.out.println("La partie est terminé et vous n'avez pas trouvé le mot :'(");
        }
        System.out.println("-------------------------------------------");
    }


    public void afficherResultat(ResultatPartie resultat) {
    System.out.println("\n=== RÉCAPITULATIF DE LA PARTIE ===");
    System.out.println("Mode de jeu : " + resultat.getModeJeu());
    System.out.println("Mot secret  : " + resultat.getMotSecret().toUpperCase());
    
    // Affichage conditionnel pour le résultat (GAGNÉ/PERDU)
    if (resultat.estTrouve()) {
        System.out.println("Résultat : GAGNÉ");
    } else {
        System.out.println("Résultat : PERDU");
    }

    // Affichage conditionnel selon le mode
    if (resultat.getModeJeu().equals("POINT")) {
        System.out.println("Score       : " + resultat.getScoreObtenu());
    } else {
        System.out.println("Temps passé : " + (resultat.getTempsPasse() / 1000) + " secondes");
    }
    
    System.out.println("==================================");
}
}