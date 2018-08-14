package in.mkp.java8lambdas.chap3;

import java.util.stream.Stream;

public class Example16 {

    public static void main(String[] args) {
        int count = Stream.of(1, 2, 3).reduce(0, (a, b) -> {
            System.out.println("a = " + a + " b = "+b);
            return a+b;});
        System.out.println("count = " + count);
    }

}
