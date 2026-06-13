package controller;

import model.Joueur;
import repository.WordRepository;


public class GamePoints extends Game {
    public GamePoints(WordRepository repo, Joueur joueur) {
        super(repo, joueur);
    }

    public void executerActionAvantSaisie() {
        // Cette classe ne fait pas d'actions avant saisie
    }

    public void verifierFinDeTour(int numEssai, boolean aTrouve) {
        if (aTrouve) {
            attribuerPoints(numEssai);
            setPartieTerminee(true);
        }
        else if (numEssai >= getMaxEssais()) {
            setPartieTerminee(true);
        }
    }


    private void attribuerPoints(int numEssai) {
        int pointsGagnes = (getMaxEssais() - numEssai + 1) * 10;
        getJoueur().ajouterPoints(pointsGagnes);
    }

    public String getNomMode() {
        return "POINT";
    }
}

