package mx.tc.j2se.tasks;

public abstract class AbstractTaskList {
    public AbstractTaskList() {

    }

    public abstract void add(Task task) throws IllegalArgumentException;
    public abstract boolean remove(Task task) throws IllegalArgumentException;
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if ((from < 0) || (to < 0)) {
            throw new IllegalArgumentException("The time cannot be negative");
        } else if (from > to) {
            throw new IllegalArgumentException("The start time cannot be higher than end time");
        } else {
            AbstractTaskList incomingList;
            if (getClass().equals(ArrayTaskListImpl.class)) {
                incomingList = new ArrayTaskListImpl();
            } else {
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
