package com.mxy.module.compress;

import org.jctools.queues.MpmcUnboundedXaddArrayQueue;

import java.util.Queue;

/**
 * @Author maxuyang
 **/
public class Test {


    static int level = 0;

    public static void main(String[] args) {
        Queue<Integer> abc = new MpmcUnboundedXaddArrayQueue<>(3);
        for (int i = 0; i <10 ; i++) {
            abc.add(i);
        }
        int i = 0;
        while(true){
            System.out.println(abc.poll());
            if(i++ == 10){
                break;
            }
        }
    }


}
