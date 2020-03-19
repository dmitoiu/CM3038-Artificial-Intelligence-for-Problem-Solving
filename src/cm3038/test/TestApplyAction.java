package cm3038.test;

import cm3038.*;
import cm3038.search.SearchProblem;

import java.util.ArrayList;

public class TestApplyAction {

    public static void main(String[] args){
        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Person> emptyList = new ArrayList<Person>();

        personList.add(new Person("Adam", 1));
        personList.add(new Person("Ben", 2));
        personList.add(new Person("Claire", 5));
        personList.add(new Person("Doris", 8));

        Bridge bridge = new Bridge(2);
        BtState beforeState = new BtState(personList, emptyList, TorchLocation.WEST, bridge);

        ArrayList<Person> leaving = new ArrayList<>();
        for(int i = 0; i < beforeState.westPersonList.size(); i++){
            Person person = beforeState.westPersonList.get(i);
            if(person.getCrossingTime() <= 2 && !leaving.contains(person)){
                leaving.add(person);
            }
        }
        BtAction btAction = new BtAction(leaving, TorchLocation.EAST);
        BtState afterState = beforeState.applyAction(btAction);
        System.out.println(beforeState.toString());
        System.out.println(btAction.toString());
        System.out.println(afterState.toString());
    }
}
