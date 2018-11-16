package ru.job4j.multi;

/**
 * Created by Lenovo2 on 11.11.2018.
 */
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Lenovo2 on 17.04.2018.
 */
public class ArraySimple<T> implements Iterable<T> {

    private Object[] box = new Object[3];
    private int index = 0;
    private int counter = 0;

    /**
     * добавляем элемент в контейнер
     * @param value
     */
    public void add(T value) {
        growLenght();
        this.box[index++] = value;
        counter++;
    }

    /**
     * Взять элемент из контейнера
     * @param position
     * @return
     */
    public T get(int position) {
        T result = null;
        if (position >= 0 && position <= this.index) {
            result = (T) box[position];
        }
        return result;
    }
    /**
     * увеличение размера массива
     */
    public void growLenght() {
        if (this.index >= this.box.length) {
            this.box =  Arrays.copyOf(box, (box.length * 3) / 2);
        }
    }

    public int lenght() {
        return box.length;
    }
    /**
     * Iterator
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            final int modCount = counter;
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (modCount != counter) {
                    throw new ConcurrentModificationException();
                }
                return point < counter;
            }

            @Override
            public T next() {
                T result = null;
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                result = (T) box[this.point++];
                return result;
            }
        };
    }
}
