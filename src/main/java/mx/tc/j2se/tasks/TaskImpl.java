package mx.tc.j2se.tasks;

/**
 * Implementation of the Task interface. It contains some text that describes the details of itself,
 * and the time of execution.
 */
public class TaskImpl implements Task{

    /**
     * Describes the details of the task.
     */
    private String title;

    /**
     * Shows the time of execution of the non-repetitive task.
     */
    private int time;

    /**
     * Shows the star time of the repetitive task.
     */
    private int start;

    /**
     * Shows the end time of the repetitive task.
     */
    private int end;

    /**
     * Shows the time interval of the repetitive task.
     */
    private int interval;

    /**
     * Shows if the task is active or not.
     */
    private boolean active;

    /**
     * Shows if the task is repeated or not.
     */
    private boolean repeated;

    /**
     * Constructor with no params of a general task.
     */
    public TaskImpl() {
    }

    /**
     * Constructor of a non-repetitive task.
     * @param title the description of the task.
     * @param time the time of execution of the task.
     */
    public TaskImpl(String title, int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("The time cannot be negative");
        } else {
            this.title = title;
            this.time = time;
        }
    }

    /**
     * Constructor of a repetitive task.
     * @param title the description of the task.
     * @param start the start time of the task.
     * @param end the end time of the task.
     * @param interval the time interval of the task.
     * @throws IllegalArgumentException when the time is negative, the start time is higher than
     * the end time, or the time interval is smaller than zero.
     */
    public TaskImpl(String title, int start, int end, int interval) throws IllegalArgumentException {
        if ((start < 0) || (end < 0)) {
            throw new IllegalArgumentException("The time cannot be negative");
        } else if (start > end) {
            throw new IllegalArgumentException("The start time cannot be higher than end time");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("The time interval must be higher than zero");
        } else {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.repeated = true;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isRepeated() {
        return repeated;
    }

    @Override
    public int getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    @Override
    public void setTime(int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("The time cannot be negative");
        } else {
            if (isRepeated()) {
                repeated = false;
            }
            this.time = time;
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }
    }

    @Override
    public int getStartTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    @Override
    public int getEndTime() {
        if (isRepeated()) {
            return end;
        } else {
            return time;
        }
    }

    @Override
    public int getRepeatInterval() {
        if (isRepeated()) {
            return interval;
        } else {
            return 0;
        }
    }

    @Override
    public void setTime(int start, int end, int interval) throws IllegalArgumentException {
        if ((start < 0) || (end < 0)) {
            throw new IllegalArgumentException("The time cannot be negative");
        } else if (start > end) {
            throw new IllegalArgumentException("The start time cannot be higher than end time");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("The time interval must be higher than zero");
        } else {
            if (!isRepeated()) {
                repeated = true;
            }
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = 0;
        }
    }

    @Override
    public int nextTimeAfter (int current) throws IllegalArgumentException {
        if (current < 0) {
            throw new IllegalArgumentException("The current time cannot be negative");
        } else {
            if (isActive()) {
                if (isRepeated()) {
                    int timesExecuted = (end - start) / interval;
                    for (int i = 0; i <= timesExecuted; i++) {
                        if (current < (i * interval + start)) {
                            return (i * interval + start);
                        }
                    }
                    return -1;
                } else {
                    if (current < time) {
                        return time;
                    } else {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        }
    }
}
