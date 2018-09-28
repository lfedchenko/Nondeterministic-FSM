package com.company;

import java.util.HashSet;
import java.util.List;

public class FSM {
    private String alphabet = "";
    private State[] states;
    
    public FSM (int stateLength, int numberofFirst, int[] finals, List<String> trans) {
        states = new State[stateLength];
        for (int i = 0; i < states.length; i++) {
            states[i] = new State(i);
        }
        //заполнение первых и финальных состояний
        states[numberofFirst].AddIsFirst();
        for (int i: finals) {
            states[i].AddIsFinal();
        }
        //первое число - откуда, второй символ, третье число - куда
        for (String t:trans) {
            String[] parts = t.split("\\s");
            int q1 = Integer.parseInt(parts[0]);
            int q2 = Integer.parseInt(parts[2]);
            char a = parts[1].charAt(0);
            //алфавит
            if (!alphabet.contains(Character.toString(a))) alphabet += a;
            states[q1].AddTrans(a, states[q2]);
        }
    }

    public State whoIsFirst() {
        for (State s: states) {
            if (s.isFirst()) {
                return s;
            }
        }
        return null;
    }

    public boolean containsFinal(HashSet<State> dest) {
        for (State s:dest) {
            if(s.isFinal()) return true;
        }
        return false;
    }

    public boolean acceptable(String word) {
        boolean res = false;
        //список всех возможных состояний, в которые мы можем попасть в итоге
        HashSet<State> destination = new HashSet<>();
        //на первом шаге в списке только начальное состояние
        destination.add(whoIsFirst());
        HashSet<State> destination1 = new HashSet<>();
        //в цикле нельзя поменять значение destination, поэтому используем новую переменную
        for(int i = 0; i < word.length(); i++) {
            for (State s: destination) {
                //вдруг, из состояния никуда нельзя перейти, addAll вылетает на null
                try {
                    destination1.addAll(s.getTrans().get(word.charAt(i)));
                }
                catch(Exception e) {}
            }
            destination = new HashSet<>(destination1);
            //обязательно очистить временную переменную, что бы там не скопилось куча всего
            destination1.clear();
        }

        if (containsFinal(destination)) {
            res = true;
        }
        return res;
    }
}
