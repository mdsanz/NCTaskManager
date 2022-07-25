package mx.tc.j2se.tasks;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Implementation of the Task interface. It contains some text that describes the details of itself,
 * and the time of execution.
 */
public class TaskImpl implements Task, Cloneable {

    /**
     * Describes the details of the task.
     */
    private String title;

    /**
     * Shows the time of execution of the non-repetitive task.
     */
    private LocalDateTime time;

    /**
     * Shows the star time of the repetitive task.
     */
    private LocalDateTime start;

    /**
     * Shows the end time of the repetitive task.
     */
    private LocalDateTime end;

    /**
     * Shows the time interval of the repetitive task.
     */
    private long interval;

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
    public TaskImpl(String title, LocalDateTime time) {

            this.title = title;
            this.time = time;

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
    public TaskImpl(String title, LocalDateTime start, LocalDateTime end, long interval) {

            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.repeated = true;

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
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null) {
            throw new IllegalArgumentException("The current time cannot be null");
        } else {
            if (isActive()) {
                if (isRepeated()) {
                    if(current.isAfter(end)) {
                        return LocalDateTime.MIN;
                    } else {
                        for(LocalDateTime i=getStartTime(); i.isBefore(getEndTime()) || i.isEqual(getEndTime()); i = i.plus(this.interval, ChronoUnit.HOURS)) {
                            if(i.isAfter(current)){
                                return i;
                            }
                        }
                        return LocalDateTime.MIN;
                    }
                } else {
                    if (current.isBefore(time)) {
                        return time;
                    } else {
                        return LocalDateTime.MIN;
                    }
                }
            } else {
                return LocalDateTime.MIN;
            }
        }
    }

    @Override
    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    @Override
    public void setTime(LocalDateTime time) {

            if (isRepeated()) {
                repeated = false;
            }
            this.time = time;
            this.start = null;
            this.end = null;
            this.interval = 0;

    }

    @Override
    public LocalDateTime getStartTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    @Override
    public LocalDateTime getEndTime() {
        if (isRepeated()) {
            return end;
        } else {
            return time;
        }
    }

    @Override
    public long getRepeatInterval() {
        if (isRepeated()) {
            return interval;
        } else {
            return 0;
        }
    }

    @Override
    public void setTime(LocalDateTime start, LocalDateTime end, long interval) throws IllegalArgumentException {
    if (interval < 0) {
        throw new IllegalArgumentException("The time cannot be negative");
    }
        else if (start.isAfter(end)) {
            throw new IllegalArgumentException("The start time cannot be higher than end time");
        } else {
            if (!isRepeated()) {
                repeated = true;
            }
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = null;
        }
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskImpl task = (TaskImpl) o;
        return time == task.time && start == task.start && end == task.end && interval == task.interval && repeated == task.repeated && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, repeated);
    }

    public String toString() {
        if (isRepeated()) {
            return "[title = " + title + ", start = " + start + ", end = " + end + ", interval = " +
            interval + ", active = " + active + ", repeated = " + repeated + "]";
        } else {
            return "[title = " + title + ", time = " + time + ", active = " + active + ", repeated = " + repeated + "]";
        }
    }

    @Override
    public TaskImpl clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (TaskImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
