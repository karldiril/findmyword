package controller;

import model.Joueur;
import repository.WordRepository;

public class GameChrono extends Game {
    private long tempsDebut;
    private final int TEMPS_MAX_SECONDES = 30;

    public GameChrono(WordRepository repo, Joueur joueur) {
        super(repo, joueur);
    }

    public void executerActionAvantSaisie() {
        if(getTentatives().isEmpty()) {
            this.tempsDebut = System.currentTimeMillis();
        }
    }

    public void verifierFinDeTour(int numEssai, boolean aTrouve) {
        long tempsEcouleMs = (System.currentTimeMillis() - this.tempsDebut);
        long tempsEcouleSecondes = tempsEcouleMs / 1000;

        if (tempsEcouleSecondes > TEMPS_MAX_SECONDES || aTrouve || numEssai >= getMaxEssais()) {
            setPartieTerminee(true);
            getJoueur().ajouterTemps(tempsEcouleMs);
        }
    }
}
