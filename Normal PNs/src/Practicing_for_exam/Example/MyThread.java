//package Practicing_for_exam.Example;
//
//import javax.sql.rowset.spi.SyncResolver;
//
//public class MyThread extends Thread {
//    int name;
//    Object P9, P10;
//
//    MyThread(int name, Object P9, Object P10)
//    {
//        this.name = name;
//        this.P9 = P9;
//        this.P10 = P10;
//    }
//
//    public void run() {
//
//        System.out.println(this.getName() + " - STATE 1");
//
//        if (name == 1) {
//            // P9
//            synchronized (P9) {
//            System.out.println(this.getName() + " - STATE 2");
//            for (int i = 0; i < k * 100000; i++) {
//                i++; i--;
//            }
//
//            try {
//                Thread.sleep(delay * 500);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            }
//        } else {
//            if (name == 2) {
//                // P9 and P10
//                synchronized (P9) {
//                    synchronized (P10) {
//                        System.out.println(this.getName() + " - STATE 2");
//                        for (int i = 0; i < k * 100000; i++) {
//                            i++;
//                            i--;
//                        }
//
//                        try {
//                            Thread.sleep(delay * 500);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            } else {
//                if (name == 3) {
//                    synchronized (P10) {
//                        System.out.println(this.getName() + " - STATE 2");
//                        for (int i = 0; i < k * 100000; i++) {
//                            i++;
//                            i--;
//                        }
//
//                        try {
//                            Thread.sleep(delay * 500);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//
//
//        System.out.println(this.getName() + " - STATE 3");
//
//
//
//
//    }
//}
