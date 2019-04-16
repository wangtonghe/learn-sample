public class TestSync {
    public static Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            System.out.println("hello world");
        }

    }

}