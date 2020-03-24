// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (TestCombinations.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038.test;

import cm3038.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCombinations {

    public static void main(String[] args){
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        // Create person objects
        Person adam = new Person("Adam", 1);
        Person ben = new Person("Ben", 2);
        Person claire = new Person("Claire", 5);
        Person doris = new Person("Doris", 8);
        Person edward = new Person("Edward", 9);
        Person fiona = new Person("Fiona", 10);

        // Add person objects to the west ArrayList of person objects
        westPersonList.add(adam);
        westPersonList.add(ben);
        westPersonList.add(claire);
        westPersonList.add(doris);

        // Add peron objects to the east ArrayList of person objects
        eastPersonList.add(edward);
        eastPersonList.add(fiona);

        // Declare and assign combination size
        int r = 2;
        // Declare and assign ArrayList of person objects size
        int n = westPersonList.size();

        // ArrayList of combinations
        ArrayList<Person[]> personCombinations = new ArrayList<>();

        getCombinations(westPersonList, n, r, personCombinations);

        // Loop over combinations
        for(int i = 0; i < personCombinations.size(); i++){
            System.out.println(Arrays.toString(personCombinations.get(i)));
        }

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
                                        int start, int end, int index,
                                        int r, ArrayList<Person[]> leavingList){
        if(index == r){
            // Clone combination
            Person[] data = combination.clone();
            // Add combination to the list of all combinations
            leavingList.add(data);
            for(int i = 0; i < r; i++){
                // Print out combination
                System.out.println(combination[i]);
            }
            System.out.println("");
            return;
        }
        for(int j = start; j <= end && end - j + 1 >= r - index; j++){
            // Create combination
            combination[index] = personList.get(j);
            // Recall method with updated arguments
            combinationsUtil(personList, combination, j+1, end, index+1, r, leavingList);
        }
    }

    public static void getCombinations(ArrayList<Person> personList, int n, int r, ArrayList<Person[]> leavingList){
        // Create temporary combination array
        Person[] combination = new Person[r];
        // Generate combinations
        combinationsUtil(personList, combination, 0, n-1, 0, r, leavingList);
    }

}
