// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (BtState.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import cm3038.search.ActionStatePair;
import cm3038.search.State;
import java.util.*;

/**
 * This class will allow the creation of the bridge torch state,
 * This state will include the west person array list,
 * the east person array list and the torch location
 */
public class BtState implements State {

    // Declare west and east array lists
    public ArrayList<Person> westPersonList, eastPersonList;
    // Declare torch location
    public TorchLocation torchLocation;
    // Declare bridge object
    public Bridge bridge;

    public BtState(ArrayList<Person> westPersonList, ArrayList<Person> eastPersonList,
                   TorchLocation torchLocation, Bridge bridge){
        this.westPersonList = westPersonList;
        this.eastPersonList = eastPersonList;
        this.torchLocation = torchLocation;
        this.bridge = bridge;
    }

    /**
     * This method will allow the access to the west person array list
     * @return ArrayList of Person objects
     */
    public ArrayList<Person> getWestPersonList() {
        return this.westPersonList;
    }

    /**
     * This method will allow the modification of the west person array list attribute
     * @param westPersonList
     */
    public void setWestPersonList(ArrayList<Person> westPersonList) {
        this.westPersonList = westPersonList;
    }

    /**
     * This method will allow the access to the east person array list attribute
     * @return
     */
    public ArrayList<Person> getEastPersonList() {
        return this.eastPersonList;
    }

    /**
     * This method will allow the modification of the east person array list attribute
     * @param eastPersonList
     */
    public void setEastPersonList(ArrayList<Person> eastPersonList) {
        this.eastPersonList = eastPersonList;
    }

    /**
     * This method will allow the access to the torch location enumeration attribute
     * @return torch enumeration
     */
    public TorchLocation getTorchLocation() {
        return this.torchLocation;
    }

    /**
     * This method will allow the modification of the torch location enumeration attribute
     * @param torchLocation
     */
    public void setTorchLocation(TorchLocation torchLocation) {
        this.torchLocation = torchLocation;
    }

    /**
     * This method will generate a hash value specific to a bridge torch state,
     * This value will be used in association with the equals method.
     * @return hash integer
     */
    @Override
    public int hashCode() {
        // Create hash variable
        int hash = 0;
        // Create torch location holder
        boolean torchWest = true;
        if(this.getTorchLocation() == TorchLocation.WEST){
            torchWest = true;
            // Loop over west person ArrayList and add hash of each person
            for(Person person : this.getWestPersonList()){
                hash = hash + (int) person.hashCode();
            }
        } else {
            torchWest = false;
            // Loop over east person ArrayList and add hash of each person
            for(Person person : this.getEastPersonList()){
                hash = hash + (int) person.hashCode();
            }
        }
        // If torch location is in west hash is 1 if not than 0
        hash = hash + (torchWest ? 1 : 0);
        return hash;
    }

    /**
     * This method will allow the representation of the state in the console
     * @return BridgeTorch representation string
     */
    public String toString(){
        // Create string container
        String result = "";
        if(this.getTorchLocation() == TorchLocation.WEST){
            // If torch location is west than add torch at the start of west array list
            result+= "Torch ";
        }
        // Loop over the west person array list
        for(int i = 0; i < westPersonList.size(); i++){
            // Create person object from west array list
            Person person = westPersonList.get(i);
            // Add the object attributes to the string container
            result+= person.getName() + "(" + person.getCrossingTime() + ")" + " ";
        }
        // Add bridge string in between west and east array list specifying the bridge capacity
        result+= " |=================="+"("+String.valueOf(this.bridge.getCapacity())+")"+"==================| ";
        if(this.getTorchLocation() == TorchLocation.EAST){
            // If torch location is east than add torch at the start of east array list
            result+= "Torch ";
        }
        // Loop over east person array list
        for(int i = 0; i < eastPersonList.size(); i++){
            // Create person object from east array list
            Person person = eastPersonList.get(i);
            // Add the object attributes to the string container
            result+= person.getName() + "(" + person.getCrossingTime() + ")" + " ";
        }
        return result;
    }

    /**
     * This method will allow the verification of the equality of 2 states
     * @param state the state to be checked
     * @return boolean
     */
    @Override
    public boolean equals(Object state) {
        if(!(state instanceof BtState)){
            // If object argument is not a state return false
            return false;
        }

        // Create state object
        BtState btState = (BtState) state;

        // Sort current object
        Collections.sort(westPersonList);
        Collections.sort(eastPersonList);

        // Sort state argument
        Collections.sort(btState.westPersonList);
        Collections.sort(btState.eastPersonList);

        // Check states equality
        boolean status = (westPersonList.equals(btState.westPersonList) &&
                          eastPersonList.equals(btState.eastPersonList) &&
                          this.getTorchLocation() == btState.getTorchLocation() &&
                          this.bridge.equals(btState.bridge));
        return status;
    }

