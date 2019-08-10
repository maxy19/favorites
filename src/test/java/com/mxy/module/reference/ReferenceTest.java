package com.mxy.module.reference;

import org.junit.Test;

import java.io.IOException;

public class ReferenceTest {

    @Test
    public void strongReferenceTest() {
        StrongReference.invoke();
    }
    @Test
    public void softReferenceTest() throws IOException, InterruptedException {
        SoftReference.invoke();
    }
    @Test
    public void weakReferenceTest() throws InterruptedException {
        WeakReference.invoke();
    }
    @Test
    public void weakReferenceByWeakHashMapTest() throws InterruptedException {
        WeakReference.invoke2();
    }
    @Test
    public void phantomReferenceTest() {
        PhantomReference.invoke();
    }

}
