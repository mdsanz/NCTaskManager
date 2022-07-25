package mx.tc.j2se.tasks;


import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {

    public AbstractTaskList() {

    }


    /**
     * Method to add a task in the list.
     * @param task the task you want to add.
     * @throws IllegalArgumentException when the task you want to add is null.
     */
    public abstract void add(Task task) throws IllegalArgumentException;

     /**
     * Method to remove a task from the list.
     * @param task the task you want to remove. If the list contains the same task several times, any of them
     * will be removed.
     * @return true if the task was in the list, otherwise it returns false.
     * @throws IllegalArgumentException when the task you want to remove is null.
     */
    public abstract boolean remove(Task task) throws IllegalArgumentException;

    /**
     * Method to know the length of the task list.
     * @return the length of the list.
     */
    public abstract int size();

    /**
     * Method to get a specific task of the list.
     * @param index the array index where the task is located.
     * @return the task found in the given index.
     * @throws IndexOutOfBoundsException when you try to access to an index that does not exist.
     */
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;
    public abstract String toString();
    public abstract Stream<Task> getStream();
    @Override
    public AbstractTaskList clone() {
        try {
            return (AbstractTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
