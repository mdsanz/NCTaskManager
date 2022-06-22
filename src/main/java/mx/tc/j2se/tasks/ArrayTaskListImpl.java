package mx.tc.j2se.tasks;

public class ArrayTaskListImpl implements ArrayTaskList {
    private Task[] taskList;
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
}
