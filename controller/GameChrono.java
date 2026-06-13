package controller;

import model.Joueur;
import repository.WordRepository;

public class GameChrono extends Game {
    private long tempsDebut;
    private final long TEMPS_MAX_MS = 30000L;

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


        if (tempsEcouleMs > TEMPS_MAX_MS) {
            tempsEcouleMs = TEMPS_MAX_MS;
        }

        if (tempsEcouleMs > TEMPS_MAX_MS || aTrouve || numEssai >= getMaxEssais()) {
            setPartieTerminee(true);
            getJoueur().ajouterTemps(tempsEcouleMs);
        }
    }

    public String getNomMode() {
        return "CHRONO";
    }

    public long getTempsRestant() {
        if (this.tempsDebut == 0) {
            return TEMPS_MAX_MS; 
        }
        
        long tempsEcouleMs = System.currentTimeMillis() - this.tempsDebut;
        long tempsRestantMs = TEMPS_MAX_MS - tempsEcouleMs;
        
        return Math.max(0, tempsRestantMs);
    }
}