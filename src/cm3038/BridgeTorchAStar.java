// -------------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem (cm3038.BridgeTorchAStarManhattan.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// -------------------------------------------------------------------------------

package cm3038;

import cm3038.search.Node;
import cm3038.search.State;
import cm3038.search.informed.BestFirstSearchProblem;

import java.util.Collections;

public class BridgeTorchAStar extends BestFirstSearchProblem {

    public static final int BRIDGE_SIZE = 2;
    public static int TORCH_DURATION = 15;

    public BridgeTorchAStar(State start, State goal) {
        super(start, goal);
    }

    @Override
    public boolean isGoal(State state) {
        return state.equals(this.goalState);
    }

    @Override
    public double evaluation(Node node)
    {
        return node.getCost()+this.heuristic(node.state);	//f(n)=g(n)+h(n)
    } //end method

    public double heuristic(State state) {
        BtState btState = (BtState) state;
        if(btState.getTorchLocation() == TorchLocation.WEST){
            return btState.isGoal() ? 0 : Collections.max(btState.westPersonList).getCrossingTime();
        } else {
            return btState.isGoal() ? 0 : Collections.min(btState.eastPersonList).getCrossingTime();
        }
    }
}
