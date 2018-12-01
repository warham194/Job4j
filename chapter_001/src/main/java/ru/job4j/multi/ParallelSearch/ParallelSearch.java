package ru.job4j.multi.ParallelSearch;

/**
 * Created by Lenovo2 on 26.11.2018.
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.multi.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        final Thread consumer = new Thread(
                () -> {
                    while (queue.size() != 0 || !Thread.currentThread().isInterrupted()) {
                        System.out.println(queue.poll());
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();
    }
}
