package ru.job4j.multi;

/**
 * Created by Lenovo2 on 23.11.2018.
 */
public class Customer implements Runnable{
    private final SimpleBlockingQueue<Integer> tasksQueue;

    /**
     * Constructor.
     * @param tasksQueue Queue.
     */
    public Customer(SimpleBlockingQueue<Integer> tasksQueue) {
        this.tasksQueue = tasksQueue;
    }

    @Override
    public void run() {
        for (int i = 0 ; i <= 5; i++) {
            try {
                int data = this.tasksQueue.poll();
                System.out.printf("Consumer - Data: %d; Size: %d%s", data, this.tasksQueue.size(), System.lineSeparator());
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
