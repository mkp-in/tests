package in.mkp.java8lambdas.chap3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Example12 {

    public static void main(String[] args) {
        final List<Integer> integers = asList(1, 2);
        final List<Integer> integers1 = asList(3, 4);
        final List<Integer> collect = Stream.of(integers, integers1)
                .flatMap(numbers -> numbers.stream())
                .collect(toList());

        System.out.println("collect = " + Arrays.toString(collect.toArray()));
    }

}
