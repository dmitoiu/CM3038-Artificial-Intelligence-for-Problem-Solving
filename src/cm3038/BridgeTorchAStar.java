// -------------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (BridgeTorchAStar.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// -------------------------------------------------------------------------------

package cm3038;

import cm3038.search.Node;
import cm3038.search.State;
import cm3038.search.informed.BestFirstSearchProblem;
import java.util.ArrayList;
import java.util.Collections;

public class BridgeTorchAStar extends BestFirstSearchProblem {

    public BridgeTorchAStar(State start, State goal) {
        super(start, goal);
    }

    /**
     * This method will allow the verification of the goal from a given bridge torch state object
     * @param state BridgeTorch state object
     * @return Equality boolean
     */
    @Override
    public boolean isGoal(State state) {
        return state.equals(this.goalState);
    }

    /**
     * This method will allow the evaluation of a specific node in the tree
     * @param node Node object
     * @return f(n)=g(n)+h(n)
     */
    @Override
    public double evaluation(Node node)
    {
        // f(n)=g(n)+h(n)
        return node.getCost()+this.heuristic(node.state);
    }

    /**
     * This method will allow the estimation of a specific BridgeTorchState to reach the goal state
     * @param state State object
     * @return Cost to reach the goal
     */
    public double heuristic(State state) {
        // Declare cost and assign value
        double cost = 0.0D;
        // Create current BridgeTorchState from the state passed as an argument
        BtState btState = (BtState) state;
        // Create BridgeTorchState goal
        BtState btGoal = (BtState) this.goalState;

        // Loop over current BridgeTorchState west ArrayList of person objects
        for(int i = 0; i < btState.westPersonList.size(); i++){
            // If the person from the current BridgeTorchState west ArrayList is not at the destination
            if(!btGoal.westPersonList.contains(btState.westPersonList.get(i))){
                // Increase cost by 1
                cost++;
            }
        }
        // Loop over current BridgeTorchState east ArrayList of person objects
        for(int i = 0; i < btState.eastPersonList.size(); i++){
            // If the person from the current BridgeTorchState east ArrayList is not at the destination
            if(!btGoal.eastPersonList.contains(btState.eastPersonList.get(i))){
                // Increase cost by 1
                cost++;
            }
        }
        // If the current BridgeTorchState is equal to the goal return 0, if not return the cost
        return this.isGoal(btState) ? 0 : cost;
    }
}
