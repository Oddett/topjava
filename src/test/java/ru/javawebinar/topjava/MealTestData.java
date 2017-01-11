package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                    )
    );

    public static final List<Meal> USER_MEAL_LIST = Arrays.asList(
            new Meal(100004, LocalDateTime.of(2017, Month.JANUARY, 11, 19, 0), "Ужин", 500),
            new Meal(100003, LocalDateTime.of(2017, Month.JANUARY, 11, 13, 0), "Обед", 1000),
            new Meal(100002, LocalDateTime.of(2017, Month.JANUARY, 11, 10, 0), "Завтрак", 500)
    );

    public static final Meal USER_MEAL_1 = new Meal(100002, LocalDateTime.of(2017, Month.JANUARY, 11, 10, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_2 = new Meal(100003, LocalDateTime.of(2017, Month.JANUARY, 11, 13, 0), "Обед", 1000);
    public static final Meal USER_MEAL_3 = new Meal(100004, LocalDateTime.of(2017, Month.JANUARY, 11, 19, 0), "Ужин", 500);

    public static final List<Meal> ADMIN_MEAL_LIST = Arrays.asList(
            new Meal(100007, LocalDateTime.of(2017, Month.JANUARY, 11, 19, 0), "Ужин", 510),
            new Meal(100006, LocalDateTime.of(2017, Month.JANUARY, 11, 13, 0), "Обед", 500),
            new Meal(100005, LocalDateTime.of(2017, Month.JANUARY, 11, 10, 0), "Завтрак", 1000)
    );

    public static final Meal ADMIN_MEAL_1 = new Meal(100005, LocalDateTime.of(2017, Month.JANUARY, 11, 10, 0), "Завтрак", 1000);
    public static final Meal ADMIN_MEAL_2 = new Meal(100006, LocalDateTime.of(2017, Month.JANUARY, 11, 13, 0), "Обед", 500);
    public static final Meal ADMIN_MEAL_3 = new Meal(100007, LocalDateTime.of(2017, Month.JANUARY, 11, 19, 0), "Ужин", 510);
}
