package ru.job4j.multi;

/**
 * Created by Lenovo2 on 08.11.2018.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {

    private UserStorage storage;
    private User first;
    private User second;

    @Before
    public void initialize() {
        this.storage = new UserStorage();
        this.first = new User(1, 100);
        this.second = new User(2, 200);
        this.storage.add(this.first);
        this.storage.add(this.second);
    }

    @Test
    public void whenTransferFromFirstToSecondUser() {
        this.storage.transfer(1, 2, 50);
        this.storage.transfer(2, 1, 100);
        assertThat(this.first.getAmount(), is(150));
        assertThat(this.second.getAmount(), is(150));
    }

    @Test
    public void whenUpdateAndRemove() {
        storage.update(new User(2, 500));
        assertThat(storage.getUsers().get(2).getAmount(), is(500));
        boolean actual = storage.delete(new User(5, 100));
        boolean expected = false;
        assertThat(actual, is(expected));
        actual = storage.delete(this.first);
        expected = true;
        assertThat(actual, is(expected));
    }
}