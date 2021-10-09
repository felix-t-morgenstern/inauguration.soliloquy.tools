package inaugural.soliloquy.tools.tests.fakes;

public class PassthroughRunnable {
    public Runnable Runnable;

    public PassthroughRunnable(Runnable runnable) {
        Runnable = runnable;
    }

    public void call() {
        Runnable.run();
    }
}
