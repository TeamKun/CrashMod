package net.teamfruit.crashmod;

import java.util.LinkedList;
import java.util.List;

public abstract class CrashRunnable implements Runnable {
    public boolean canceled;

    public void cancel() {
        this.canceled = true;
    }

    public static class CrashScheduler {
        public final List<CrashRunnable> runnables = new LinkedList<>();

        public void tick() {
            runnables.removeIf(e -> {
                e.run();
                return e.canceled;
            });
        }

        public void runTaskTimer(CrashRunnable runnable) {
            runnables.add(runnable);
        }

        public void reset() {
            runnables.clear();
        }
    }
}
