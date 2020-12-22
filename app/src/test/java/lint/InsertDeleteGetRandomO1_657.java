package lint;

import java.util.*;
import java.util.Random;

// Your submission beats 62.60% Submissions!
public class InsertDeleteGetRandomO1_657 {
    private List<Integer> ARR = new ArrayList<>();  // this is array not linked list.
    private Map<Integer, Integer> IndexesByInt = new HashMap<>();
    private Random rand = new Random();

    public RandomizedSet() {
        // do intialization if necessary
    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        // write your code here
        if (!IndexesByInt.containsKey(val) ) {
            ARR.add(val);
            IndexesByInt.put(val, ARR.size()-1);
            return true;
        } else {
            return false;
        }
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        // write your code here
        if (!IndexesByInt.containsKey(val)) {
            return false;
        }
        int idx = IndexesByInt.get(val);
        // nice function: do swap, then update map, and remove the val.
        Collections.swap(ARR, idx, ARR.size()-1);
        // remove the found one
        IndexesByInt.remove(val);
        // update swapped last item's psotion
        IndexesByInt.put(ARR.get(idx), idx);
        // don't forget to remove the last one from array
        ARR.remove(ARR.size()-1);
        return true;
    }

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        // write your code here
        int idx = rand.nextInt(ARR.size());
        return ARR.get(idx);
    }
}
