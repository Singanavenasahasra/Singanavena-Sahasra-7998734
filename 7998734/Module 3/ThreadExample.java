public class ThreadExample {

    static class CustomTask implements Runnable {
        private final String threadName;

        public CustomTask(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            int i = 1;
            while (i <= 5) {
                System.out.println(threadName + " is executing loop step: " + i);
                executeDelay(200);
                i++;
            }
        }

        private void executeDelay(long ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new CustomTask("Thread A"));
        Thread thread2 = new Thread(new CustomTask("Thread B"));

        thread1.start();
        thread2.start();
    }
}