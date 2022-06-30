package mx.tc.j2se.tasks;

public interface LinkedTaskList {
    void add(Task task);

    boolean remove(Task task);

    int size();

    Task getTask(int index);

    LinkedTaskList incoming(int from, int to);
}
