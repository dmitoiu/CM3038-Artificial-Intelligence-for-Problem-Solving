// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (BtAction.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import cm3038.search.Action;

import java.util.ArrayList;
import java.util.Collections;

public class BtAction extends Action {

    // Declare attributes
    public ArrayList<Person> personList;
    public TorchLocation torchLocation;

    public BtAction(ArrayList<Person> personList, TorchLocation torchLocation){
        this.personList = personList;
        this.torchLocation = torchLocation;
    }

    /**
     * This method will allow the access to the ArrayList of person objects
     * @return ArrayList of person objects
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * This method will allow the modification of the ArrayList of person objects
     * @param personList ArrayList of person objects
     */
    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    /**
     * This method will allow the access to the torch location enumeration
     * @return torch location enumeration
     */
    public TorchLocation getTorchLocation() {
        return torchLocation;
    }

    /**
     * This method will allow the modification of the torch location enumeration
     * @param torchLocation
     */
    public void setTorchLocation(TorchLocation torchLocation) {
        this.torchLocation = torchLocation;
    }

    /**
     * This method will allow the cost of the action to be modified
     * @param cost action cost as a double
     */
    public void setCost(double cost){
        this.cost = cost;
    }

    /**
     * This method will allow the visualisation of the representation of the action
     * @return Action string representation
     */
    @Override
    public String toString() {
        // Create string representation container
        String result = "";
        // Add "Move Torch " string to the container
        result+= "Move Torch ";
        // Get the maximum crossing time of the persons leaving the source for the destination
        this.cost = Collections.max(personList).getCrossingTime();
        // Loop over the ArrayList of person objects
        for(int i = 0; i < personList.size(); i++){
            // Create person object from the source ArrayList of person objects
            Person person = personList.get(i);
            // Add the name and crossing time of the person to the string representation container
            result+= person.getName() + "(" + person.getCrossingTime() + ")" + " ";
        }
        // If torch location is in east, continue...
        if(this.torchLocation == TorchLocation.EAST){
            // Add to the string representation the east mention
            result+= "From WEST to EAST " + "(cost:" + String.valueOf(this.cost) + ")";
        } else{
            // If the torch location is west, add to the string representation the west mention
            result+= "From EAST to WEST " + "(cost:" + String.valueOf(this.cost) + ")";
        }
        return result;
    }
}
