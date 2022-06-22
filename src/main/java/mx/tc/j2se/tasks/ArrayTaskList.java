package mx.tc.j2se.tasks;

public interface ArrayTaskList {
    void add(Task task);
    boolean remove(Task task);
    int size();
    Task getTask(int index);
}
