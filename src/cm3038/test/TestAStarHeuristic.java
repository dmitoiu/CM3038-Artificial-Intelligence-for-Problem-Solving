package cm3038.test;

import cm3038.*;
import cm3038.search.Path;
import javafx.scene.control.RadioMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestAStarHeuristic {

    public static void main(String[] args){
        ArrayList<Person> initPersonList = new ArrayList<Person>();
        ArrayList<Person> initEmptyList = new ArrayList<Person>();

        initPersonList.add(new Person("Adam", 1));
        initPersonList.add(new Person("Ben", 2));
        initPersonList.add(new Person("Claire", 5));
        initPersonList.add(new Person("Doris", 8));

        initEmptyList.add(new Person("Edward", 9));
        initEmptyList.add(new Person("Fiona", 10));


        Collections.sort(initPersonList);

        Bridge bridge = new Bridge(2);
        BtState initialState = new BtState(initPersonList, initEmptyList, TorchLocation.WEST, bridge);
        BtState goalState = new BtState(initEmptyList, initPersonList, TorchLocation.EAST, bridge);

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

            System.out.println(problem.heuristic(initialState));
            if(problem.heuristic(initialState) > path.cost){
                System.out.println("Overestimate!");
            }

        }
    }
}
