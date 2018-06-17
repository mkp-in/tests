package in.mkp.java8lambdas.chap2;

import java.text.SimpleDateFormat;
import javax.swing.text.DateFormatter;

public class Ex2 {

    public static void main(String[] args) {
        ThreadLocal<DateFormatter> dateFormatterThreadLocal = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));
    }
}
