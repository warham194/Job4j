package ru.job4j.multi.kash;

/**
 * Created by Lenovo2 on 16.12.2018.
 */
public class Base {
    private final int id;
    private int version;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }
    public Base(Base model) {
        this.id = model.id;
        this.name = model.name;
        this.version = model.version;
    }



    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
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

    public void up(String newName) {
        this.name = newName;
    }

}
