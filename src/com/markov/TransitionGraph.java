package com.markov;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class TransitionGraph {
    private final HashMap<Bigram, Integer> bigramFreqMap;
    private HashMap<String, TransitionStates> transitionGraph;
    public TransitionGraph(HashMap<Bigram, Integer> bigramFreqMap) {
        this.bigramFreqMap = bigramFreqMap;
        createTransitionGraph();
    }

    private void createTransitionGraph() {
        this.transitionGraph = new HashMap<>();
        Set<Bigram> keys = bigramFreqMap.keySet();
        for(Bigram b : keys) {
            double weight = (double) bigramFreqMap.get(b);
            String wordAtCurrState = b.getS1();
            String wordAtFutureState = b.getS2();
            if(!transitionGraph.containsKey(wordAtCurrState)) {
                TransitionStates entry = new TransitionStates();
                entry.addFutureState(weight, wordAtFutureState);
                transitionGraph.put(wordAtCurrState, entry);
            } else {
                transitionGraph.get(wordAtCurrState).addFutureState(weight, wordAtFutureState);
            }
        }
    }

    public List<String> generateSentences(int n) {
        List<String> result = new LinkedList<>();
        for(int i = 0; i < n; ++i) {
            TransitionStates currState = transitionGraph.get("<s>");
            String s;
            StringBuilder sb = new StringBuilder();
            while(true) {
                s = currState.nextState();
                if(s.equals("</s>")) break;
                sb.append(s);
                sb.append(" ");
                currState = transitionGraph.get(s);
            }
            result.add(sb.toString());
        }
        return result;
    }

    @Override
    public String toString() {
        return transitionGraph.entrySet().toString();
    }
}
