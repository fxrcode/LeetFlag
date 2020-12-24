package basic;

import java.util.*;

import org.junit.Test;

public class TArray {
    @Test
    public void printArray() {
        int[] A = {1,2,3,4,5,6,98};
        // https://stackoverflow.com/questions/51852848/java-print-array-of-integers-joined-by-spaces
        System.out.println( Arrays.toString(A));
    }

    @Test
    public void convertListToArray() {
        List<Integer> A = Arrays.asList(9, 89, 6, 5, 4, 3,2,1);
        // https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
        int[] B = A.stream().mapToInt(i -> i).toArray();
        System.out.println( Arrays.toString(B));
    }
}
