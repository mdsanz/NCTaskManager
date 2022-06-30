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

    public LinkedTaskListImpl() {

    }

    public LinkedTaskListImpl(Node head) {
        this.head = head;
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
       return removed;
    }

    @Override
    public int size() {
        Node n = head;
        int count = 0;
        while (n != null) {
            count++;
            n = n.next;
        }
        return count;
    }

    @Override
    public Task getTask(int index) {
        Node n = head;
        if (index != 0) {
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
        }
        return n.task;
    }

    @Override
    public LinkedTaskList incoming(int from, int to) {
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
