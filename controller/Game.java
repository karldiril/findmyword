package controller;

import java.util.ArrayList;
import model.Joueur;
import model.Word;
import repository.WordRepository;


public abstract class Game {
    private Word motSecret;
    private WordRepository repository;
    private ArrayList<Word> tentatives = new ArrayList<>();
    private ArrayList<String[]> analyses = new ArrayList<>();
    private int maxEssais = 6;
    private Joueur joueur;
    private boolean partieTerminee = false;


    public Game(WordRepository repo, Joueur joueur) {
        this.repository = repo;
        this.joueur = joueur;
        this.motSecret = this.repository.getWord();
    }


    public Word getMotSecret() {
        return this.motSecret;
    }

    public ArrayList<Word> getTentatives() {
        return this.tentatives;
    }

    public ArrayList<String[]> getAnalyses() {
        return this.analyses;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public int getMaxEssais() {
        return this.maxEssais;
    }

    public boolean isPartieTerminee() {
        return this.partieTerminee;
    }

    public void setPartieTerminee(boolean etat) {
        this.partieTerminee = etat;
    }

    public String[] analyserTentatives(Word proposition) {
        String[] resultat = new String[Word.TAILLE_MOT];
        String propositionTexte = proposition.getTexte();
        String motSecretTexte = this.motSecret.getTexte();

        for (int i = 0; i < propositionTexte.length(); i++) {
            if (estLettreBienPlacee(i, proposition))
                resultat[i] = "OK";
            else if (compterOccurence(propositionTexte.charAt(i), motSecretTexte) > 0)
                resultat[i] = "PRESENT";
            else
                resultat[i] = "ABSENT";
        }
        this.tentatives.add(proposition);
        this.analyses.add(resultat);
        return resultat;
    }

    private boolean estLettreBienPlacee(int index, Word proposition) {
        return proposition.getTexte().charAt(index) == this.motSecret.getTexte().charAt(index);
    }

    private int compterOccurence(char lettre, String texte) {
        int compteur = 0;
        for (int i = 0; i < texte.length(); i++) {
            if (texte.charAt(i) == lettre) {
                compteur++;
            }
        }
        return compteur;
    }

    public abstract void executerActionAvantSaisie();
    public abstract void verifierFinDeTour(int numEssai, boolean aTrouve);
}