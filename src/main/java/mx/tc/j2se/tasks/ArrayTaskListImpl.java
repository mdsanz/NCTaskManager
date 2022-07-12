package mx.tc.j2se.tasks;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Implementation of the ArrayTaskList interface. It contains an array with the tasks in there, and the size
 * of this array.
 */
public class ArrayTaskListImpl extends AbstractTaskList {
    /**
     * Array with tasks in there.
     */
    private Task[] taskList;
    /**
     * Length of the array.
     */
    private int size;

    public ArrayTaskListImpl() {
        super();
    }

    public ArrayTaskListImpl(Task[] taskList, int size) {
        this.taskList = taskList;
        this.size = size;
    }

    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("You can not add null elements");
        } else {
            Task[] taskList2 = new Task[size + 1];
            for (int i = 0; i < size; i++) {
                taskList2[i] = taskList[i];
            }
            taskList2[size] = task;
            this.size++;
            taskList = taskList2;
        }
    }

    @Override
    public boolean remove(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("You can not remove null elements");
        } else {
            int count = 0;
            for (Task t : taskList) {
                if (t == task) {
                    count++;
                }
            }
            if (count == 0) {
                return false;
            } else {
                Task[] taskList2 = new Task[size - count];
                for (int i = 0, j = 0; i < size; i++) {
                    if (taskList[i] != task) {
                        taskList2[j] = taskList[i];
                        j++;
                    }
                }
                size -= count;
                taskList = taskList2;
                return true;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("The index cannot be equals or higher than the size list");
        } else {
            return taskList[index];
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new AbstractTaskList.TaskListIterator(new ArrayTaskListImpl(taskList, size));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskListImpl taskList1 = (ArrayTaskListImpl) o;
        return size == taskList1.size && Arrays.equals(taskList, taskList1.taskList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(taskList);
        return result;
    }
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder("(");
        for(int i = 0; i < size; i++) {
            if (i == size -1) {
                list.append("Task ").append(i + 1).append(" = ").append(taskList[i].toString()).append(")");
            } else {
                list.append("Task ").append(i + 1).append(" = ").append(taskList[i].toString()).append(",\n");
            }
        }
        return list.toString();
    }
}
