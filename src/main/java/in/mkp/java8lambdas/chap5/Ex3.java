package in.mkp.java8lambdas.chap5;

import java.util.HashMap;
import java.util.Map;

public class Ex3 {
    final static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("args = " + fibonacci(6));
    }

    public static Integer fibonacci(int n) {
        //IntStream.range(1, n).reduce(1, i -> map.computeIfAbsent(1, fibonacci(i)));

        return 0;

    }

}
