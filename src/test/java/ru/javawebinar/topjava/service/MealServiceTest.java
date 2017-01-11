package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL_1;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(100002, 100000);
        MATCHER.assertEquals(USER_MEAL_1, meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(100002, 100000);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_3, USER_MEAL_2), service.getAll(100000));
    }

    @Test
    public void getBetweenDates() throws Exception {
        Collection<Meal> meals = service.getBetweenDates(LocalDate.of(2017, 1, 10), LocalDate.of(2017, 1, 12), 100001);
        MATCHER.assertCollectionEquals(ADMIN_MEAL_LIST, meals);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2017, 1, 10, 0, 0, 0), LocalDateTime.of(2017, 1, 12, 0, 0, 0), 100001);
        MATCHER.assertCollectionEquals(ADMIN_MEAL_LIST, meals);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(100001);
        MATCHER.assertCollectionEquals(ADMIN_MEAL_LIST, all);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(USER_MEAL_1);
        updated.setCalories(800);
        service.update(updated, 100000);
        MATCHER.assertEquals(updated, service.get(updated.getId(), 100000));
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2017, Month.JANUARY, 11, 9, 0), "Завтрак", 500);
        service.save(newMeal, 100000);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_3, USER_MEAL_2, USER_MEAL_1, newMeal), service.getAll(100000));
    }
}