package app.data;

import java.util.LinkedList;

public class MemoryBlock {

    private LinkedList<MemoryUnit> totalSize;
    private Process processInUse;

    public MemoryBlock(LinkedList<MemoryUnit> totalSize) {
        this.totalSize = totalSize;
        this.processInUse = null;
    }

    public MemoryBlock(int size) {
        this.totalSize = new LinkedList<>();
        this.processInUse = null;
        for (int i = 0; i < size; i++) {
            addUnit(new MemoryUnit());
        }
    }

    public MemoryBlock() {
        this.totalSize = new LinkedList<>();
        this.processInUse = null;
    }

    public int getTotalSize() {
        int size = 0;
        for (MemoryUnit unit:totalSize) {
            if (!unit.isInUse()) size++;
        }
        return size;
    }

    public void addUnit(MemoryUnit unit) {
        if (processInUse == null) {
            totalSize.add(unit);
        } else {
            System.err.println("Block already in use");
        }
    }

    public void showStats() {
        System.out.println("Size of block: " + totalSize.size());
        if (processInUse != null) {
            System.out.println("Process using this block: " + processInUse.getName());
        } else {
            System.out.println("No process is using this block");
        }
    }

    public LinkedList<MemoryUnit> getUnits() {
        return totalSize;
    }

    public void setTotalSize(LinkedList<MemoryUnit> totalSize) {
        this.totalSize = totalSize;
    }

    public Process getProcessInUse() {
        return processInUse;
    }

    public void setProcessInUse(Process processInUse) {
        this.processInUse = processInUse;
    }
}
