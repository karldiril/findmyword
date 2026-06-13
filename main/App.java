package main;

import view.GameUI;
import controller.Game;
import controller.GamePoints;
import controller.GameChrono;
import model.Joueur;
import model.Word;
import model.ResultatPartie;
import repository.WordRepository;
import repository.JsonWordRepository; 

public class App {
    public void demarrer() {
        GameUI ui = new GameUI();
        
        // Affichage de l'accueil
        ui.afficherAccueil();

        // 1. Configuration de la partie (manches, mode de jeu et nombre de joueurs)
        int nbJoueurs = ui.demanderNbJoueurs();
        String mode = ui.demanderModeDeJeu();
        int nbTours = ui.demanderNbTours();
        
        WordRepository repo = new JsonWordRepository("data/mots.json");
        
        // Création des objets Joueur
        Joueur[] joueurs = new Joueur[nbJoueurs];
        for (int i = 0; i < nbJoueurs; i++) {
            String nom = ui.demanderNomJoueur(i + 1);
            joueurs[i] = new Joueur(nom);
        }

        // Boucle principale des manches
        for (int tour = 1; tour <= nbTours; tour++) {
            ui.afficherManche(tour, nbTours);
            
            // Boucle secondaire : Les Joueurs
            for (int i = 0; i < nbJoueurs; i++) {
                Joueur joueurActuel = joueurs[i];
                ui.afficherTourJoueur(i + 1);
                Game partie;
                // Instanciation du mode de jeu choisi par l'utilisateur
                if (mode.equals("POINT")) {
                    partie = new GamePoints(repo, joueurActuel);
                }
                else {
                    partie = new GameChrono(repo, joueurActuel);
                }
                
                // Démarre le chrono si on est en mode CHRONO
                partie.executerActionAvantSaisie();
                
                boolean aTrouve = false;
                int numEssai = 0;

                // 3. Boucle de jeu (Les essais du joueur)
                while (!partie.isPartieTerminee()) {
                    
                    if (partie instanceof GameChrono) {
                        ui.afficherTempsRestant(((GameChrono) partie).getTempsRestant());
                    }

                    // Demande et validation du mot
                    String saisie = ui.demanderSaisieMot(joueurActuel.getNom());
                    Word proposition = new Word(saisie);
                    int codeErreur = proposition.verifierValidite();

                    if (codeErreur == Word.VALIDE) {
                        numEssai++;
                        
                        // Analyse et affichage de la grille
                        partie.analyserTentatives(proposition);
                        ui.afficherGrille(partie.getTentatives(), partie.getAnalyses());
                        
                        // Vérification de la victoire
                        aTrouve = proposition.getTexte().equals(partie.getMotSecret().getTexte());
                        
                        // Mise à jour de l'état de la partie (et attribution des points/temps)
                        partie.verifierFinDeTour(numEssai, aTrouve);
                        
                    } else {
                        // Affichage de l'erreur grâce à la vue
                        ui.afficherErreurValidation(codeErreur);
                    }
                }
                
                // 4. Bilan de fin de tour
                ui.afficherFinPartie(aTrouve, partie.getMotSecret());

                // Création des stats cumulées du joueur
                ResultatPartie res = new ResultatPartie(
                    partie.getNomMode(),
                    partie.getMotSecret().getTexte(),
                    aTrouve,
                    joueurActuel.getScoreGlobal(),
                    joueurActuel.getTempsCumule()
                );
                
                ui.afficherResultat(res);
            }

            
        }
        // calcul et affichage du gagnant
        if (nbJoueurs == 1) {
            ui.afficherBilanSolo(joueurs[0], mode);
        } else if (nbJoueurs == 2) {
            // 1. On calcule (Contrôleur)
            Joueur gagnant = determinerGagnant(joueurs[0], joueurs[1], mode);
            boolean egalite = (gagnant == null);
            
            // 2. On affiche (Vue)
            ui.afficherGagnant(gagnant, egalite, mode);
        }
        // Message de fin
        ui.afficherMessage("\nFin de partie ! Merci d'avoir joué.");
    }

    private Joueur determinerGagnant(Joueur j1, Joueur j2, String mode) {
        if (mode.equals("POINT")) {
            if (j1.getScoreGlobal() > j2.getScoreGlobal()) {
                return j1;
            } else if (j2.getScoreGlobal() > j1.getScoreGlobal()) {
                return j2;
            }
        } else {
            // Mode CHRONO : le gagnant a le temps le plus faible
            if (j1.getTempsCumule() < j2.getTempsCumule()) {
                return j1;
            } else if (j2.getTempsCumule() < j1.getTempsCumule()) {
                return j2;
            }
        }
        
        // Si aucun des 'return' précédents n'a été déclenché, c'est une égalité
        return null; 
    }
}