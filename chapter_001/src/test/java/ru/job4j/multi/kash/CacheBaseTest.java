package ru.job4j.multi.kash;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


public class CacheBaseTest {
    private CacheBase list;
    @Before
    public void init() {
        this.list = new CacheBase();
    }
    @Test
    public void whenAddNewModel() {
        this.list.add(new Base(1, "Ivan"));
        assertThat(this.list.getValues().get(1).getVersion(), is(0));
    }
    @Test
    public void whenAddNotNewModel() {
        this.list.add(new Base(1, "Ivan"));
        this.list.add(new Base(1, "Oleg"));
        int actual = this.list.getValues().get(1).getVersion();
        int expected = 2;
        assertThat(actual, is(expected));
    }
    @Test
    public void whenDeleteModel() {
        this.list.add(new Base(1, "Ivan"));
        this.list.delete(new Base(1, "Ivan"));
        assertThat(this.list.getValues().size(), is(0));
    }

    @Test
    public void whenUpdateModel() {
        this.list.add(new Base(1, "Ivan"));
        assertThat(this.list.getValues().get(1).getVersion(), is(1));
        this.list.update(new Base(1, "Oleg"));
        assertThat(this.list.getValues().get(1).getVersion(), is(2));
    }
    @Test (expected = OptimisticException.class)
    public void whenUpdateModelWithWrongVersion() {
        this.list.add(new Base(1, "Ivan"));
        this.list.update(new Base(1, "Ivan"));
    }
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            try {
                throw new RuntimeException("Wrong in version!");
            } catch (Exception e) {
                ex.set(e);
            }
        });
        thread.start();
        thread.join();
        String actual = ex.get().getMessage();
        String expected = "Wrong in version!";
        assertThat(actual, is(expected));
    }
    @Test
    public void whenTwoThreadsTryToUpdateModel() throws InterruptedException {
        this.list.add(new Base(1, "Ivan"));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Runnable run = () -> {
            try {
                list.update(new Base(1, "Ivan"));
            } catch (OptimisticException oe) {
                ex.set(oe);
            }
        };
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(this.list.getValues().get(1).getVersion(), is(1));
        assertThat(ex.get().getMessage(), is("Wrong in version!"));
    }
}