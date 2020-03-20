// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import cm3038.search.*;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args){

        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Person> emptyList = new ArrayList<Person>();

        personList.add(new Person("Adam", 1));
        personList.add(new Person("Ben", 2));
        personList.add(new Person("Claire", 5));
        personList.add(new Person("Doris", 8));

        personList.add(new Person("Edward", 9));
        personList.add(new Person("Fiona", 10));

        Collections.sort(personList);

        Bridge bridge = new Bridge(3);
        BtState initialState = new BtState(personList, emptyList, TorchLocation.WEST, bridge);
        BtState goalState = new BtState(emptyList, personList, TorchLocation.EAST, bridge);

        BridgeTorchAStar problem = new BridgeTorchAStar(initialState, goalState);
        System.out.println(problem.heuristic(initialState));

        System.out.println("Searching...");		//print some message
        Path path=problem.search();				//perform search, get result
        System.out.println("Done!");				//print some message
        if (path==null)							//if it is null, no solution
            System.out.println("No solution");
        else {
            path.print();							//otherwise print path
            System.out.println("Nodes visited: "+problem.nodeVisited);
            System.out.println("Cost: "+path.cost+"\n");
        }
    }
}
