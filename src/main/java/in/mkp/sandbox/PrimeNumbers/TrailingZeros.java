package in.mkp.sandbox.PrimeNumbers;

/**
 * Inspired by https://codefights.com/challenge/BpAwhnKbrt6s3Bebw
 *
 * Basically, simplified the problem to find number of trailing 0s in n!.
 *
 * REF: https://math.stackexchange.com/questions/141196/highest-power-of-a-prime-p-dividing-n
 *      http://mathforum.org/library/drmath/view/67291.html#assoc
 *      https://en.wikipedia.org/wiki/Legendre%27s_formula
 *
 *
 * For answers please see http://www.purplemath.com/modules/factzero.htm
 *
 */
public class TrailingZeros {

    public static void main(String[] args) {
        TrailingZeros trailingZeros = new TrailingZeros();
        System.out.println("divisors.count(10) = " + trailingZeros.count(10));
        System.out.println("divisors.count(23) = " + trailingZeros.count(23));
        System.out.println("divisors.count(100) = " + trailingZeros.count(100));
        System.out.println("divisors.count(1000) = " + trailingZeros.count(1000));
    }
    
    int count(int n) {
        int sum = 0;
        int i=1;
        int val;
        do {
            val = Math.floorDiv(n , (int)Math.pow(5, i++));
            sum+=val;
        } while (val > 0);

        return sum;
    }
}
