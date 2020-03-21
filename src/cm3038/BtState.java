// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem (cm3038.BtState.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import cm3038.search.ActionStatePair;
import cm3038.search.State;

import java.util.*;

public class BtState implements State {

    public ArrayList<Person> westPersonList, eastPersonList;
    public TorchLocation torchLocation;
    Bridge bridge;

    public BtState(ArrayList<Person> westPersonList, ArrayList<Person> eastPersonList, TorchLocation torchLocation, Bridge bridge){
        this.westPersonList = westPersonList;
        this.eastPersonList = eastPersonList;
        this.torchLocation = torchLocation;
        this.bridge = bridge;
    }

    public ArrayList<Person> getWestPersonList() {
        return westPersonList;
    }

    public void setWestPersonList(ArrayList<Person> westPersonList) {
        this.westPersonList = westPersonList;
    }

    public ArrayList<Person> getEastPersonList() {
        return eastPersonList;
    }

    public void setEastPersonList(ArrayList<Person> eastPersonList) {
        this.eastPersonList = eastPersonList;
    }

    public TorchLocation getTorchLocation() {
        return torchLocation;
    }

    public void setTorchLocation(TorchLocation torchLocation) {
        this.torchLocation = torchLocation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        boolean torchWest = true;
        if(this.getTorchLocation() == TorchLocation.WEST){
            torchWest = true;
        } else {
            torchWest = false;
        }
        hash = hash + (torchWest ? 1 : 0);
        return hash;
    }

    public String toString(){
        String result = "";
        if(this.getTorchLocation() == TorchLocation.WEST){
            result+= "Torch ";
        }
        for(int i = 0; i < westPersonList.size(); i++){
            Person person = westPersonList.get(i);
            result+= person.getName() + "(" + person.getCrossingTime() + ")" + " ";
        }
        result+= " |=================="+"("+String.valueOf(this.bridge.getCapacity())+")"+"==================| ";
        if(this.getTorchLocation() == TorchLocation.EAST){
            result+= "Torch ";
        }
        for(int i = 0; i < eastPersonList.size(); i++){
            Person person = eastPersonList.get(i);
            result+= person.getName() + "(" + person.getCrossingTime() + ")" + " ";
        }
        return result;
    }

    @Override
    public boolean equals(Object state) {
        if(!(state instanceof BtState)){
            return false;
        }
        BtState btState = (BtState) state;
        Collections.sort(westPersonList);
        Collections.sort(eastPersonList);

        Collections.sort(btState.westPersonList);
        Collections.sort(btState.eastPersonList);

        boolean status = (westPersonList.equals(btState.westPersonList) &&
                          eastPersonList.equals(btState.eastPersonList) &&
                          this.getTorchLocation() == btState.getTorchLocation());
        return status;
    }

    @Override
    public List<ActionStatePair> successor() {
        List<ActionStatePair> result=new ArrayList<ActionStatePair>();

        ArrayList<Person> source = new ArrayList<Person>();
        if(this.getTorchLocation() == TorchLocation.WEST){
            source = westPersonList;
        } else {
            source = eastPersonList;
        }

        int r = this.bridge.getCapacity();
        int n = source.size();

        while(r != 0){
            ArrayList<Person[]> personCombinations = new ArrayList<>();
            getCombinations(source, n, r, personCombinations);
            for(int combination = 0; combination < personCombinations.size(); combination++){
                ArrayList<Person> leavingAction = new ArrayList<>();
                for(int person = 0; person < personCombinations.get(combination).length; person++){
                    Person combinationPerson = personCombinations.get(combination)[person];
                    leavingAction.add(combinationPerson);
                }
                BtAction action = new BtAction(leavingAction, this.oppositeLocation(this.getTorchLocation()));
                BtState nextState = this.applyAction(action);
                action.setCost(Collections.max(leavingAction).getCrossingTime());
                result.add(new ActionStatePair(action, nextState));
            }
            r--;
        }

        return result;
    }

    public static void combinationsUtil(ArrayList<Person> personList, Person[] combination,
                                        int start, int end, int index, int r,
                                        ArrayList<Person[]> leavingList){
        if(index == r){
            Person[] data = combination.clone();
            leavingList.add(data);
            return;
        }
        for(int j = start; j <= end && end - j + 1 >= r - index; j++){
            combination[index] = personList.get(j);
            combinationsUtil(personList, combination, j+1, end, index+1, r, leavingList);
        }
    }

    public static void getCombinations(ArrayList<Person> personList, int n, int r, ArrayList<Person[]> leavingList){
        Person[] combination = new Person[r];
        combinationsUtil(personList, combination, 0, n-1, 0, r, leavingList);
    }

    @SuppressWarnings("unchecked")
    public BtState applyAction(BtAction action)
    {
        ArrayList<Person> west = (ArrayList<Person>) westPersonList.clone();
        ArrayList<Person> east = (ArrayList<Person>) eastPersonList.clone();

        ArrayList<Person> source = new ArrayList<>();
        ArrayList<Person> destination = new ArrayList<>();
        if(this.getTorchLocation() == TorchLocation.WEST){
            source = west;
            destination = east;
        }
        if(this.getTorchLocation() == TorchLocation.EAST){
            source = east;
            destination = west;
        }
        source.removeAll(action.personList);
        destination.addAll(action.personList);

        BtState nextState = new BtState(west, east, this.oppositeLocation(this.getTorchLocation()), this.bridge);
        return nextState;
    } //end method

    private TorchLocation oppositeLocation(TorchLocation current)
    {
        if (current== TorchLocation.WEST)
            return TorchLocation.EAST;
        return TorchLocation.WEST;
    } //end method
}
