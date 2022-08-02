package mx.tc.j2se.tasks;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
        Comparator<LocalDateTime> comparator = Comparator.naturalOrder();
        Comparator<Task> compareTask = Comparator.comparing(Task::getTime);
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<>(comparator);
        Iterator<Task> list = incoming(tasks,start,end);

        while(list.hasNext())
            {
            Task t = list.next();
            if(t.isRepeated()){
                for(LocalDateTime i = t.nextTimeAfter(start); i.isBefore(end) || i.isEqual(end); i = i.plus(t.getRepeatInterval(), ChronoUnit.HOURS)){
                    if(map.containsKey(i)){

                        for (Map.Entry<LocalDateTime,Set<Task>> entry : map.entrySet()){
                            if(entry.getKey().isEqual(i)){
                                (entry).getValue().add(t);
                            }
                        }

                    }else{
                        Set<Task> set1 = new TreeSet<>(compareTask);
                        set1.add(t);
                        map.put(i,set1);
                    }
                }
            }else{
                if(map.containsKey(t.getTime())){
                    for (Map.Entry<LocalDateTime,Set<Task>> entry : map.entrySet()){
                        if(entry.getKey().isEqual(t.getTime())){
                            (entry).getValue().add(t);
                        }
                    }
                }else{

                    Set<Task> set = new TreeSet<>(compareTask);
                    set.add(t);
                    map.put(t.getTime(),set);
                }
            }
        }

        return map;
    }
}
