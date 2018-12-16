package ru.job4j.multi.kash;

/**
 * Created by Lenovo2 on 16.12.2018.
 */
public class Base {
    private final int id;
    private int version = 0;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Base base = (Base) o;

        if (id != base.id) {
            return false;
        }
        return version == base.version;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + version;
        return result;
    }

    public void increment() {
        this.version++;
    }
}
