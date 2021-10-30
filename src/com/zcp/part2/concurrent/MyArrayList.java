package com.zcp.part2.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 改造后的ArrayList，实现了写、删操作线程安全，读不加锁
 * @param <T>
 */
public class MyArrayList<T> {

    private static Unsafe unsafe = null;
    private volatile int state;//1表示被加锁了，0表示未加锁

    public static long stateOffset;
    private ArrayList<T> list;
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            stateOffset = unsafe.objectFieldOffset(MyArrayList.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public MyArrayList() {
        this.list = new ArrayList<T>();
    }

    public void add(T val){
        for (;;){
            if (compareAndSetState(0,1)){
                //CAS加锁保证原子性
                list.add(val);
                compareAndSetState(1,0);
                break;
            }
        }
    }

    public void remove(int index){
        for (;;){
            if (compareAndSetState(0,1)){
                //CAS加锁保证原子性
                if(index >= list.size()){
                    compareAndSetState(1,0);
                    return;
                }
                list.remove(index);
                compareAndSetState(1,0);
                break;
            }
        }
    }

    public T get(int index){
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

}
