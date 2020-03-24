// ---------------------------------------------------------------------------
// Darie-Dragos Mitoiu
// The Bridge and Torch Problem (Bridge.java) v1.0.0 30/01/2020
// A program designed to solve the bridge and torch problem using A* algorithm
// ---------------------------------------------------------------------------

package cm3038;

public class Bridge {

    // Declaring attributes
    public int capacity;

    public Bridge(int capacity){
        this.capacity = capacity;
    }

    /**
     * This method will allow the access to the capacity attribute
     * @return Capacity integer
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * This method will allow the modification of the capacity attribute of the object
     * @param capacity integer
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * This method will allow the verification of the equality of 2 bridge objects
     * @param bridge object
     * @return Equality boolean
     */
    @Override
    public boolean equals(Object bridge) {
        if(!(bridge instanceof Bridge)){
            return false;
        }
        Bridge btBridge = (Bridge) bridge;
        boolean status = this.getCapacity() == btBridge.getCapacity();
        return status;
    }

    /**
     * This method will allow the generation of an unique integer value for the bridge object
     * @return Hash integer
     */
    @Override
    public int hashCode() {
        // Declare hash and assign value
        int hash = 7;
        // Generate hash integer
        hash = 31 * hash + (int) this.getCapacity();
        return hash;
    }
}
