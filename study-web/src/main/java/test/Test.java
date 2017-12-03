package test;

public class Test {

    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                synchronized (o1) {
                    System.out.println("线程1获取索o1");
                    try {
                        Thread.sleep(100);
                        synchronized (o2) {
                            System.out.println("线程1获取索o2");
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                synchronized (o2) {
                    System.out.println("线程2获取索o2");
                    try {
                        Thread.sleep(100);
                        synchronized (o1) {
                            System.out.println("线程2获取索o1");
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }


    public static int count(int n) {
        //3:011 4:100 5:101 6:110 7:111 8:1000 9:1001 10:1100 11:1101 12:1110 13:1111 14:10000
        //100 110 1110 11110
        //4   6    12
        int c = 0;
        while(n != 0) {
            n = n&(n-1);
            c++;
        }
        System.out.println(c);
        return c;
    }

}
