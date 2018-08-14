package in.mkp.java8lambdas.chap6;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Ex1 {

    public static void main(String[] args) {
        final long l = sumOfSqaures(5);
        System.out.println("l = " + l);
    }

    public static long sumOfSqaures(int size) {
        long[] values = new long[size];
        Arrays.parallelSetAll(values, i -> i * i);
        return IntStream.range(0, size).mapToLong(i -> values[i]).sum();
    }

}
