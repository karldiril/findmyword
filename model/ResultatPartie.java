package model;

public class ResultatPartie {
    private String modeJeu;
    private String motSecret;
    private boolean trouve;
    private int scoreObtenu;
    private long tempsPasse;


    public ResultatPartie(String mode, String mot, boolean trouve, int score, long temps) {
        this.modeJeu = mode;
        this.motSecret = mot;
        this.trouve = trouve;
        this.scoreObtenu = score;
        this.tempsPasse = temps;
    }

    public String getModeJeu() {
        return modeJeu;
    }

    public String getMotSecret() {
        return motSecret;
    }

    public boolean estTrouve() {
        return trouve;
    }

    public int getScoreObtenu() {
        return scoreObtenu;
    }

    public long getTempsPasse() {
        return tempsPasse;
    }
}
