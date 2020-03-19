package cm3038.test;

import cm3038.Bridge;
import cm3038.BtState;
import cm3038.Person;
import cm3038.TorchLocation;

import java.util.ArrayList;

public class TestToString {

    public static void main(String[] args){
        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Person> emptyList = new ArrayList<Person>();

        personList.add(new Person("Adam", 1));
        personList.add(new Person("Ben", 2));
        personList.add(new Person("Claire", 5));
        personList.add(new Person("Doris", 8));

        Bridge bridge = new Bridge(2);
        BtState initialState = new BtState(personList, emptyList, TorchLocation.WEST, bridge);
        System.out.println(initialState.toString());
    }
}
