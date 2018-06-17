package in.mkp.java8lambdas.chap3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Ex7 {

    public static void main(String[] args) {
        ex7();
    }

    public static void ex7() {
        List<String> list = Arrays.asList("abc", "ABC", "abcdef", "AbC", "ghiK", "abcdefghijklm");

        Optional<String> result = list.stream().max((String s1, String s2) -> {
            int len1 = (int) (s1.chars().filter(Character::isLowerCase).count());
            int len2 = (int) (s2.chars().filter(Character::isLowerCase).count());
            return len1 - len2;
        });

        if (result.isPresent()) {
            System.out.println("result.get() = " + result.get());
        }

    }

}
