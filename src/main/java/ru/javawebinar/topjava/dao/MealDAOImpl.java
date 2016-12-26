package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOImpl implements MealDAO {
    private Map<Integer, UserMeal> map = new ConcurrentHashMap<>();
    private AtomicInteger count = new AtomicInteger(0);

    {
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 600));
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()){
            userMeal.setId(count.incrementAndGet());
        }
        return map.put(userMeal.getId(), userMeal);
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return map.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        return map.values();
    }
}
