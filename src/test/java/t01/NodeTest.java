package t01;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aleksandr Shevkunenko on 24.06.2017.
 */
public class NodeTest {
    @Test
    public void getStepCount() throws Exception {
        final int N = 10;

        Node root = new Node(0);

        for (int i = 1; i < N; i++) root.addOrGet(i);
        for (int i = 0; i < N; i++) assertEquals(i, root.getStepCount(i));

        for (int i = -1; i > -N; i--) root.addOrGet(i);
        for (int i = 0; i > -N; i--) assertEquals(-i, root.getStepCount(i));

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                assertEquals(i + j, root.addOrGet(i).getStepCount(-j));

        assertEquals(-1, root.getStepCount(N));
        assertEquals(-1, root.getStepCount(-N));

        //-------------------------------------------------

        int[] nums = { 16, 8, 32, 40, 4, 20, 12 };

        root = new Node(0);

        for (int i : nums) {
            root.addOrGet(i);
            root.addOrGet(-i);
        }

        assertEquals(4, root.addOrGet(-32).getStepCount(8));
        assertEquals(4, root.addOrGet(8).getStepCount(-32));
        assertEquals(3, root.addOrGet(-20).getStepCount(-8));
        assertEquals(3, root.addOrGet(-8).getStepCount(-20));
        assertEquals(4, root.addOrGet(-16).getStepCount(12));
        assertEquals(4, root.addOrGet(12).getStepCount(-16));
        assertEquals(4, root.addOrGet(4).getStepCount(40));
        assertEquals(4, root.addOrGet(40).getStepCount(4));
        assertEquals(1, root.addOrGet(8).getStepCount(12));
        assertEquals(1, root.addOrGet(12).getStepCount(8));
        assertEquals(2, root.addOrGet(-12).getStepCount(-16));
        assertEquals(2, root.addOrGet(-16).getStepCount(-12));

        assertEquals(-1, root.getStepCount(55));
        assertEquals(0, root.getStepCount(0));
        assertEquals(3, root.getStepCount(4));
    }

}