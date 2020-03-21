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
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        westPersonList.add(new Person("Adam", 1));
        westPersonList.add(new Person("Ben", 2));
        westPersonList.add(new Person("Claire", 5));
        westPersonList.add(new Person("Doris", 8));

        eastPersonList.add(new Person("Edward", 9));
        eastPersonList.add(new Person("Fiona", 10));

        Collections.sort(westPersonList);
        Collections.sort(eastPersonList);

        Bridge bridge = new Bridge(3);
        BtState initialState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);
        BtState goalState = new BtState(eastPersonList, westPersonList, TorchLocation.EAST, bridge);

        BridgeTorchAStar problem = new BridgeTorchAStar(initialState, goalState);

        System.out.println("Init: " + initialState.toString());
        System.out.println("Goal: " + goalState.toString());
        System.out.println("");
        Path path=problem.search();
        if (path==null)
            System.out.println("No solution");
        else {
            path.print();
            System.out.println("Nodes visited: "+problem.nodeVisited);
            System.out.println("Solution Cost: "+path.cost+"\n");
        }
    }
}
