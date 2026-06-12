package repository;

import model.Word;

public class FixedWordRepository {
    private final String motFixe;

    public FixedWordRepository(String motFixe) {
        this.motFixe = motFixe;
    }

    public Word getWord() {
        return new Word(this.motFixe);
    }
}
