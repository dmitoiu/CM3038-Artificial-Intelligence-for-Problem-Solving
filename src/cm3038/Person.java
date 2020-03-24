// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (Person.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import java.util.Collections;
import java.util.Objects;

public class Person implements Comparable<Person> {

    // Declaring attributes
    public String name;
    public int crossingTime;

    public Person(String name, int crossingTime){
        this.name = name;
        this.crossingTime = crossingTime;
    }

    /**
     * This method will allow the modification of the name attribute
     * @param name Name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method will allow the modification of the crossing time attribute
     * @param crossingTime Time Integer
     */
    public void setCrossingTime(int crossingTime) {
        this.crossingTime = crossingTime;
    }

    /**
     * This method will allow the access to the name attribute
     * @return Name String
     */
    public String getName() {
        return name;
    }

    /**
     * This method will allow the access to the crossing time attribute
     * @return
     */
    public int getCrossingTime() {
        return crossingTime;
    }

    /**
     * This method will check the equality of the current person
     * object and a different person object passed as an argument
     * @param person Person Object
     * @return Equality boolean
     */
    @Override
    public boolean equals(Object person) {
        if(!(person instanceof Person)){
            // If the argument is not a person object return false
            return false;
        }
        // Create person object
        Person btPerson = (Person) person;

        // Check equality
        boolean status = (this.getName().equals(btPerson.getName()) &&
                          this.getCrossingTime() == btPerson.getCrossingTime());
        return status;
    }

    /**
     * This method will generate an integer which will be used
     * to verify the equality of the person objects
     * @return Hash Integer Value
     */
    @Override
    public int hashCode() {
        // Declare hash and assign value
        int hash = 7;
        // Generate hash integer
        hash = 31 * hash + (int) this.getCrossingTime();
        hash = 31 * hash + (this.getName() == null ? 0 : this.getName().hashCode());
        return hash;
    }

    /**
     * This method will allow the creation of the person object representation
     * @return Person object attributes as string
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", crossingTime=" + crossingTime +
                '}';
    }

    /**
     * This method will behave similar to the equals method,
     * but it will work only for person objects,
     * This method will be used when sorting the array lists of person objects
     * in Collections.sort(ArrayList<Person>) and getting the max and min
     * using the Collections.max() and Collection.min() methods.
     * @param person Person object
     * @return Crossing Time Order
     */
    @Override
    public int compareTo(Person person) {
        // Compare current object crossing time with the crossing time of the person object passed as an argument
        return this.crossingTime - person.crossingTime;
    }
}
