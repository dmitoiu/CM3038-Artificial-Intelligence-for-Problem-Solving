package cm3038.test;

import cm3038.BridgeTorchAStar;
import cm3038.BtState;
import cm3038.Person;
import cm3038.TorchLocation;
import cm3038.search.SearchProblem;

import java.util.ArrayList;

public class TestSuccessor {

    public static void main(String[] args){
        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Person> emptyList = new ArrayList<Person>();

        personList.add(new Person("Adam", 1));
        personList.add(new Person("Ben", 2));
        personList.add(new Person("Claire", 5));
        personList.add(new Person("Doris", 8));

        BtState initialState = new BtState(personList, emptyList, TorchLocation.WEST);
        BtState goalState = new BtState(emptyList, personList, TorchLocation.EAST);

        SearchProblem problem = new BridgeTorchAStar(initialState, goalState);
        System.out.println("State:\n" + initialState.toString());
        System.out.println("All Actions States:\n" + initialState.successor().toString());
    }
}
