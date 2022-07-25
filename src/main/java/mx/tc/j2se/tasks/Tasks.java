package mx.tc.j2se.tasks;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedMap;

public class Tasks {

    public static Iterator<Task> incoming(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end) throws IllegalArgumentException {
        //AbstractTaskList list = TaskListFactory.createTaskList(ListTypes.LINKED);
        LinkedList<Task> list = new LinkedList<>();
        if (start == null || end == null) {
            throw new IllegalArgumentException("The start time and the end time cannot be null");
        } else if (start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("The start time cannot be equal or higher than end time");
        } else {
            while (tasks.hasNext()) {
                Task i = tasks.next();
                if (i.nextTimeAfter(start).isBefore(end) && i.nextTimeAfter(start).isAfter(start)) {
                    list.add(i);
                }
            }
        }
        return list.iterator();
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end) {

    }
}
