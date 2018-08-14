package in.mkp.java8inaction.chapter6;

import java.util.stream.IntStream;

public class Prime {

    public static void main(String[] args) {
        System.out.println("args = " + isPrime1(3));
    }

    public static boolean isPrime1(int n) {
        return IntStream
                .range(2, (n/2) +1)
                .noneMatch(i -> (n % i) == 0);
    }

}
