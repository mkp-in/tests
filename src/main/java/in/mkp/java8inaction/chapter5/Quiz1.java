package in.mkp.java8inaction.chapter5;

import java.util.List;
import java.util.stream.Collectors;

import in.mkp.java8inaction.chapter4.Dish;
import in.mkp.java8inaction.chapter4.DishHelper;

public class Quiz1 {

    public static void main(String[] args) {

        final List<Dish> collect = DishHelper.getDishes().stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2).collect(Collectors.toList());
        System.out.println("collect = " + collect.toString());

    }

}
