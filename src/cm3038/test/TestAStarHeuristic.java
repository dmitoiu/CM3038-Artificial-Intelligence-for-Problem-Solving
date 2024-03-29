// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (TestAStarHeuristic.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038.test;

import cm3038.*;
import cm3038.search.Path;
import java.util.ArrayList;
import java.util.Collections;

public class TestAStarHeuristic {

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

        // Sort west and east ArrayList of person objects
        Collections.sort(westPersonList);
        Collections.sort(eastPersonList);

        // Create bridge object
        Bridge bridge = new Bridge(3);

        // Create initial state object
        BtState initialState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);

        // Create goal state object
        BtState goalState = new BtState(eastPersonList, westPersonList, TorchLocation.EAST, bridge);

        // Create problem object
        BridgeTorchAStar problem = new BridgeTorchAStar(initialState, goalState);

        // Start search
        Path path=problem.search();
        if (path==null)
            System.out.println("No solution");
        else {
            path.print();
            System.out.println("Nodes Visited: "+problem.nodeVisited);
            System.out.println("Solution Cost: "+path.cost+"\n");

            System.out.println("Cost estimation to reach the goal " +
                               "from initial state: " +
                               problem.heuristic(initialState));

            if(problem.heuristic(initialState) > path.cost){
                // If the estimate is higher than the actual cost to the goal, the heuristic overestimates...
                System.out.println("Overestimate!");
            }

        }
    }
}
