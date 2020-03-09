// -------------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem (cm3038.BridgeTorchDepthFirst.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// -------------------------------------------------------------------------------

package cm3038;

import cm3038.search.Node;
import cm3038.search.SearchProblem;
import cm3038.search.State;

import java.util.List;

public class BridgeTorchDepthFirst extends SearchProblem {

    public BtState goal;

    public BridgeTorchDepthFirst(BtState start, BtState goal) {
        super(start);
        this.goal = goal;
    }

    @Override
    protected void addChild(List<Node> fringe, Node childNode) {
        fringe.add(0,childNode);
    }

    @Override
    public boolean isGoal(State state) {
        return state.equals(this.goal);
    }
}
