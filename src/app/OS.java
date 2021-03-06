package app;

import app.data.MemoryBlock;
import app.data.Process;

import java.util.ArrayList;
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
        if (!pc.isStartingUp()) {
            findHoles();
            for (Process process : processes) {
                if (process.getProcessState() == Process.State.Waiting) {
                    process.run(firstFit(holes, process.getSize()));
                }
                process.tick();
            }
        } else {
            assignOwnMemory();
        }
    }

    public MemoryBlock firstFit(List<MemoryBlock> blocks, int processSize) {
        for (MemoryBlock block:blocks) {
            if (block.getTotalSize() >= processSize) {
                return block;
            }
        }
        return null;
    }

    public void assignOwnMemory() {
        if (pc.hasAvailableMemory(size)) {
            for (int i = 0; i < pc.getAllMemory().getTotalSize(); i++) {
                if (i <= size) pc.getAllMemory().getUnits().get(i).setInUse(true);
            }
            System.out.println("Memory 0 to " + size + " assigned to OS");
        } else {
            System.err.println("Not enough memory to assign to OS");
        }
    }

    public void findHoles() {
        List<MemoryBlock> blockList = new ArrayList<>();
        int blockIndex = -1;
        boolean isAHole = false;
        for (int i = 0; i < pc.getAvailableMemory().getTotalSize(); i++) {
            if (!pc.getAvailableMemory().getUnits().get(i).isInUse()) {
                if (!isAHole) {
                    blockIndex++;
                    blockList.add(new MemoryBlock());
                    isAHole = true;
                    blockList.get(blockIndex).getUnits().add(pc.getAllMemory().getUnits().get(i));
                } else {
                    blockList.get(blockIndex).getUnits().add(pc.getAllMemory().getUnits().get(i));
                }
            } else {
                isAHole = false;
            }
        }
        this.holes = blockList;
    }

    public void showHoles() {
        System.out.println("Holes found: " + holes.size());
        for (int i = 0; i < holes.size(); i++) {
            System.out.println("Status of hole " + (i+1) + ":");
            holes.get(i).showStats();
        }
    }

    public void showProcesses() {
        List<Process> livingProcesses = getAliveProcesses();
        System.out.println("Total processes: " + livingProcesses.size());
        for (Process process: processes) {
            process.showStats();
        }
    }

    public List<Process> getAliveProcesses() {
        List<Process> livingProcesses = new ArrayList<>();
        for (Process process: processes) {
            if (process.getProcessState() != Process.State.Dead) livingProcesses.add(process);
        }
        return livingProcesses;
    }

    public void processesString() {
        for (Process process: processes) {
            System.out.println(process.toString());
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
