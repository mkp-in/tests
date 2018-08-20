package in.mkp.java8inaction.chapter10.usingnull;

/**
 *
 * REF: https://www.safaribooksonline.com/library/view/java-8-in/9781617291999/kindle_split_022.html
 */
public class Test {

    public static Person person = new Person();

    public static void main(String[] args) {
        usingNullsV1();
        usingNullsV2();
    }

    public static String usingNullsV1() {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                   return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    public static String usingNullsV2() {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();

        if (car == null) {
            return "Unknown" ;
        }

        Insurance insurance = car.insurance;

        if (insurance == null) {
            return "Unknown";
        }

        return insurance.getName();
    }
}
