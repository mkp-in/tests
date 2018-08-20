package in.mkp.java8inaction.chapter10.usingoptional;

import java.util.Optional;

public class Car {
    private Optional<Insurance> insurance = Optional.empty();

    public Optional<Insurance> getInsurance() {
        return this.insurance;
    }

}
