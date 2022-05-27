package app.data;

public class MemoryUnit {

    private boolean inUse;

    public MemoryUnit() {
        inUse = false;
    }

    public MemoryUnit(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
