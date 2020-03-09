// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem (cm3038.BtState.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import cm3038.search.ActionStatePair;
import cm3038.search.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BtState implements State {

    public ArrayList<Person> westPersonList, eastPersonList;
    public TorchLocation torchLocation;
    public int elapsedTime;

    public BtState(ArrayList<Person> westPersonList, ArrayList<Person> eastPersonList, TorchLocation torchLocation){
        this.westPersonList = westPersonList;
        this.eastPersonList = eastPersonList;
        this.torchLocation = torchLocation;
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
        return Objects.hash(getWestPersonList(), getEastPersonList(), getTorchLocation());
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
        result+= " |==================" + "(2)" + "==================| ";
        if(this.getTorchLocation() == TorchLocation.EAST){
            result+= "Torch ";
        }
        for(int i = 0; i < eastPersonList.size(); i++){
            Person person = eastPersonList.get(i);
            result+= person.getName() + "(" + person.getCrossingTime() + ")" + " ";
        }
        result+= "\n";
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

        for(int i = 0; i < source.size(); i++){
            ArrayList<Person> leaving = new ArrayList<>();
            Person person = source.get(i);
            if(!leaving.contains(person)){
                leaving.add(person);
                BtAction btAction = new BtAction(leaving, this.oppositeLocation(this.getTorchLocation()));
                BtState nextState = this.applyAction(btAction);
                btAction.setCost(Collections.max(leaving).getCrossingTime());
                result.add(new ActionStatePair(btAction, nextState));
            }

            for(int j = 0; j < source.size(); j++){
                ArrayList<Person> leaving2 = (ArrayList<Person>) leaving.clone();
                Person person2 = source.get(j);
                if(!leaving2.contains(person2)){
                    leaving2.add(person2);
                    BtAction btAction = new BtAction(leaving2, this.oppositeLocation(this.getTorchLocation()));
                    BtState nextState = this.applyAction(btAction);
                    btAction.setCost(Collections.max(leaving2).getCrossingTime());
                    result.add(new ActionStatePair(btAction, nextState));
                }
            }

        }

        return result;
    }

    public int min(int[] array){
        int minValue = array[0];
        for(int i = 0; i < array.length; i++){
            if(array[i] < minValue){
                minValue = array[i];
            }
        }
        return minValue;
    }

    public int[] find2Min(int[] array){
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for(int i = 0; i < array.length; i++){
            if(array[i] < firstMin && array[i] != 0){
                secondMin = firstMin;
                firstMin = array[i];
            } else if(array[i] < secondMin && array[i] != firstMin && array[i] != 0){
                secondMin = array[i];
            }
        }
        return new int[]{firstMin, secondMin};
    }

    public int[] find2Max(int[] array){
        int firstMax = array[0];
        int secondMax = array[0];

        for(int i = 0; i < array.length; i++){
            if(array[i] > firstMax){
                secondMax = firstMax;
                firstMax = array[i];
            } else if(array[i] > secondMax){
                secondMax = array[i];
            }
        }
        return new int[]{firstMax, secondMax};
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

        BtState nextState = new BtState(west, east, this.oppositeLocation(this.getTorchLocation()));
        return nextState;
    } //end method

    public boolean isGoal(){
        return westPersonList.isEmpty();
    }

    private TorchLocation oppositeLocation(TorchLocation current)
    {
        if (current== TorchLocation.WEST)
            return TorchLocation.EAST;
        return TorchLocation.WEST;
    } //end method
}
