package app;

import app.data.MemoryBlock;
import app.data.MemoryUnit;
import app.data.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OS {

    private int size = 64;
    private PC pc;
    private List<Process> processes;
    private List<MemoryBlock> holes;

    public OS(List<Process> processes, PC pc) {
        this.processes = processes;
        this.pc = pc;
    }

    public void run() {

    }

    public void assignOwnMemory() {
        if (pc.hasAvailableMemory(size)) {
            for (int i = 0; i < pc.getAllMemory().getTotalSize(); i++) {
                if (i <= size) pc.getAllMemory().getUnits().get(i).setInUse(true);
            }
        }
    }

    public MemoryBlock getBiggestHole() {

    }

    public void findHoles() {
        List<MemoryBlock> blockList = new ArrayList<>();
        int blockIndex = 0;
        boolean isAHole = false;
        for (int i = 0; i < pc.getAvailableMemory().getTotalSize(); i++) {
            if (!pc.getAvailableMemory().getUnits().get(i).isInUse()) {
                if (!isAHole) {
                    blockList.add(new MemoryBlock());
                    isAHole = true;
                    blockList.get(i).getUnits().add(pc.getAllMemory().getUnits().get(i));
                } else {
                    blockList.get(i).getUnits().add(pc.getAllMemory().getUnits().get(i));
                }
            } else {
                isAHole = false;
            }
        }
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public PC getPc() {
        return pc;
    }
    public void setPc(PC pc) {
        this.pc = pc;
    }
    public List<Process> getProcesses() {
        return processes;
    }
    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }
    public List<MemoryBlock> getHoles() {
        return holes;
    }
    public void setHoles(List<MemoryBlock> holes) {
        this.holes = holes;
    }
}
