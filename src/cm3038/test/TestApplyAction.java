// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (TestApplyAction.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038.test;

import cm3038.*;
import cm3038.search.SearchProblem;

import java.util.ArrayList;

public class TestApplyAction {

    public static void main(String[] args){
        // Create west ArrayList of person objects
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        // Create east ArrayList of person objects
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
        Bridge bridge = new Bridge(3);
        // Create before state
        BtState beforeState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);

        // Create ArrayList of person that will leave the source for destination
        ArrayList<Person> leaving = new ArrayList<>();
        // Loop over west ArrayList of person objects
        for(int i = 0; i < beforeState.westPersonList.size(); i++){
            // Create person object
            Person person = beforeState.westPersonList.get(i);
            // Add all the person objects to the leaving ArrayList of person objects that meat the condition below
            if(person.getCrossingTime() <= 5 && !leaving.contains(person)){
                // Add person
                leaving.add(person);
            }
        }

        // Create action from the leaving ArrayList of person objects
        BtAction btAction = new BtAction(leaving, TorchLocation.EAST);
        // Declare after state and apply action
        BtState afterState = beforeState.applyAction(btAction);
        System.out.println("Before State   -> " + beforeState.toString());
        System.out.println("Action Applied -> " + btAction.toString());
        System.out.println("After State    -> " + afterState.toString());
    }
}
