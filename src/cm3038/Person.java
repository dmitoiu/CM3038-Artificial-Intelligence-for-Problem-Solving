package cm3038;// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and cm3038.Torch Problem (cm3038.BtState.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return getCrossingTime() == person.getCrossingTime() &&
                getName().equals(person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCrossingTime());
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
