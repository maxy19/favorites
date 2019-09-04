package com.mxy.module.skeleton;

/*骨架实现的步骤：
        创建接口
        创建抽象类实现该接口，并实现公共方法
        在具体实现子类中创建一个私有内部类，继承抽象类。将外部调用委托给抽象接口，该类可以在使用通用方法的同时继承和实现其他接口。
        */
public class Daily {
    public static void main(String[] args) {
        InstantCoffee instantCoffee = new InstantCoffee();
        LatteCoffee latteCoffee = new LatteCoffee();

        instantCoffee.process();
        System.out.println("-----------------");
        latteCoffee.process();
    }
}