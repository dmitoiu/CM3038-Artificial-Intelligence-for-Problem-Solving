// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (cm3038.BtState.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import java.util.Collections;
import java.util.Objects;

public class Person implements Comparable<Person> {

    public String name;
    public int crossingTime;

    public Person(String name, int crossingTime){
        this.name = name;
        this.crossingTime = crossingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCrossingTime(int crossingTime) {
        this.crossingTime = crossingTime;
    }

    public String getName() {
        return name;
    }

    public int getCrossingTime() {
        return crossingTime;
    }

    @Override
    public boolean equals(Object person) {
        if(!(person instanceof Person)){
            return false;
        }
        Person btPerson = (Person) person;

        boolean status = (this.getName().equals(btPerson.getName()) &&
                          this.getCrossingTime() == btPerson.getCrossingTime());
        return status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) this.getCrossingTime();
        hash = 31 * hash + (this.getName() == null ? 0 : this.getName().hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", crossingTime=" + crossingTime +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.crossingTime - o.crossingTime;
    }
}
