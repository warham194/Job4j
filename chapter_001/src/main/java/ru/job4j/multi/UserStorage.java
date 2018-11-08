package ru.job4j.multi;

/**
 * Created by Lenovo2 on 08.11.2018.
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> customers = new HashMap<>();

    public boolean add(User user) {
        synchronized (this) {
            boolean result = false;
            if (!customers.containsKey(user.getId())) {
                customers.putIfAbsent(user.getId(), user);
                result = true;
            }
            return result;
        }
    }

    public boolean update(User user) {
        synchronized (this) {
            boolean result = false;
            if (this.customers.containsKey(user.getId())) {
                this.customers.put(user.getId(), user);
                result = true;
            }
            return result;
        }
    }

    public boolean delete(User user) {
        synchronized (this) {
            return customers.remove(user.getId(), user);
        }
    }

    public boolean transfer(final int fromId, final int told, final int amount) {
        synchronized (this) {
            User from = customers.get(fromId);
            User to = customers.get(told);
            boolean result = customers.containsKey(fromId) && customers.containsKey(told)
                    && from.getAmount() >= amount;
            if (result) {
                from.setAmount(from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
               // update(from);
                //update(to);
            }
            return result;
        }
    }

    public synchronized Map<Integer, User> getUsers() {
        return customers;
    }
}
