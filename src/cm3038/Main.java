// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (Main.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import cm3038.search.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args){
        // Create String separator
        String separator = new String(new char[120]).replace("\0", "-");
        System.out.println("BASIC PROBLEM:");
        System.out.println(separator);
        // Solve basic problem
        solveBasicProblem();
        System.out.println("STATIC ADVANCED PROBLEM:");
        System.out.println(separator);
        // Solve static advanced problem
        solveAdvancedProblem();
        System.out.println("DYNAMIC ADVANCED PROBLEM:");
        System.out.println(separator);
        // Solve dynamic advanced problem
        solveDynamicAdvancedProblem();
    }

    /**
     * This method will allow the basic problem to be solved using A* algorithm
     */
    public static void solveBasicProblem(){
        // Create west and east array lists
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        // Create person objects
        Person adam = new Person("Adam", 1);
        Person ben = new Person("Ben", 2);
        Person claire = new Person("Claire", 5);
        Person doris = new Person("Doris", 8);

        // Add person objects to west array list
        westPersonList.add(adam);
        westPersonList.add(ben);
        westPersonList.add(claire);
        westPersonList.add(doris);

        // Sort west array lists
        Collections.sort(westPersonList);

        // Create bridge object
        Bridge bridge = new Bridge(2);

        // Create initial state
        BtState initialState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);
        // Create goal state
        BtState goalState = new BtState(eastPersonList, westPersonList, TorchLocation.EAST, bridge);

        // Create A Star problem object
        BridgeTorchAStar problem = new BridgeTorchAStar(initialState, goalState);

        // Print initial state
        System.out.println("Init: " + initialState.toString());
        // Print goal state
        System.out.println("Goal: " + goalState.toString());
        System.out.println("");
        // Create path object
        Path path=problem.search();
        if (path==null)
            System.out.println("No solution");
        else {
            path.print();
            System.out.println("Nodes visited: "+problem.nodeVisited);
            System.out.println("Solution Cost: "+path.cost+"\n");
        }
    }

    /**
     * This method will allow the static advanced problem to be solved using A* algorithm
     */
    public static void solveAdvancedProblem(){
        // Create west and east array lists
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        // Create person objects
        Person adam = new Person("Adam", 1);
        Person ben = new Person("Ben", 2);
        Person claire = new Person("Claire", 5);
        Person doris = new Person("Doris", 8);
        Person edward = new Person("Edward", 9);
        Person fiona = new Person("Fiona", 10);

        // Add person objects to west array list
        westPersonList.add(adam);
        westPersonList.add(ben);
        westPersonList.add(claire);
        westPersonList.add(doris);

        // Add person objects to east array list
        eastPersonList.add(edward);
        eastPersonList.add(fiona);

        // Sort west and east array lists
        Collections.sort(westPersonList);
        Collections.sort(eastPersonList);

        // Create bridge object
        Bridge bridge = new Bridge(3);

        // Create initial state
        BtState initialState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);
        // Create goal state
        BtState goalState = new BtState(eastPersonList, westPersonList, TorchLocation.EAST, bridge);

        // Create A Star problem object
        BridgeTorchAStar problem = new BridgeTorchAStar(initialState, goalState);

        // Print initial state
        System.out.println("Init: " + initialState.toString());
        // Print goal state
        System.out.println("Goal: " + goalState.toString());
        System.out.println("");
        // Create path object
        Path path=problem.search();
        if (path==null)
            System.out.println("No solution");
        else {
            path.print();
            System.out.println("Nodes visited: "+problem.nodeVisited);
            System.out.println("Solution Cost: "+path.cost+"\n");
        }
    }

    /**
     * This method will allow the dynamic advanced problem to be solved using A*
     */
    public static void solveDynamicAdvancedProblem(){
        boolean addPerson = true;
        String personName = "";
        int personCrossingTime = 0;
        int count = 0;
        int people = 0;
        int bridgeCapacity = 0;

        // Create buffer reader object
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        // Create west and east array lists
        ArrayList<Person> westPersonList = new ArrayList<Person>();
        ArrayList<Person> eastPersonList = new ArrayList<Person>();

        System.out.println("WEST BANK:");
        System.out.print("Enter number of people you wish to add to the west bank: ");
        try{
            // Get number of people on west bank
            String peopleString = bufferReader.readLine();
            // Validate user input
            while(!Validation.validateInt(String.valueOf(peopleString))){
                System.out.println("\nInvalid input. Please enter numerical values only.\n");
                System.out.print("\nEnter number of people you wish to add to the west bank: ");
                peopleString = bufferReader.readLine();
            }
            // Convert user input to integer
            people = Integer.parseInt(peopleString);
        } catch (IOException e){
            e.printStackTrace();
        }
        // If number of people is not 0, continue...
        while(addPerson && people != 0){
            try{
                System.out.print("Enter person name: ");
                // Get user input
                personName = bufferReader.readLine();
                System.out.print("Enter person crossing time: ");
                // Get user input
                String personCrossingTimeString = bufferReader.readLine();
                // Validate user input
                while(!Validation.validateInt(personCrossingTimeString)){
                    System.out.println("\nInvalid input. Please enter numerical values only.\n");
                    System.out.print("Enter person crossing time: ");
                    personCrossingTimeString = bufferReader.readLine();
                }
                // Convert user input to integer
                personCrossingTime = Integer.parseInt(personCrossingTimeString);
                // Create person object
                Person westPerson = new Person(personName, personCrossingTime);
                // Add object to the west bank
                westPersonList.add(westPerson);
                System.out.println(westPerson.getName() + " has been added to the west bank successfully.");
                // Increase counter by 1
                count++;
                if(count == people){
                    // If counter reaches limit, then break loop
                    addPerson = false;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("");
        System.out.println("EAST BANK:");
        System.out.print("Enter number of people you wish to add to the east bank: ");
        try{
            // Get user input
            String peopleString = bufferReader.readLine();
            // Validate user input
            while(!Validation.validateInt(String.valueOf(peopleString))){
                System.out.println("\nInvalid input. Please enter numerical values only.");
                System.out.print("\nEnter number of people you wish to add to the east bank: ");
                peopleString = bufferReader.readLine();
            }
            // Convert user input to integer
            people = Integer.parseInt(peopleString);
            // Reset flag
            addPerson = true;
            // Reset counter
            count = 0;
        } catch (IOException e){
            e.printStackTrace();
        }
        // If number of people is not 0, continue...
        while(addPerson && people != 0){
            try{
                System.out.print("Enter person name: ");
                // Get user input
                personName = bufferReader.readLine();
                System.out.print("Enter person crossing time: ");
                // Get user input
                String personCrossingTimeString = bufferReader.readLine();
                // Validate user input
                while(!Validation.validateInt(personCrossingTimeString)){
                    System.out.println("\nInvalid input. Please enter numerical values only.");
                    System.out.print("\nEnter person crossing time: ");
                    personCrossingTimeString = bufferReader.readLine();
                }
                // Convert user input to integer
                personCrossingTime = Integer.parseInt(personCrossingTimeString);
                // Create person object
                Person eastPerson = new Person(personName, personCrossingTime);
                // Add object to the east bank
                eastPersonList.add(eastPerson);
                System.out.println(eastPerson.getName() + " has been added to the west bank successfully.");
                // Increase counter by 1
                count++;
                if(count == people){
                    // If counter reaches limit, then break loop
                    addPerson = false;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("");
        System.out.println("Bridge Capacity:");
        System.out.print("Enter bridge capacity: ");
        try{
            // Get user input
            String bridgeString = bufferReader.readLine();
            // Validate user input
            while(!Validation.validateInt(String.valueOf(bridgeString))){
                System.out.println("\nInvalid input. Please enter numerical values only.");
                System.out.print("\nEnter bridge capacity: ");
                bridgeString = bufferReader.readLine();
            }
            // Convert user input to integer
            bridgeCapacity = Integer.parseInt(bridgeString);
        } catch (IOException e){
            e.printStackTrace();
        }

        // Sort west and east array lists
        Collections.sort(westPersonList);
        Collections.sort(eastPersonList);

        // Create bridge object
        Bridge bridge = new Bridge(bridgeCapacity);

        // Create initial state
        BtState initialState = new BtState(westPersonList, eastPersonList, TorchLocation.WEST, bridge);
        // Create goal state
        BtState goalState = new BtState(eastPersonList, westPersonList, TorchLocation.EAST, bridge);

        // Create A Star problem object
        BridgeTorchAStar problem = new BridgeTorchAStar(initialState, goalState);

        // Print initial state
        System.out.println("Init: " + initialState.toString());
        // Print goal state
        System.out.println("Goal: " + goalState.toString());
        System.out.println("");
        // Create path object
        Path path=problem.search();
        if (path==null)
            System.out.println("No solution");
        else {
            // Print the moves made to reach the goal
            path.print();
            System.out.println("Nodes visited: "+problem.nodeVisited);
            System.out.println("Solution Cost: "+path.cost+"\n");
        }
    }
}
