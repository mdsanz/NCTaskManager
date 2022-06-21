package mx.tc.j2se.tasks;

public class TaskImpl implements Task{
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public TaskImpl() {
    }

    public TaskImpl(String title, int time) {
        this.title = title;
        this.time = time;
    }

    public TaskImpl(String title, int start, int end, int interval) {
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
    public int getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    @Override
    public void setTime(int time) {
        if (isRepeated()) {
            repeated = false;
        }
            this.time = time;
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
    public void setTime(int start, int end, int interval) {
        if (!isRepeated()) {
            repeated = true;
        }
            this.start = start;
            this.end = end;
            this.interval = interval;
    }

    @Override
    public int nextTimeAfter (int current) {
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
