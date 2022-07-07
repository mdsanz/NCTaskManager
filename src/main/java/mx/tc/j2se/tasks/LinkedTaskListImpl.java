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
    private Node queue;
    private int size;

    public LinkedTaskListImpl() {

    }

    public LinkedTaskListImpl(Node head) {
        this.head = head;
        this.queue = head;
        this.size = 1;
    }

    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("You can not add null elements");
        } else {
            if (head == null) {
                head = new Node(task);
                queue = head;
            } else {
                queue.next = new Node(task);
                queue = queue.next;
            }
            size++;
        }
    }

    @Override
    public boolean remove(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("You can not remove null elements");
        } else {
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
            queue = linkedList.queue;
            return removed;
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
        } else if (from > to) {
            throw new IllegalArgumentException("The start time cannot be higher than end time");
        } else {
            LinkedTaskListImpl incomingList = new LinkedTaskListImpl();
            for (int i = 0; i < size; i++) {
                if ((getTask(i).nextTimeAfter(from) > 0) && (getTask(i).nextTimeAfter(from) < to)) {
                    incomingList.add(getTask(i));
                }
            }
            return incomingList;
        }
    }
}
