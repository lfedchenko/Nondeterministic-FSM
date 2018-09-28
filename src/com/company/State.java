package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class State {
    private int number;
    private boolean First;
    private boolean Final;
    private Map<Character, HashSet<State>> transitions;

    public State(int number) {
        this.number = number;
        this.First = false;
        this.Final = false;
        this.transitions = new HashMap<Character, HashSet<State>>();
    }

    public void AddIsFirst() {
        this.First = true;
    }

    public boolean isFirst() {
        return this.First;
    }

    public void AddIsFinal() {
        this.Final = true;
    }

    public boolean isFinal() {
        return this.Final;
    }

    public void AddTrans(Character a, State state) {
        if (transitions.keySet().contains(a)) {
            transitions.get(a).add(state);
        } else {
            HashSet<State> set = new HashSet<>();
            set.add(state);
            transitions.put(a, set);
        }
    }

    public Map<Character, HashSet<State>> getTrans() {
        return this.transitions;
    }

}
