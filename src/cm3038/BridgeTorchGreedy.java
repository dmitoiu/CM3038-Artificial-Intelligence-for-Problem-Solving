// -------------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem (cm3038.BridgeTorchGreedy.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// -------------------------------------------------------------------------------

package cm3038;

import cm3038.search.Node;
import cm3038.search.State;

public class BridgeTorchGreedy extends BridgeTorchAStar {

    public BridgeTorchGreedy(State start, State goal) {
        super(start, goal);
    }

    @Override
    public double evaluation(Node node) {
        return this.heuristic(node.state);
    }
}
