package ru.job4j.multi.kash;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lenovo2 on 16.12.2018.
 */
public class CacheBase {

    ConcurrentHashMap<Integer, Base> values;

    public CacheBase() {
        this.values = new ConcurrentHashMap<>();
    }

    /**
     * add.
     * @param model model.
     */
    public void add(Base model) {
        this.values.putIfAbsent(model.getId(), model);
    }

    /**
     * Метод изменений.
     * Если данные затерты то выбросить OptimisticException
     * @param model
     */
    public void update(Base model) {
        values.computeIfPresent(model.getId(), (key, value) -> {
            if (values.get(model.getId()).getVersion() != model.getVersion()) {
                throw new OptimisticException("Wrong in version!");
            }
            value.update(model);
            value.increment();
            return value;
        });
    }

    /**
     * delete.
     * @param model model.
     */
    public void delete(Base model) {
        this.values.remove(model.getId());
    }

    /**
     * get.
     * @param model model.
     * @return Base.
     */
    public Base getBase(Base model) {
        return this.values.get(model);
    }

    /**
     * get.
     * @return values.
     */
    public ConcurrentHashMap<Integer, Base> getValues() {
        return this.values;
    }
}
