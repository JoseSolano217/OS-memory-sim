package app.data;

import java.util.Random;

public class Process {

    private int size;
    private int duration;
    private enum State {
        Waiting,
        Running,
        Dead
    }
    private State processState = State.Waiting;

    public Process() {
        this.size = new Random().nextInt(15) + 1;
        this.duration = new Random().nextInt(5);
    }

    private void tick() {
        if (processState == State.Waiting) duration--;
        if (duration == 0) processState = State.Dead;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public State getProcessState() {
        return processState;
    }

    public void setProcessState(State processState) {
        this.processState = processState;
    }
}
