package in.mkp.java8inaction.chapter10.usingoptional;

import java.util.Optional;

public class Person {

    public Optional<Car> car = Optional.empty();

    public Optional<Car> getCar() {
        return this.car;
    }
}
