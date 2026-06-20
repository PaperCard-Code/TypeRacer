package util;

import javax.swing.Timer;
import java.awt.event.ActionListener;

public class TimerUtil {

    private Timer timer;

    public TimerUtil(
            int delay,
            ActionListener listener) {

        timer = new Timer(delay, listener);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public boolean isRunning() {
        return timer.isRunning();
    }
}