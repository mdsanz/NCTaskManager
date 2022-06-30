package mx.tc.j2se.tasks;

public class LinkedTaskListImpl implements LinkedTaskList {
    private class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    private Node head;
    private int size;

    public LinkedTaskListImpl() {

    }

    public LinkedTaskListImpl(Node head) {
        this.head = head;
        this.size = 1;
    }

    @Override
    public void add(Task task) {
        if (head == null) {
            head = new Node(task);
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = new Node(task);
        }
        size++;
    }

    @Override
    public boolean remove(Task task) {
       Node n = head;
       boolean removed = false;
       LinkedTaskListImpl linkedList = new LinkedTaskListImpl();
       while (n != null) {
           if (n.task == task) {
               removed = true;
           } else {
               linkedList.add(n.task);
           }
           n = n.next;
       }
       head = linkedList.head;
       size = linkedList.size;
       return removed;
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
            Node n = head;
            if (index != 0) {
                for (int i = 0; i < index; i++) {
                    n = n.next;
                }
            }
            return n.task;
        }
    }

    @Override
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {
        if ((from < 0) || (to < 0)) {
            throw new IllegalArgumentException("The time cannot be negative");
        } else {
            LinkedTaskListImpl incomingList = new LinkedTaskListImpl();
            Node n = head;
            while (n != null) {
                if (n.task.isActive()) {
                    if (n.task.isRepeated()) {
                        int timesExecuted = (n.task.getEndTime() - n.task.getStartTime()) / n.task.getRepeatInterval();
                        for (int i = 0; i <= timesExecuted; i++) {
                            if (((i * n.task.getRepeatInterval() + n.task.getStartTime()) > from) && ((i * n.task.getRepeatInterval() + n.task.getStartTime()) < to)) {
                                incomingList.add(n.task);
                                break;
                            }
                        }
                    } else {
                        if ((n.task.getTime() > from) && (n.task.getTime() < to)) {
                            incomingList.add(n.task);
                        }
                    }
                }
                n = n.next;
            }
            return incomingList;
        }
    }
}
