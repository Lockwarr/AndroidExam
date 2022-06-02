package bg.tu.varna.lab8;

public class TimerTask implements Runnable {

    private TimeListener listener;

    public TimerTask(TimeListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.OnSetTimer(1);
    }
}
