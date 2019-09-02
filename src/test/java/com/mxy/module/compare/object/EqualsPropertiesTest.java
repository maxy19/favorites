package com.mxy.module.compare.object;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 通过CGLIB比较对象属性差异问题。
 */
@Slf4j
public class EqualsPropertiesTest {
    @Test
    public void test1() {
        B b = new B();
        b.setNum(1);
        b.setChina(true);
        b.setPhone(12545L);
        b.setHeight(15.1f);
        b.setDepart("zs");
        b.setData((byte) 20);
        b.setPower('z');

        B b1 = new B();
        b1.setNum(2);
        b1.setChina(false);
        b1.setPhone(1254L);
        b1.setHeight(15.1f);
        b1.setDepart("zs");
        b1.setData((byte) 20);
        b1.setPower('z');

        A a = new A();
        a.setAge(1);
        a.setBoy(true);
        a.setIdCard(12545L);
        a.setInCome(15.1f);
        a.setName("zs");
        a.setPass((byte) 20);
        a.setTag('a');
        a.setB(b);

        A a2 = new A();
        a2.setAge(1);
        a2.setBoy(true);
        a2.setIdCard(1254L);
        a2.setInCome(15.1f);
        a2.setName("zs1");
        a2.setPass((byte) 200);
        a2.setTag('a');
        a2.setB(b1);
        //第一次初始化比较耗时, 之后花费毫秒基本为0
        log.info("result = {}", new EqualsProperties<B, B>(b, b1, Sets.newHashSet("china")) {
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
    }
}