package in.mkp.java8lambdas.chap3;

public class Excercise6 {

    public static void main(String[] args) {
        String testString = "this is a TEST string for ex6";

        long tot = testString.chars().filter(Character::isLowerCase).count();
        System.out.println("tot = " + tot);
    }

}
