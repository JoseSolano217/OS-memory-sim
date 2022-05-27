package app;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private PC pc;
    private OS OS;

    public Main() {
        tick();
    }

    public void tick() {
        pc.tick();
    }
}
