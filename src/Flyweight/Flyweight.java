package Flyweight;

import java.util.*;

class Sentence
{
    private String plainText;
    private List<WordToken> tokens = new ArrayList<>();
    private String [] words;
    public Sentence(String plainText)
    {
        this.plainText = plainText;
        this.words = plainText.split(" ");
    }

    public WordToken getWord(int index)
    {
        WordToken token = new WordToken(index);
        tokens.add(token);
        return token;

    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        String word;

        for (WordToken token : tokens) {
            if (token.capitalize) {
                String capitalizedWord = words[token.index];
                capitalizedWord = capitalizedWord.toUpperCase();
                words[token.index] = capitalizedWord;
            }
        }

        for (int i = 0; i < words.length; i++) {
            word = words[i];

            if (i==0) {
                sb.append(word);
            } else {
                sb.append(" ").append(word);
            }
        }
        return sb.toString();
    }

    class WordToken
    {
        public boolean capitalize;
        public int index;

        public WordToken(int index)
        {
            this.index = index;
        }

    }
}

class FlyweightDemo
{
    public static void main(String [] args) {
        Sentence sentence = new Sentence("hello world");
        sentence.getWord(1).capitalize = true;
        System.out.println(sentence);
    }
}