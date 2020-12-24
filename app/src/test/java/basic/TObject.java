package basic;

import org.junit.Test;

public class TObject {
    @Test
    public void testAssign() {
        To t1 = new To(1);
        To t2 = new To(12);
        System.out.println( t1 );
        t1 = t2;
        System.out.println( t1);
    }
    private class To {
        int v;
        To next;
        public To(int a) {
            this.v = a;
        }
		@Override
		public String toString() {
			return "To [next=" + next + ", v=" + v + "]";
		}

    }

    // TODO: notify, synchronize, producer/consumer, barber problem, etc.
    
}
