package in.mkp.java8inaction.chapter10.usingoptional;

import java.util.Optional;


public class Test {

    public static Person person = new Person();

    public static void main(String[] args) {
        System.out.println("args = " + usingOptional());
    }

    public static String usingOptional() {
                return Optional.of(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

}
