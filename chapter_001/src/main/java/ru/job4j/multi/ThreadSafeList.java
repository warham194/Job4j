package ru.job4j.multi;

import java.util.*;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
/**
 * Created by Lenovo2 on 16.11.2018.
 */
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ThreadSafeList<T> implements Iterable<T> {
    @GuardedBy("this")
    private ArraySimple<T> list;

    public ThreadSafeList(ArraySimple<T> list) {
        this.list = list;
    }

    public synchronized void add(T value) {
       list.add(value);
    }

    public synchronized T get(int i) {
        return list.get(i);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized List<T> copy(ArraySimple<T> array) {
        List<T> temp = new ArrayList<T>();
        for (Object o : array) {
            temp.add((T) o);
        }
      //  Collections.copy(temp, (List<T>) array);
        // temp.addAll((Collection<T>) array);
        return temp;
    }
}
