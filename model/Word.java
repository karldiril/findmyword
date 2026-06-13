package model;


public class Word {
    public static final int TAILLE_MOT = 5;
    public static final int VALIDE = 0;
    public static final int ERREUR_LONGUEUR = 1;
    public static final int ERREUR_CARACTERE = 2;
    public static final int ERREUR_DOUBLON = 3;

    private String texte;

    public Word(String texte) {
        this.texte = texte.toLowerCase();
    }

    public String getTexte() {
        return this.texte;
    }


    public int verifierValidite() {
        if (!aBonneLongueur()) {
            return ERREUR_LONGUEUR;
        }
        if (!contientUniquementLettres()) {
            return ERREUR_CARACTERE;
        }
        if (!aLettresUniques()) {
            return ERREUR_DOUBLON;
        }
        return VALIDE;
    }


    private boolean aBonneLongueur() {
        return this.texte.length() == TAILLE_MOT;
    }

    private boolean contientUniquementLettres() {
        for (int i = 0; i < this.texte.length(); i++) {
            char c = this.texte.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }


    private boolean aLettresUniques() {
        for (int i = 0; i < this.texte.length(); i++) {
            for (int j = i + 1; j < this.texte.length(); j++) {
                if (this.texte.charAt(i) == this.texte.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public String toString() {
        return this.texte;
    }
}

