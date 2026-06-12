package model;


public class Joueur {
    private String nom;
    private int scoreGlobal;
    private long tempsCumule;

    public Joueur(String nom) {
        this.nom = nom;
        this.scoreGlobal = 0;
        this.tempsCumule = 0;
    }

    public String getNom() {
        return this.nom;
    }

    public int getScoreGlobal() {
        return this.scoreGlobal;
    }

    public long getTempsCumule() {
        return this.tempsCumule;
    }

    public void ajouterPoints(int points) {
        if (points > 0) {
            this.scoreGlobal += points;
        }
    }

    public void ajouterTemps(long ms) {
        if (ms > 0) {
            this.tempsCumule += ms;
        }
    }
}