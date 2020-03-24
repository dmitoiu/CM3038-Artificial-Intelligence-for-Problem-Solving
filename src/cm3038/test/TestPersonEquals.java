// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (TestPersonEquals.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038.test;

import cm3038.Person;

public class TestPersonEquals {

    public static void main(String[] args){
        // Create person objects
        Person person1 = new Person("Adam", 1);
        Person person2 = new Person("Ben", 2);

        System.out.println(person1.getName() + " equals " + person2.getName() + " -> " + person1.equals(person2));
        System.out.println(person1.getName() + " hashcode " + person1.hashCode());
        System.out.println(person2.getName() + " hashcode " + person2.hashCode());
    }
}