    /**
     * This method will allow the creation of all combinations possible from a specific state,
     * The combinations generated will not include duplicates.
     * @return ArrayList of ActionStatePair objects
     */
    @Override
    public List<ActionStatePair> successor() {
        // Create action state pair object array list
        List<ActionStatePair> result=new ArrayList<ActionStatePair>();

        // Create source array list
        ArrayList<Person> source = new ArrayList<Person>();
        if(this.getTorchLocation() == TorchLocation.WEST){
            // If torch location is west assign source value to the west person array list
            source = westPersonList;
        } else {
            // If torch location is east assign source value to the east person array list
            source = eastPersonList;
        }

        // Assigning combinations formula values => C(n, r) = n! / r!(n - r)!
        // Assign the bridge capacity to r
        int r = this.bridge.getCapacity();
        // Assign the array list size to n
        int n = source.size();

        while(r != 0){
            // Create combinations array containing the combinations of persons
            ArrayList<Person[]> personCombinations = new ArrayList<>();
            // Add the combination to the array list (personCombinations)
            getCombinations(source, n, r, personCombinations);
            // Loop over combinations array list
            for(int combination = 0; combination < personCombinations.size(); combination++){
                // Create leaving persons array list, the persons that will leave the source for the destination
                ArrayList<Person> leavingAction = new ArrayList<>();
                // Loop over the person(s) in the combination
                for(int person = 0; person < personCombinations.get(combination).length; person++){
                    // Create person object from combination
                    Person combinationPerson = personCombinations.get(combination)[person];
                    // Add person to the leaving array list
                    leavingAction.add(combinationPerson);
                }
                // Create action from leaving array list, the persons that will leave the source for destination
                BtAction action = new BtAction(leavingAction, this.oppositeLocation(this.getTorchLocation()));
                // Create next state from the action
                BtState nextState = this.applyAction(action);
                // Set the cost of the action to the highest crossing time of the person in the combination
                action.setCost(Collections.max(leavingAction).getCrossingTime());
                // Create action state pair object and add it to the array list
                result.add(new ActionStatePair(action, nextState));
            }
            // Decrease r by 1
            r--;
        }
        return result;
    }

    /**
     * This method will allow the creation of all possible combinations
     * from a given array list of person objects without duplicates.
     * @param personList ArrayList of person objects
     * @param combination Array of Person of objects
     * @param start start of the ArrayList
     * @param end end of the ArrayList
     * @param index Position in the ArrayList
     * @param r combination size
     * @param leavingList ArrayList of all combinations
     */
    public static void combinationsUtil(ArrayList<Person> personList, Person[] combination,
                                        int start, int end, int index, int r,
                                        ArrayList<Person[]> leavingList){
        if(index == r){
            // If index equals r, clone combination
            Person[] data = combination.clone();
            // Add combination to the leaving array list
            leavingList.add(data);
            return;
        }
        // Loop over combinations
        for(int j = start; j <= end && end - j + 1 >= r - index; j++){
            // Assign combination array person objects
            combination[index] = personList.get(j);
            // Call method again with the next combination arguments
            combinationsUtil(personList, combination, j+1, end, index+1, r, leavingList);
        }
    }

    /**
     * This method will allow the creation of all possible combinations
     * from an ArrayList of person objects using the utility method
     * @param personList ArrayList of person objects
     * @param n size of the ArrayList
     * @param r size of the combination
     * @param leavingList ArrayList of all combinations
     */
    public static void getCombinations(ArrayList<Person> personList, int n, int r, ArrayList<Person[]> leavingList){
        // Create temporary array of person objects with a size of r
        Person[] combination = new Person[r];
        // Generate combinations
        combinationsUtil(personList, combination, 0, n-1, 0, r, leavingList);
    }

    /**
     * This method will allow an action to be applied on a state
     * @param action The action object to be applied
     * @return BridgeTorchState after the action has been applied
     */
    @SuppressWarnings("unchecked")
    public BtState applyAction(BtAction action)
    {
        // Create west person array list from the current west person array list
        ArrayList<Person> west = (ArrayList<Person>) westPersonList.clone();
        // Create east person array list from the current east person array list
        ArrayList<Person> east = (ArrayList<Person>) eastPersonList.clone();

        // Create source array list
        ArrayList<Person> source = new ArrayList<>();
        // Create destination array list
        ArrayList<Person> destination = new ArrayList<>();
        // If torch location is west, continue...
        if(this.getTorchLocation() == TorchLocation.WEST){
            // Assign the west person list to the source array list
            source = west;
            // Assign the east person list to the destination array list
            destination = east;
        }
        // If torch location is east, continue...
        if(this.getTorchLocation() == TorchLocation.EAST){
            // Assign the east person list to the source array list
            source = east;
            // Assign the west person list to the destination array list
            destination = west;
        }
        // Remove the "leaving" action persons from the source array list
        source.removeAll(action.personList);
        // Add the "leaving" action persons to the destination array list
        destination.addAll(action.personList);

        // Create next state object from the local west and east persons lists
        BtState nextState = new BtState(west, east, this.oppositeLocation(this.getTorchLocation()), this.bridge);
        return nextState;
    }

    /**
     * This method will inverse the current location of the torch
     * @param current A torch enumeration
     * @return flipped torch location
     */
    private TorchLocation oppositeLocation(TorchLocation current)
    {
        if (current == TorchLocation.WEST)
        {
            // If the current torch equals west, return torch location east
            return TorchLocation.EAST;
        }
        // If the current torch location equals east, return torch location west
        return TorchLocation.WEST;
    }
}
