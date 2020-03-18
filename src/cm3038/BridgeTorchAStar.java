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
        double cost = 0.0D;
        BtState btState = (BtState) state;
        BtState btGoal = (BtState) this.goalState;

        for(int i = 0; i < btState.westPersonList.size(); i++){
            if(!btGoal.westPersonList.contains(btState.westPersonList.get(i))){
                cost+= btState.westPersonList.get(i).getCrossingTime();
            }
        }
        for(int i = 0; i < btState.eastPersonList.size(); i++){
            if(!btGoal.eastPersonList.contains(btState.eastPersonList.get(i))){
                cost+= btState.eastPersonList.get(i).getCrossingTime();
            }
        }
        return 0;
    }
}
