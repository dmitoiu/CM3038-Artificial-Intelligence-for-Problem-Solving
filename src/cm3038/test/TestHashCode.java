// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (TestHashCode.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038.test;

import cm3038.Bridge;
import cm3038.BtState;
import cm3038.Person;
import cm3038.TorchLocation;

import java.util.ArrayList;

public class TestHashCode {

    public static void main(String[] args){
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        // Create person objects
        Person adam = new Person("Adam", 1);
        Person ben = new Person("Ben", 2);
        Person claire = new Person("Claire", 5);
        Person doris = new Person("Doris", 8);
        Person edward = new Person("Edward", 9);
        Person fiona = new Person("Fiona", 10);

        // Add person objects to the west ArrayList of person objects
        westPersonList.add(adam);
        westPersonList.add(ben);
        westPersonList.add(claire);
        westPersonList.add(doris);

        // Add peron objects to the east ArrayList of person objects
        eastPersonList.add(edward);
        eastPersonList.add(fiona);

        // Create bridge object
        Bridge bridge = new Bridge(2);

        // Create Initial state object
        BtState initialState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);
        // Create some state object
        BtState someState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);
        // Create goal state object
        BtState goalState = new BtState(eastPersonList, westPersonList, TorchLocation.EAST, bridge);

        System.out.println(initialState.toString() + " hashcode -> " + initialState.hashCode());
        System.out.println(someState.toString() + " hashcode -> " + someState.hashCode());
        System.out.println(goalState.toString() + " hashcode -> " + goalState.hashCode());
    }
}
