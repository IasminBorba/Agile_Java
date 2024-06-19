package util;

import junit.framework.TestCase;

public class ParityCheckerTest extends TestCase {
    public void testParity(){
        assertEquals(0, xorAll(0,1,0,1));
        assertEquals(1, xorAll(0,1,1,1));
    }

    private int xorAll(int first, int... rest){
        int parity = first;
        for(int num: rest){
            parity ^= num;
        }
        return parity;
    }

    public void testSingleByte(){
        ParityChecker checker = new ParityChecker();
        byte source1 = 10;
        byte source2 = 13;
        byte source3 = 2;

        byte[] data = new byte[] {source1, source2, source3};

        byte checksum = 5;

        assertEquals(checksum, checker.checksum(data));

        data[1] = 14;
        assertTrue(6 == checker.checksum(data));
    }
}
