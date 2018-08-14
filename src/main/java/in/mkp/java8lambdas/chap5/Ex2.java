package in.mkp.java8lambdas.chap5;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex2 {


    public static void main(String[] args) {
        q1();
        q2();
    }

    /**
     * Find the artist with the longest name. You should implement this using a Collector and the reduce higher-order
     * function from Chapter 3. Then compare the differences in your implementation: which was easier to write and
     * which was easier to read? The following example should return "Stuart Sutcliffe":
     *
     * Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
     *     "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
     */

    public static void q1() {

        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");

        Optional<String> res = names.collect(Collectors.maxBy(Comparator.comparing(String::length)));

        System.out.println("Result = " + res.get());

    }

    /**
     * Given a Stream where each element is a word, count the number of times each word appears.
     * So, if you were given the following input, you would return a Map of [John → 3, Paul → 2, George → 1]:
     *
     * Stream<String> names = Stream.of("John", "Paul", "George", "John",
     *                                  "Paul", "John");
     */
    public static void q2() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John",
                "Paul", "John");

        final Map<String, Long> collect = names.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        System.out.println("collect = " + collect);
    }
}
