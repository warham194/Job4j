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
        cacheBase.add(new Base(1));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Runnable run = () -> {
            try {
                cacheBase.update(new Base(1));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        };
        Thread first = new Thread(run);
        Thread second = new Thread(run);
        Thread third = new Thread(run);
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