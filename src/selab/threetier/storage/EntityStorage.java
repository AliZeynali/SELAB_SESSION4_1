package selab.threetier.storage;
import selab.threetier.logic.Entity;
import selab.threetier.logic.Task;

import java.util.*;

public class EntityStorage<T extends Entity & Comparable<T>> {

    private HashMap<Integer, T> list = new HashMap<Integer, T>();
    private int counter = 0;

    public int addOrUpdate(T item) {
        int id = item.getId();
        if (id == 0) {
            item.setId(++counter);
            id = counter;
        }

        if (list.containsKey(id))
            list.replace(id, item);
        else
            list.put(id, item);
        return id;
    }
    public void delete(T item){
        list.remove(item.getId());
    }

    public ArrayList<T> getAll() {
        ArrayList<T> tasks = new ArrayList<T>(list.values());
        Collections.sort(tasks);
        return tasks;
    }



    public T get(int id) {
        return list.get(id);
    }
}
