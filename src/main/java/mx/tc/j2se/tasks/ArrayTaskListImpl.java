package mx.tc.j2se.tasks;

/**
 * Implementation of the ArrayTaskList interface. It contains an array with the tasks in there, and the size
 * of this array.
 */
public class ArrayTaskListImpl implements ArrayTaskList {
    /**
     * Array with tasks in there.
     */
    private Task[] taskList;
    /**
     * Length of the array.
     */
    private int size;

    public ArrayTaskListImpl() {
    }

    @Override
    public void add(Task task) {
        Task[] taskList2 = new Task[size+1];
        for (int i = 0; i < size; i++) {
            taskList2[i] = taskList[i];
        }
        taskList2[size] = task;
        this.size++;
        taskList = taskList2;
    }

    @Override
    public boolean remove(Task task) {
        int count = 0;
        for (Task t : taskList) {
            if (t == task) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            Task[] taskList2 = new Task[size-count];
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public Task getTask(int index) {
        return taskList[index];
    }

    @Override
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskListImpl incomingList = new ArrayTaskListImpl();
        for (Task t : taskList) {
            if (t.isActive()) {
                if (t.isRepeated()) {
                    int timesExecuted = (t.getEndTime() - t.getStartTime()) / t.getRepeatInterval();
                    for (int i = 0; i <= timesExecuted; i++) {
                        if (((i * t.getRepeatInterval() + t.getStartTime()) > from) && ((i * t.getRepeatInterval() + t.getStartTime()) < to)) {
                            incomingList.add(t);
                            break;
                        }
                    }
                } else {
                    if ((t.getTime() > from) && (t.getTime() < to)) {
                        incomingList.add(t);
                    }
                }
            }
        }
        return incomingList;
    }

}
