package com.markov;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;

import java.util.*;

class Bigram {
    private final String s1;
    private final String s2;

    private Bigram(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getS1() {
        return this.s1;
    }

    public String getS2() {
        return this.s2;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Bigram)) return false;
        Bigram b = (Bigram) o;
        return this.s1.equalsIgnoreCase(b.s1) && this.s2.equalsIgnoreCase(b.s2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.s1, this.s2);
    }

    @Override
    public String toString() {
        return "["+s1+","+s2+"]";
    }

    private static List<Bigram> convertTokensToBigrams(List<String> l) {
        List<Bigram> result = new LinkedList<>();
        for(int i = -1, j = 0; i < l.size(); ++i, ++j) {
            if(i == -1) {
                result.add(new Bigram("<s>", l.get(j)));
            } else if (j == l.size()) {
                result.add(new Bigram(l.get(i),"</s>"));
            } else {
                result.add(new Bigram(l.get(i), l.get(j)));
            }
        }
        return result;
    }

    private static void countBigramFrequency(List<Bigram> list, HashMap<Bigram, Integer> map) {
        for(Bigram b : list) {
            if(!map.containsKey(b)) {
                map.put(b, 1);
            } else {
                map.put(b, map.get(b)+1);
            }
        }
    }

    public static List<Bigram> convertTokensToBigrams(DocumentPreprocessor tokenizedDoc) {
        List<Bigram> bigrams = new ArrayList<>();
        Iterator<List<HasWord>> iter = tokenizedDoc.iterator();
        while(iter.hasNext()) {
            List<HasWord> sentenceOfHasWord = iter.next();
            List<String> sentenceOfString = new ArrayList<String>();
            sentenceOfHasWord.forEach(e -> sentenceOfString.add(e.word()));
            bigrams.addAll(Bigram.convertTokensToBigrams(sentenceOfString));
        }
        return bigrams;
    }

    public static HashMap<Bigram, Integer> bigramFrequencyMap(List<Bigram> list) {
        HashMap<Bigram, Integer> freq = new HashMap<>();
        countBigramFrequency(list, freq);
        return freq;
    }

}
