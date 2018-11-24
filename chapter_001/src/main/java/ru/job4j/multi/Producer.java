package ru.job4j.multi;

/**
 * Created by Lenovo2 on 23.11.2018.
 */
public class Producer implements Runnable {
    SimpleBlockingQueue<Integer> tasksQueue;

    /**
     * Constructor.
     * @param tasksQueue Queue.
     */
    public Producer(SimpleBlockingQueue<Integer> tasksQueue) {
        this.tasksQueue = tasksQueue;
    }

    @Override
    public void run() {
        for (int index = 0; index <= 5; index++) {
            this.tasksQueue.offer(index);
            System.out.printf("Producer -- Data: %d; Size: %d%s", index, this.tasksQueue.size(), System.lineSeparator());
        }
    }
}
