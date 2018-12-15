package com.markov;

import edu.stanford.nlp.process.DocumentPreprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Driver {

    public Driver(String[] args) {
        main(args);
    }

    public static void main(String[] args) {
        String document="";
        int numSentencesToGenerate = 0;

        try {
            boolean validFormat;
            do {
                System.out.println("Enter text file name in directory (include .txt): ");
                Scanner sc = new Scanner(System.in);
                document = sc.next();
                validFormat = document.matches(".*\\.(txt){1}$");
            } while(!validFormat);

            do {
                System.out.println("Enter number of sentences to generate: ");
                Scanner sc = new Scanner(System.in);
                numSentencesToGenerate = sc.nextInt();
                System.out.println(numSentencesToGenerate);
            } while(numSentencesToGenerate < 1);
        } catch(Exception e) {
            e.printStackTrace();
        }

        DocumentPreprocessor tokenizedDoc = new DocumentPreprocessor(System.getProperty("user.dir")+"\\"+document);
        List<Bigram> bigrams = Bigram.convertTokensToBigrams(tokenizedDoc);
        HashMap<Bigram, Integer> bigramFreqMap = Bigram.bigramFrequencyMap(bigrams);
        TransitionGraph trans = new TransitionGraph(bigramFreqMap);
        List<String> result = trans.generateSentences(numSentencesToGenerate);
        System.out.println(result);
    }
}
