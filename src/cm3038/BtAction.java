package cm3038;// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem (cm3038.BtAction.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

import cm3038.search.Action;

import java.util.ArrayList;
import java.util.Collections;

public class BtAction extends Action {

    public ArrayList<Person> personList;
    public TorchLocation torchLocation;

    public BtAction(ArrayList<Person> personList, TorchLocation torchLocation){
        this.personList = personList;
        this.torchLocation = torchLocation;
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public TorchLocation getTorchLocation() {
        return torchLocation;
    }

    public void setTorchLocation(TorchLocation torchLocation) {
        this.torchLocation = torchLocation;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public static Person maxPerson(ArrayList<Person> personsList){
        Person person = personsList.get(0);
        for(int i = 0; i < personsList.size(); i++){
            if(personsList.get(i).getCrossingTime() > person.getCrossingTime()){
                person = personsList.get(i);
            }
        }
        return person;
    }

    @Override
    public String toString() {
        String result = "";
        this.cost = Collections.max(personList).getCrossingTime();
        result+= "Move Torch ";
        for(int i = 0; i < personList.size(); i++){
            Person person = personList.get(i);
            result+= person.getName() + "(" + person.getCrossingTime() + ")" + " ";
        }
        if(this.torchLocation == TorchLocation.EAST){
            result+= "From WEST to EAST " + "(cost:" + String.valueOf(this.cost) + ")";
        } else{
            result+= "From EAST to WEST" + "(cost:" + String.valueOf(this.cost) + ")";
        }
        return result;
    }
}
