package com.github.scaffold.hashmap;

import java.util.HashMap;

/**
 * 模拟HashMap循环现象
 * 
 * @author wangzhifeng
 * @date 2018年6月16日 下午5:33:31
 */
public class HashmapDeadLock {
    private HashMap hash = new HashMap();

    public HashmapDeadLock() {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    hash.put(new Integer(i), Integer.valueOf(i));
                }
                System.out.println("t1 over");
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    hash.put(new Integer(i), Integer.valueOf(i));
                }
                System.out.println("t2 over");
            }
        };
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new HashmapDeadLock();
    }
}
