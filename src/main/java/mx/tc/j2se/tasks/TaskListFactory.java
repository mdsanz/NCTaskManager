package mx.tc.j2se.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes type) {
        if (type == ListTypes.ARRAY) {
            return new ArrayTaskListImpl();
        } else {
            return new LinkedTaskListImpl();
        }
    }
}
