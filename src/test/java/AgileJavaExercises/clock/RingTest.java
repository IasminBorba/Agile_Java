package AgileJavaExercises.clock;

import junit.framework.TestCase;

public class RingTest extends TestCase {
    public void testCreate() throws Exception {
        Ring<String> ring = new Ring<>();
        assertEquals(0, ring.size());

        String name1 = "Name1";
        ring.add(name1);
        assertTrue(ring.hasElement(name1));
        assertEquals(1, ring.size());

        String name2 = "Name2";
        ring.add(name2);
        assertTrue(ring.hasElement(name2));
        assertEquals(2, ring.size());

        String nameNotAdded = "name Not Added";
        assertFalse(ring.hasElement(nameNotAdded));
    }

    public void testRemoveElement() throws Exception {
        Ring<String> ring = new Ring<>();
        assertEquals(0, ring.size());

        String name1 = "Name1";
        ring.add(name1);
        assertTrue(ring.hasElement(name1));
        assertEquals(1, ring.size());
        assertEquals(name1, ring.getCurrentElement());


        String name2 = "Name2";
        ring.add(name2);
        assertTrue(ring.hasElement(name2));
        assertEquals(2, ring.size());

        String strRing = "Name1 <-> Name2 <-> Name1";
        assertEquals(strRing, ring.printRing());


        ring.remove(name1);
        assertFalse(ring.hasElement(name1));
        assertEquals(1, ring.size());
        assertEquals(name2, ring.getCurrentElement());

        strRing = "Name2 <-> Name2";
        assertEquals(strRing, ring.printRing());

        try {
            ring.remove(name2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertFalse(ring.hasElement(name2));
        assertEquals(0, ring.size());

        try {
            assertEquals(null, ring.getCurrentElement());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            assertEquals(null, ring.printRing());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void testAdvancingOrBacking() throws Exception {
        Ring<String> ring = new Ring<>();
        assertEquals(0, ring.size());

        String name1 = "Maria";
        ring.add(name1);
        assertTrue(ring.hasElement(name1));
        assertEquals(1, ring.size());
        assertEquals("Maria <-> Maria", ring.printRing());


        String name2 = "José";
        ring.add(name2);
        assertTrue(ring.hasElement(name2));
        assertEquals(2, ring.size());


        try {
            assertEquals(name1, ring.getCurrentElement());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals("Maria <-> José <-> Maria", ring.printRing());

        ring.advanced();
        try {
            assertEquals(name2, ring.getCurrentElement());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals("José <-> Maria <-> José", ring.printRing());

        ring.back();
        try {
            assertEquals(name1, ring.getCurrentElement());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals("Maria <-> José <-> Maria", ring.printRing());
    }

    public void testAddException() throws Exception {
        Ring<Integer> ring = new Ring<>();
        assertEquals(0, ring.size());

        Integer number1 = 10;
        ring.add(number1);
        try {
            ring.add(number1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ring.hasElement(number1));
        assertEquals(1, ring.size());
        assertEquals("10 <-> 10", ring.printRing());
    }

    public void testRemoveException() {
        Ring<Object> ring = new Ring<>();

        Integer number1 = 10;
        try {
            ring.remove(number1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, ring.size());
    }

    public void testAdvancedAndBackException() throws Exception {
        Ring<Character> ring = new Ring<>();
        assertEquals(0, ring.size());

        try {
            ring.advanced();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ring.back();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Character a = 'A';
        ring.add(a);
        try {
            ring.advanced();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ring.back();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals("A <-> A", ring.printRing());
    }

    public void testExceptions() throws Exception {
        Ring<String> ring = new Ring<>();
        assertEquals(0, ring.size());

        try {
            ring.getCurrentElement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ring.printRing();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String sport1 = "Soccer";
        try {
            ring.alterCurrentElement(sport1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ring.add(sport1);

        String sport2 = "Basketball";
        ring.add(sport2);

        String sport3 = "Baseball";
        ring.add(sport3);

        String sport4 = "Tennis";
        ring.add(sport4);

        String sport5 = "Volleyball";
        ring.add(sport5);

        String sport6 = "Swimming";
        ring.add(sport6);

        String sport7 = "Cycling";
        ring.add(sport7);

        String sport8 = "Gymnastics";
        ring.add(sport8);

        String sport9 = "Surfing";
        ring.add(sport9);

        String sport10 = "Skateboarding";
        ring.add(sport10);

        assertEquals("Soccer <-> Skateboarding <-> Surfing <-> Gymnastics <-> Cycling <-> " +
                "Swimming <-> Volleyball <-> Tennis <-> Baseball <-> Basketball <-> Soccer", ring.printRing());

        try {
            ring.advanced();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ring.back();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            ring.alterCurrentElement(sport7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(sport7, ring.getCurrentElement());

        ring.remove(sport7);
        assertEquals(sport6, ring.getCurrentElement());
        assertEquals("Swimming <-> Volleyball <-> Tennis <-> Baseball <-> Basketball <-> " +
                "Soccer <-> Skateboarding <-> Surfing <-> Gymnastics <-> Swimming", ring.printRing());

        ring.alterCurrentElement(sport2);
        ring.remove(sport2);
        assertEquals(sport1, ring.getCurrentElement());
        assertEquals("Soccer <-> Skateboarding <-> Surfing <-> Gymnastics <-> " +
                "Swimming <-> Volleyball <-> Tennis <-> Baseball <-> Soccer", ring.printRing());
    }

    public void testAssert() throws Exception {
        Ring<String> ring = new Ring<>();
        assertEquals(0, ring.size());

        String Xnull = null;
        try {
            ring.add(Xnull);
        } catch (AssertionError Assert) {
            System.out.println(Assert.getMessage());
        }

        assertEquals(0, ring.size());
    }
}
