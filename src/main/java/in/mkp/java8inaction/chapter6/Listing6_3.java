package in.mkp.java8inaction.chapter6;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.mkp.java8inaction.chapter4.Dish;
import in.mkp.java8inaction.chapter4.DishHelper;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

public class Listing6_3 {

    public static void main(String[] args) {
        /**
         * Finding the highest-calorie Dish in each subgroup.
         *
         * REF: https://www.safaribooksonline.com/library/view/java-8-in/9781617291999/kindle_split_017.html
         */

        final List<Dish> dishes = DishHelper.getDishes();

        final Map<Dish.Type, Dish> collect = dishes.stream()
                .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
        System.out.println("collect = " + collect);

    }
}
