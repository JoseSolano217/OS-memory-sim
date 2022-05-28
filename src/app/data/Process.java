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
        this.size = new Random().nextInt(15) + 1;
        this.duration = new Random().nextInt(5);
        this.name = name;
    }

    private void tick() {
        if (processState == State.Running) {
            duration--;
        }
        if (duration == 0) processState = State.Dead;
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
        this.processState = State.Running;
        System.out.println("Running process " + name + " in block " + memoryBlock.getUnits().getFirst());
    }
}
