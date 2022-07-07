package mx.tc.j2se.tasks;

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

}
