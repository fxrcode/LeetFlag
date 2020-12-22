package basic;

import java.lang.reflect.Field;
import java.util.*;

import org.junit.Test;

public class ComparatorInterval {
    @Test
    public void testInterval1() {
        Interval inta = new Interval(1,5);
        Interval intb1 = new Interval(-3,0);
        Interval intb2 = new Interval(1,4);
        Interval intb3 = new Interval(2,4);
        Interval intb4 = new Interval(2,8);
        Interval intb5 = new Interval(7,8);

        System.out.println(inta);

        // System.out.println(showFields(inta));;

        List<Interval> ints = Arrays.asList(inta, intb1, intb2, intb3, intb4, intb5);
        Comparator<Interval> comp = (i1, i2) -> {
            if (i1.left == i2.left) {
                return i1.right - i2.right;
            } else {
                return i1.left - i2.left;
            }
        };
        System.out.println( comp.compare(inta, intb1));
        Collections.sort(ints, comp);
        System.out.println( ints );
    }

    private class Interval {
        int left, right;
        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }
		@Override
		public String toString() {
			return "Interval [" + left + ", " + right + "]";
		}
    }

    /**
     * Object name is used by compiler, so I can't get it, eg. 'intb1'
     * @param o
     * @return
     */
    private static String getObjName(Object o) {
        Field fd[] = o.getClass().getDeclaredFields();
        for (int i = 0; i < fd.length; i++) {
              System.out.println(fd[i].getName());
        }
        return "";
    }

    /**
     * https://www.quora.com/How-can-I-print-variable-name-in-Java
     * @param object
     * @return
     */
    public static String showFields(Object object) { 
        StringBuilder result = new StringBuilder();
        for (Field f: object.getClass().getDeclaredFields()) {
            try {
            result
            .append(f.getName()) 
            .append(" : ")
            .append(f.get(object)) 
            .append(System.getProperty("line.separator")); 
            } 
            catch (IllegalStateException ise) { 
                result
                .append(f.getName()) 
                .append(" : ") 
                .append("[cannot retrieve value]") 
                .append(System.getProperty("line.separator")); 
            } 
            // nope 
            catch (IllegalAccessException iae) {} 
        } 
        return result.toString();
    }
}
