package mx.tc.j2se.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.ARRAY) {
            return new ArrayTaskListImpl();
        } else {
            return new LinkedTaskListImpl();
        }
    }
}
