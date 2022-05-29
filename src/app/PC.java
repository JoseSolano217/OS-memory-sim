package app;

import app.data.MemoryBlock;
import app.data.MemoryUnit;

import java.util.LinkedList;

public class PC {

    private boolean startup = true;
    private MemoryBlock allMemory;
    private OS pc89;

    public PC(MemoryBlock allMemory) {
        this.allMemory = allMemory;
    }

    public boolean hasAvailableMemory(int memoryNeeded) {
        return (allMemory.getTotalSize() >= memoryNeeded);
    }

    public void tick() {
        pc89.run();
    }

    public void run() {
        int time = 0;
        long lastingTime = System.nanoTime();
        double ticks = 60.0;
        double ns = 1000000000 / ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (time <= 120) {
            long now = System.nanoTime();
            delta += (now - lastingTime) / ns;
            lastingTime = now;
            while (delta >= 1) {
                delta--;
                tick();
                startup = false;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                time++;
                pc89.processesString();
                pc89.showHoles();
                System.out.println(time + " seconds passed since simulation start");
            }
        }
    }

    public MemoryBlock getAvailableMemory() {
        LinkedList<MemoryUnit> memory = new LinkedList<>();
        allMemory.getUnits().forEach(memoryUnit -> {if (!memoryUnit.isInUse()) memory.add(memoryUnit);});
        return new MemoryBlock(memory);
    }

    public boolean isStartingUp() {
        return startup;
    }

    public void setStartup(boolean startup) {
        this.startup = startup;
    }

    public MemoryBlock getAllMemory() {
        return allMemory;
    }

    public void setAllMemory(MemoryBlock allMemory) {
        this.allMemory = allMemory;
    }

    public OS getPc89() {
        return pc89;
    }

    public void setPc89(OS pc89) {
        this.pc89 = pc89;
    }
}
