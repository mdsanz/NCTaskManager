package mx.tc.j2se.tasks;

/**
 * Interface that represents a list of tasks like an array. The tasks can be added or removed from the list.
 * @author Marcos Sánchez
 */
public interface ArrayTaskList {
    /**
     * Method to add a task in the list.
     * @param task the task you want to add.
     */
    void add(Task task);

    /**
     * Method to remove a task from the list.
     * @param task the task you want to remove. If the list contains the same task several times, any of them
     * will be removed.
     * @return true if the task was in the list, otherwise it returns false.
     */
    boolean remove(Task task);

    /**
     * Method to know the length of the task list.
     * @return the length of the list.
     */
    int size();

    /**
     * Method to get a specific task of the list.
     * @param index the array index where the task is located.
     * @return the task found in the given index.
     */
    Task getTask(int index);


    ArrayTaskList incoming(int from, int to);
}
