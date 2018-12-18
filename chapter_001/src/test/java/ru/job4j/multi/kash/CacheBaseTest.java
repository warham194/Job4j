package ru.job4j.multi.kash;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CacheBaseTest {
    private CacheBase cacheBase;
    private AtomicReference<Exception> ex;
    Base base;
    @Before
    public void setUp() {
        cacheBase = new CacheBase();
        ex = new AtomicReference<>();
        base = new Base(1, "First");
    }

    @Test
    public void whenAddAndDeleteTest() {
        this.cacheBase.add(base);
        assertThat(this.cacheBase.size(), is(1));
        this.cacheBase.delete(base);
        assertThat(this.cacheBase.size(), is(0));
    }
    @Test
    public void whenInterThreadBaseUpdateTest() throws InterruptedException {
        this.cacheBase.add(base);
        Thread thread1 = new Thread(
                () -> {
                    Base base1 = new Base(base);
                    base1.up("First_changed_1");
                    try {
                        cacheBase.update(base1);
                        System.out.println(base1.getName());
                    } catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    Base base2 = new Base(base);
                    base2.up("First_changed_2");
                    try {
                        cacheBase.update(base2);
                        System.out.println(base2.getName());
                    } catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        Thread thread3 = new Thread(
                () -> {
                    Base base3 = new Base(base);
                    base3.up("First_changed_3");
                    try {
                        cacheBase.update(base3);
                        System.out.println(base3.getName());
                    } catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        thread2.start();
        thread1.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(base.getName());
        assertThat(ex.get().getMessage(), is("Wrong in version!"));
        assertThat(cacheBase.getValues().get(1).getVersion(), is(3));
    }
}