package ru.job4j.multi.kash;

/**
 * Created by Lenovo2 on 16.12.2018.
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String massage) {
        super(massage);
    }
}