package in.mkp.sandbox.bitoperators;

/**
 *
 * REF: http://www.catonmat.net/blog/low-level-bit-hacks-you-absolutely-must-know/
 *      http://graphics.stanford.edu/~seander/bithacks.html
 *
 * Created by mkumar on 4/10/17.
 */
public class BasicTricks {

    public static void main(String[] args) {
        BasicTricks basicTricks = new BasicTricks();
        System.out.println("786 "+Integer.toBinaryString(786) +
                " and setting nth bit: "+ Integer.toBinaryString(basicTricks.setNthBit(786, 7)));

        System.out.println("786 "+Integer.toBinaryString(786) +
                " and checking nth bit is set "+basicTricks.testNthBitSet(786, 8));

        System.out.println("786 "+Integer.toBinaryString(786) +
                " and toggle nth bit: "+ Integer.toBinaryString(basicTricks.toggleNthBit(786, 8)));

        System.out.println("88 "+Integer.toBinaryString(88) +
                " turn off rightmost 1-bit: "+ Integer.toBinaryString(basicTricks.turnOffRightMost1Bit(88)));

        System.out.println("88 "+Integer.toBinaryString(88) +
                " isolate rightmost 1-bit: "+ Integer.toBinaryString(basicTricks.isolateRightMost1Bit(88)));

        System.out.println("88 "+Integer.toBinaryString(88) +
                " isolate rightmost 1-bit version2: "+ Integer.toBinaryString(basicTricks.isolateRightMost1Bit2(88)));

        System.out.println("89 "+Integer.toBinaryString(89) +
                " isolate rightmost 0-bit: "+ Integer.toBinaryString(basicTricks.isolateRightMost0Bit(89)));

        System.out.println("89 "+Integer.toBinaryString(89) +
                " isolate rightmost 0-bit: "+ Integer.toBinaryString(basicTricks.swapBits(89)));
    }

    boolean testNthBitSet(final int i, final int n) {
        return ((i & ( 1 << n )) > 0);
    }

    int setNthBit(final int i, final int n) {
        return i | ( 1 << n );
    }

    int toggleNthBit(final int i, final int n) {
        return i ^ ( 1 << n);
    }

    int turnOffRightMost1Bit(final int i) {
        return i & (i-1);
    }

    int isolateRightMost1Bit(final int i) {
        return i & ~(i-1);
    }

    int isolateRightMost1Bit2(final int i) {
        //This bit hack works because of two's complement. In two's complement system -x is the same as ~x+1.
        return i & -i;
    }

    int swapBits(final int i) {
        int j = ~i & i;
        return j;
    }

    int isolateRightMost0Bit(final int i) {
        return ~i & (i+1);
    }
}

