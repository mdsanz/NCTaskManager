package mx.tc.j2se.tasks;

/**
 * Interface that represents a real task.
 * @author Marcos Sánchez
 */
public interface Task {
    /**
     * Method to know the name of a task.
     * @return the name of the task.
     */
    String getTitle();

    /**
     * Method to add or modify the name of a task.
     * @param title String with the new name of the task.
     */
    void setTitle(String title);

    /**
     * Method to know if a task is active or not.
     * @return true if the task is active, otherwise it returns false.
     */
    boolean isActive();

    /**
     * Method to activate or deactivate a task.
     * @param active the value must be true to activate a task, false to deactivate.
     */
    void setActive(boolean active);

    /**
     * Method to know what time a task is going to start.
     * @return the time that the task starts. If the task is a repetitive one, then the method
     * returns the start time of the repetition.
     */
    int getTime();

    /**
     * Method to insert or modify the execution time of a non-repetitive task. If the task is a repetitive
     * one, then it becomes non-repetitive.
     * @param time the new time of execution of the task.
     */
    void setTime(int time);

    /**
     * Method to know the start of a repetitive task.
     * @return the start time of the task. If the task is a non-repetitive one, then it returns the
     * time of execution.
     */
    int getStartTime();

    /**
     * Method to know the end of a repetitive task.
     * @return the end time of the task. If the task is a non-repetitive one, then it returns the time
     * of execution.
     */
    int getEndTime();

    /**
     * Method to know the time interval in which a repetitive task is executed.
     * @return the time interval of the task. If the task is a non-repetitive one, then it returns 0.
     */
    int getRepeatInterval();

    /**
     * Method to insert or modify the start, end and interval of a repetitive task. If the task is a
     * non-repetitive one, then it becomes repetitive.
     * @param start the new start time of the task.
     * @param end the new end time of the task.
     * @param interval the new time interval of the task.
     */
    void setTime(int start, int end, int interval);

    /**
     * Method to know if a task is a repetitive one or not.
     * @return true if the task is repetitive, otherwise it returns false.
     */
    boolean isRepeated();

    /**
     * Method to know the next start time of the task execution after a current time.
     * @param current the current time.
     * @return the time of execution after the current time if the task is non-repetitive and active. If the task is active
     * and repetitive, then it returns the next start time after the current time. If after the current
     * the task is not executed anymore, then it returns -1. If the task is not active, then it
     * returns -1, too.
     */
    int nextTimeAfter(int current);
}
