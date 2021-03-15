package com.mxy.module.cache.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.SneakyThrows;

public class BloomFilterDemo {

    /**
     * 预期数据量(实际数据如果超过预期数据将会导致误判率升高)
     */
    private static int dataSize = 1000000;

    /**
     * 能容忍的误判率(误判率不能为0,BloomFilter理论上存在误判的情况)
     */
    private static double falseRate = 0.0001;

    @SneakyThrows
    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), dataSize, falseRate);
        for (int i = 0, length =1000000 ; i < length; i++) {
            bloomFilter.put(i);
        }
        System.out.println(bloomFilter.approximateElementCount());
        System.out.println(bloomFilter.mightContain(0));
        System.out.println(bloomFilter.expectedFpp());
    }
}
