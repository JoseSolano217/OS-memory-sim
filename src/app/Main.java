package app;

import app.data.MemoryBlock;
import app.data.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private PC pc;
    private OS OS;

    public Main() {
        this.pc = new PC(new MemoryBlock(128));
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < 5 + new Random().nextInt(5); i++) {
            processes.add(new Process("Process" + i));
        }
        this.OS = new OS(processes, pc);
        pc.setPc89(this.OS);
        tick();
    }

    public void tick() {
        pc.run();
    }
}
