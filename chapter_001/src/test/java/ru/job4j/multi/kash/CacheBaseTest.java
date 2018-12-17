package ru.job4j.multi.kash;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Lenovo2 on 16.12.2018.
 */
public class CacheBaseTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        CacheBase cacheBase = new CacheBase();
        cacheBase.add(new Base(1, "Ivan"));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Runnable runOne = () -> {
            try {
                cacheBase.update(new Base(1, "Ilya"));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        };

        Runnable runTwo = () -> {
            try {
                cacheBase.update(new Base(1, "Max"));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        };

        Runnable runThree = () -> {
            try {
                cacheBase.update(new Base(1, "Anton"));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        };
        Thread first = new Thread(runOne);
        Thread second = new Thread(runTwo);
        Thread third = new Thread(runThree);
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        assertThat(cacheBase.getValues().get(1).getVersion(), is(2));
        assertThat(ex.get().getMessage(), is("Wrong in version!"));
    }
}