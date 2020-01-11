package com.mxy.module.compare.object;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * 通过CGLIB比较对象属性差异问题。
 */
@Slf4j
public class EqualsPropertiesTest {
    @Test
    public void test1() {
        B b = new B();
        b.setArr(Lists.newArrayList(new C(1, "1234")));

        B b1 = new B();
        b1.setArr(Lists.newArrayList(new C(1, "2234")));

        A a = new A();
        a.setB(b);

        A a2 = new A();
        a2.setB(b1);
        //第一次初始化比较耗时, 之后花费毫秒基本为0
        //有指定返回值,比较对象全部信息
        log.info("有指定返回值:{}",  new AbstractEqualsProperties<A, A, List<String>>(a, a2) {
        }.execute());
        System.out.println();
        //如果不想有返回值,可以使用Void占位 比较对象全部信息
        log.info("没有指定返回值:{}", new AbstractEqualsProperties<B, B, Void>(b, b1) {
        }.execute());
        System.out.println();
        //没有指定返回值,只判断指定值
        log.info("没有指定返回值,只判断指定值:{}", new AbstractEqualsProperties<B, B, Void>(b, b1,Lists.newArrayList("arr")) {
        }.execute());
    }

    @Getter
    @Setter
    class A {

        private boolean isBoy;
        private String name;
        private B b;
        private int age;
        private long IdCard;
        private float inCome;
        private byte pass;
        private char tag;
    }

    @Getter
    @Setter
    class B {
        private int num;
        private String depart;
        private long phone;
        private boolean isChina;
        private float height;
        private byte data;
        private char power;
        private List<C> arr = Lists.newArrayList(new C(1, "abcd"));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class C {
        private int num;
        private String depart;
    }
}