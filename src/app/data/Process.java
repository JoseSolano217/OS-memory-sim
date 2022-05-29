package app.data;

import java.util.Random;

public class Process {

    private String name;
    private int size;
    private int duration;
    public enum State {
        Waiting,
        Running,
        Dead
    }
    private State processState = State.Waiting;

    public Process(String name) {
        this.size = new Random().nextInt(10) + 5;
        this.duration = new Random().nextInt(10) + 5;
        this.name = name;
    }

    public void tick() {
        if (processState == State.Running) {
            duration--;
        }
        if (duration == 0 && processState != State.Dead) kill();
    }

    public void showStats() {
        System.out.println("Process " + name + " is " + processState);
        System.out.println("Time left to finish process " + name + ": " + duration + "s");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProcessState(State processState) {
        this.processState = processState;
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

    public void kill() {
        this.processState = State.Dead;
        System.out.println("Killed process " + name);
    }

    public void run(MemoryBlock memoryBlock) {
        if (memoryBlock != null && processState == State.Waiting) {
            this.processState = State.Running;
            System.out.println("Running process " + name);
        }
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                ", processState=" + processState +
                '}';
    }
}
