package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Word;
import model.Joueur;
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

    public int demanderNbJoueurs() {
        int choix = 0;
        while (choix != 1 && choix != 2) {
            System.out.print("A combien souhaitez vous jouer ? (1 ou 2 joueurs) : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                scanner.nextLine();
            }
        }
        return choix;
    }


    public String demanderModeDeJeu() {
        String mode = "";
        while (!mode.equals("POINT") && !mode.equals("CHRONO")) {
            System.out.print("Choisir le mode de jeu (POINT / CHRONO) : ");
            mode = scanner.nextLine().toUpperCase();
        }
        return mode;
    }

    public int demanderNbTours() {
        int tours = 0;
        while (tours != 1 && tours != 5 && tours != 10 && tours != 20) {
            System.out.print("Nombre de tours (1, 5, 10, 20) : ");
            if (scanner.hasNextInt()) {
                tours = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                scanner.nextLine();
            }
        }
        return tours;
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public void afficherManche(int tour, int nbTours) {
        System.out.println("\\n=== MANCHE \" + tour + \" SUR \" + nbTours + \" ===");
    }

    public void afficherTourJoueur(int numeroJoueur) {
        System.out.println("\nTour du Joueur " + numeroJoueur);
    }


    public void afficherBilanSolo(Joueur joueur, String mode) {
        System.out.println("\n==================================");
        System.out.println("          BILAN DU JEU            ");
        System.out.println("==================================");
        System.out.println("Joueur : " + joueur.getNom());
        if (mode.equals("POINT")) {
            System.out.println("Score final : " + joueur.getScoreGlobal() + " points");
        } else {
            System.out.println("Temps total : " + (joueur.getTempsCumule() / 1000) + " secondes");
        }
        System.out.println("==================================");
    }


    public void afficherGagnant(Joueur gagnant, boolean egalite, String mode) {
        System.out.println("\n==================================");
        System.out.println("          FIN DU TOURNOI          ");
        System.out.println("==================================");
        if (egalite) {
            System.out.println("C'est une ÉGALITÉ PARFAITE !");
        } else {
            System.out.println("LE GRAND GAGNANT EST : " + gagnant.getNom().toUpperCase());
            if (mode.equals("POINT")) {
                System.out.println("Avec un score de : " + gagnant.getScoreGlobal() + " points !");
            } else {
                System.out.println("Avec un temps total de : " + (gagnant.getTempsCumule() / 1000) + " secondes !");
            }
        }
        System.out.println("==================================");
    }
}