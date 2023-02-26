package com.mxy.module.spi;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class SPITest {

    @Test
    public void jdkTest(){
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
           Search search =  iterator.next();
           search.searchDoc("jdk-spi-test");
        }
    }
    @Test
    public void springTest(){

        List<Search> searches = SpringFactoriesLoader.loadFactories(Search.class,
                Thread.currentThread().getContextClassLoader());

        for (Search search : searches) {
            search.searchDoc("spring-spi-test");
        }
    }
}