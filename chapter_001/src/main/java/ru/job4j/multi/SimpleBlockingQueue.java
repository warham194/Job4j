package ru.job4j.multi;

/**
 * Created by Lenovo2 on 23.11.2018.
 */

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int capacity;

    /**
     * Constructor.
     * @param capacity Max size.
     */
    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }


    public void offer(T value) {
        synchronized (this) {
            while (this.queue.size() == this.capacity) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.queue.add(value);
            notify();
        }
    }

    public T poll() {
        synchronized (this) {
            while (this.queue.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T result = this.queue.remove();  // Вопрос по методу poll()
            if (this.queue.size() != this.capacity) {
                notify();
            }
            return result;
        }
    }

    public int size() {
        synchronized (this) {
            return this.queue.size();
        }
    }

}
