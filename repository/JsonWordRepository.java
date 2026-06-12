package repository;

import words.WordSet;
import words.JsonWordSet;

import model.Word;


public class JsonWordRepository extends WordRepository {
    private WordSet wordSet;

    public JsonWordRepository(String path) {
        try {
            this.wordSet = new JsonWordSet(path);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
        
    }

    public Word getWord() {
        String mot = this.wordSet.random();
        return new Word(mot);
    }
}