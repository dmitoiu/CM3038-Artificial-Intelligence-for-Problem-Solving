// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (Validation.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

import java.util.regex.Pattern;

public class Validation {

    // Declaring constants
    private static final Pattern ALPHA_PATTERN = Pattern.compile("^[a-zA-Z]*$");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("[0-9]+");

    /**
     * This method will validate the user input for alphabetical characters
     * @param userInput string
     * @return boolean
     */
    public static boolean validateAlpha(String userInput){
        if(!ALPHA_PATTERN.matcher(userInput).matches()){
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method will validate the user input for numerical values
     * @param userInput string
     * @return boolean
     */
    public static boolean validateInt(String userInput){
        if(!NUMERIC_PATTERN.matcher(userInput).matches()){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Do not create an object from this utility class
     */
    private Validation() {}

}
