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
        Person adam = new Person("Adam", 1);
        Person ben = new Person("Ben", 2);

        System.out.println(adam.getName() + " equals " + ben.getName() + " -> " + adam.equals(ben));
        System.out.println(adam.getName() + " hashcode " + adam.hashCode());
        System.out.println(ben.getName() + " hashcode " + ben.hashCode());
    }
}
