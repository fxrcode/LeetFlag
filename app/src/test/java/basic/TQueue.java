package basic;

import java.util.*;

import org.junit.Test;

public class TQueue {
    @Test
    public void testComp() {
        Comparator<Node> compMin = (n1, n2) -> {
            return n1.v - n2.v;
        };
        Comparator<Node> compMax = (n1, n2) -> {
            return n2.v - n1.v;
        };
        // Should I declare pq with Interface or Concrete type? https://stackoverflow.com/a/7716516/3984911
        // PriorityQueue<Node> pq = new PriorityQueue(compMin);
        Queue<Node> pq = new PriorityQueue<>(compMax);
        Node n1 = new Node(0,0, 1);
        Node n2 = new Node(1,2,42);

        pq.offer(n1);
        pq.offer(n2);

        while (!pq.isEmpty()) {
            System.out.println( pq.poll().v);
        }
    }

    private class Node {
        int  r,c;
        int v;
        public Node(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
}
