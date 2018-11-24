package ru.job4j.multi;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Lenovo2 on 24.11.2018.
 */
public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
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

}