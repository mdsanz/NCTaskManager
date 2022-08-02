package mx.tc.j2se.tasks;


import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class LinkedTaskListImpl extends AbstractTaskList {
    @Override
    public Iterator<Task> iterator() {
        class LinkedListIterator implements Iterator<Task> {

            int position;
            Task task;

            LinkedListIterator() {
                position = -1;
            }

            @Override
            public boolean hasNext() {
                return (position + 1) < size;
            }

            @Override
            public Task next() throws IndexOutOfBoundsException {
                if (hasNext()) {
                    position++;
                    task = getTask(position);
                    return task;
                } else {
                    throw new IndexOutOfBoundsException("There's no more tasks in the list");
                }
            }

            @Override
            public void remove() {
                LinkedTaskListImpl list = new LinkedTaskListImpl();
                boolean founded = false;
                for (int i = 0; i < size; i++) {
                    if (founded) {
                        list.add(getTask(i));
                    } else {
                        if (getTask(i) == task) {
                            founded = true;
                        } else {
                            list.add(getTask(i));
                        }
                    }
                }
                head = list.head;
                queue = list.queue;
                size = list.size;
                position--;
            }
        }
        return new LinkedListIterator();
    }


    private class Node {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return task.equals(node.task) && next.equals(node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(task, next);
        }

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
        super();
    }

    public LinkedTaskListImpl(Node head, int size, Node queue) {
        this.head = head;
        this.size = size;
        this.queue = queue;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskListImpl)) return false;
        LinkedTaskListImpl tasks = (LinkedTaskListImpl) o;
        return size == tasks.size && head.equals(tasks.head) && queue.equals(tasks.queue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, queue, size);
    }

    public String toString() {
        StringBuilder list = new StringBuilder();
        Node n = head;
        int count = 0;
        while (n != null) {
            count++;
            list.append("Task ").append(count).append(" = ").append(n.task.toString());
            if (n.next != null) {
                list.append(" ->\n");
            }
            n = n.next;
        }
        return list.toString();
    }

    @Override
    public Stream<Task> getStream(){
        if (size == 0) {
            throw new RuntimeException();
        } else {
            Stream.Builder<Task> streamBuilder = Stream.builder();
            for (Task task : this) {
                streamBuilder.accept(task);
            }

            return streamBuilder.build();
        }

    }

}
