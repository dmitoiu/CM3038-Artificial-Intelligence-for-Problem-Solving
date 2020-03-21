package cm3038.test;

import cm3038.*;
import cm3038.search.Path;
import javafx.scene.control.RadioMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestAStarHeuristic {

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

        Path path=problem.search();
        if (path==null)
            System.out.println("No solution");
        else {
            path.print();
            System.out.println("Nodes Visited: "+problem.nodeVisited);
            System.out.println("Solution Cost: "+path.cost+"\n");

            System.out.println(problem.heuristic(initialState));
            if(problem.heuristic(initialState) > path.cost){
                System.out.println("Overestimate!");
            }

        }
    }
}
