package in.mkp.java8inaction.chapter5;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 *  REF: 5.5. PUTTING IT ALL INTO PRACTICE
 *
 */
public class TraderTransactionQuestions {

    public static void main(String[] args) {
        q1();
        q2();
        q3();
        q4();
        q4v2();
        q5();
        q6();
        q7();
        q7v2();
        q7v3();
        q7v4();
        q8();
    }

    /**
     * Find all transactions in the year 2011 and sort them by value (small to high).
     */
    public static void q1() {
        final List<Transaction> collect = TraderTransactionHelper.getTransactions().stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(t -> t.getValue()))
                .collect(toList());

        System.out.println("collect = " + collect);
    }


    /**
     * What are all the unique cities where the traders work?
     */
    public static void q2() {
        final List<String> collect = TraderTransactionHelper.getTransactions().stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        System.out.println("collect = " + collect.toString());
    }

    /**
     * Find all traders from Cambridge and sort them by name.
     */
    public static void q3() {
        final List<Transaction> transactions = TraderTransactionHelper.getTransactions();
        final List<Trader> collect = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(t -> t.getName()))
                .distinct()
                .collect(Collectors.toList());

        System.out.println("collect.toString() = " + collect.toString());
    }

    /**
     * Return a string of all traders’ names sorted alphabetically.
     */
    public static void q4() {
        final String reduce = TraderTransactionHelper.getTransactions().stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (acc, b) -> acc.concat(b));

        System.out.println("reduce = " + reduce);
    }

    /**
     * Return a string of all traders’ names sorted alphabetically.
     *
     * in original version reduce is inefficient because new string is created at every step.
     */
    public static void q4v2() {
        final String collect = TraderTransactionHelper.getTransactions().stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());

        System.out.println("collect = " + collect);
    }

    /**
     * Are any traders based in Milan?
     */
    public static void q5() {
        final boolean milan = TraderTransactionHelper.getTransactions().stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println("milan = " + milan);
    }

    /**
     * Print all transactions’ values from the traders living in Cambridge.
     */
    public static void q6() {
        System.out.println("Transactions");
        TraderTransactionHelper.getTransactions().stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t -> t.getValue())
                .forEach(System.out::println);
        System.out.println("\n\n");
    }


    /**
     * What’s the highest value of all the transactions?
     */
    public static void q7() {
        final Optional<Integer> reduce = TraderTransactionHelper.getTransactions().stream()
                .map(t -> t.getValue()) //.max(Comparator.comparing((a, b) -> a - b))
                .reduce(Integer::max);

        System.out.println("max " + (reduce.isPresent() ? reduce.get() : "none"));
    }

    /**
     * What’s the highest value of all the transactions?
     */
    public static void q7v2() {
        final Optional<Integer> reduce = TraderTransactionHelper.getTransactions().stream()
                .map(t -> t.getValue())
                .sorted(Comparator.reverseOrder())
                .findFirst();

        System.out.println("max " + (reduce.isPresent() ? reduce.get() : "none"));
    }

    /**
     * What’s the highest value of all the transactions?
     */
    public static void q7v3() {
        final Optional<Integer> reduce = TraderTransactionHelper.getTransactions().stream()
                .max(Comparator.comparing(Transaction::getValue))
                .map(t -> t.getValue());

        System.out.println("max " + (reduce.isPresent() ? reduce.get() : "none"));
    }

    public static void q7v4() {
        final List<Transaction> transactions = TraderTransactionHelper.getTransactions();

        final OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();

        System.out.println("max.getAsInt() = " + max.getAsInt());
    }

    public static void q8() {
        final List<Transaction> transactions = TraderTransactionHelper.getTransactions();

        final Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        System.out.println("min = " + (min.isPresent() ? min.get().toString() : "None"));
    }


}
