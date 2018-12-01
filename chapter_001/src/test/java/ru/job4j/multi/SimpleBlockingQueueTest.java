package ru.job4j.multi;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by Lenovo2 on 24.11.2018.
 */
public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
    @Test
    public void whenTemplateProducerCustomer() {
        /**
         * Producer.
         */
        Thread producerOne = new Thread(new Producer(queue));
        /**
         * Customer.
         */
        Thread customerOne = new Thread(new Customer(queue));
        customerOne.start();
        producerOne.start();

        try {
            customerOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            producerOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(() -> {
            IntStream.range(0, 5).forEach(queue::offer);
        });
        producer.start();
        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    buffer.add(queue.poll());
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}

