package cm3038.test;

import cm3038.*;
import cm3038.search.SearchProblem;

import java.util.ArrayList;

public class TestIsGoal {

    public static void main(String[] args){
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        westPersonList.add(new Person("Adam", 1));
        westPersonList.add(new Person("Ben", 2));
        westPersonList.add(new Person("Claire", 5));
        westPersonList.add(new Person("Doris", 8));

        Bridge bridge = new Bridge(2);
        BtState initialState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);
        BtState goalState = new BtState(eastPersonList, westPersonList, TorchLocation.EAST, bridge);

        SearchProblem problem = new BridgeTorchAStar(initialState, goalState);
        System.out.println(problem.isGoal(goalState));
    }
}
