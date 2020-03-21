package cm3038.test;

import cm3038.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCombinations {

    public static void main(String[] args){
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        westPersonList.add(new Person("Adam", 1));
        westPersonList.add(new Person("Ben", 2));
        westPersonList.add(new Person("Claire", 5));
        westPersonList.add(new Person("Doris", 8));

        int r = 2;
        int n = westPersonList.size();

        ArrayList<Person[]> personCombinations = new ArrayList<>();

        getCombinations(westPersonList, n, r, personCombinations);

        for(int i = 0; i < personCombinations.size(); i++){
            System.out.println(Arrays.toString(personCombinations.get(i)));
        }

    }

    public static void combinationsUtil(ArrayList<Person> personList, Person[] combination,
                                        int start, int end, int index,
                                        int r, ArrayList<Person[]> leavingList){
        if(index == r){
            Person[] data = combination.clone();
            leavingList.add(data);
            for(int i = 0; i < r; i++){
                System.out.println(combination[i]);
            }
            System.out.println("");
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

}
