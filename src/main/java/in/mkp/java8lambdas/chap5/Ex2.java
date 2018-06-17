package in.mkp.java8lambdas.chap5;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex2 {


    public static void main(String[] args) {
        q2();
    }

    public static void q1() {

        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");

        Optional<String> res = names.collect(Collectors.maxBy(Comparator.comparing(String::length)));

        System.out.println("Result = " + res.get());

    }

    public static void q2() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John",
                "Paul", "John");

        Long map = names.collect(Collectors.counting());

        System.out.println("map = " + map);
    }
}
