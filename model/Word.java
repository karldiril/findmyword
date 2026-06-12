public class Word {
    public static final int TAILLE_MOT = 5;

    private String texte;

    public Word(String texte) {
        this.texte = texte;
    }

    public String getTexte() {
        return this.texte;
    }

    public boolean valide() {
        if (this.texte.length() != TAILLE_MOT) {
            return false;
        }

        for (int i = 0; i < this.texte.length(); i++) {
            char c = this.texte.charAt(i);

            if (!Character.isLetter(c)) {
                return false;
            }
        }

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

