package mx.tc.j2se.tasks;

import java.util.Iterator;

public abstract class AbstractTaskList implements Iterable<Task> {

    public AbstractTaskList() {

    }

    public class TaskListIterator implements Iterator<Task> {

        int position;
        Task task;
        AbstractTaskList list;
       TaskListIterator(AbstractTaskList list) {
            position = -1;
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return (position + 1) < list.size();
        }

        @Override
        public Task next() throws IndexOutOfBoundsException {
            if (hasNext()) {
                position++;
                task = list.getTask(position);
                return task;
            } else {
                throw new IndexOutOfBoundsException("There's no more tasks in the list");
            }
        }

        @Override
        public void remove() {

        }

    }

    public abstract void add(Task task) throws IllegalArgumentException;
    public abstract boolean remove(Task task) throws IllegalArgumentException;
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;
    public abstract String toString();
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if ((from < 0) || (to < 0)) {
            throw new IllegalArgumentException("The time cannot be negative");
        } else if (from > to) {
            throw new IllegalArgumentException("The start time cannot be higher than end time");
        } else {
            AbstractTaskList incomingList;
            if (getClass().equals(ArrayTaskListImpl.class)) {
                incomingList = new ArrayTaskListImpl();
            }
            else {
                incomingList = new LinkedTaskListImpl();
            }
            for (int i = 0; i < size(); i++)
                if ((getTask(i).nextTimeAfter(from) > 0) && (getTask(i).nextTimeAfter(from) < to)) {
                    incomingList.add(getTask(i));
                }
            return incomingList;
        }
    }


}
