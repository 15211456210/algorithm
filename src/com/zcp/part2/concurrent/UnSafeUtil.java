package com.zcp.part2.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class UnSafeUtil {
    private static final Unsafe unsafe = Unsafe.getUnsafe();

}
