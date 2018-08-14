package in.mkp.java8inaction.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctLettersInAnArray {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        final List<String> collect = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("collect = " + Arrays.toString(collect.toArray()));
    }

}
