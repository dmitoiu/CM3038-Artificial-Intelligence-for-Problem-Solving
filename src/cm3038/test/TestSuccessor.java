package cm3038.test;

import cm3038.*;
import cm3038.search.SearchProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TestSuccessor {

    public static void main(String[] args){
        int list[] = {1, 2, 3, 4, 5};

        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Person> emptyList = new ArrayList<Person>();

        personList.add(new Person("Adam", 1));
        personList.add(new Person("Ben", 2));
        personList.add(new Person("Claire", 5));
        personList.add(new Person("Doris", 8));

        int r = 1;
        int n = personList.size();

        ArrayList<Person[]> personCombinations = new ArrayList<>();

        getCombinations(personList, n, r, personCombinations);

        for(int i = 0; i < personCombinations.size(); i++){
            System.out.println(Arrays.toString(personCombinations.get(i)));
        }

        Bridge bridge = new Bridge(2);
        BtState initialState = new BtState(personList, emptyList, TorchLocation.WEST, bridge);
        BtState goalState = new BtState(emptyList, personList, TorchLocation.EAST, bridge);

        SearchProblem problem = new BridgeTorchAStar(initialState, goalState);
        System.out.println("State:\n" + initialState.toString());
        System.out.println("All Actions States:\n" + initialState.successor().toString());
        System.out.println(initialState.successor().size());
    }

    public static void combinationsUtil(ArrayList<Person> personList, Person[] combination,
                                        int start, int end, int index,
                                        int r, ArrayList<Person[]> leavingList){
        if(index == r){
            Person[] data = combination.clone();
            leavingList.add(data);
            for(int i = 0; i < r; i++){
                System.out.println(combination[i]);
            }
            System.out.println("");
            return;
        }
        for(int j = start; j <= end && end - j + 1 >= r - index; j++){
            combination[index] = personList.get(j);
            combinationsUtil(personList, combination, j+1, end, index+1, r, leavingList);
        }
    }

    public static void getCombinations(ArrayList<Person> personList, int n, int r, ArrayList<Person[]> leavingList){
        Person[] combination = new Person[r];
        combinationsUtil(personList, combination, 0, n-1, 0, r, leavingList);
    }
}
