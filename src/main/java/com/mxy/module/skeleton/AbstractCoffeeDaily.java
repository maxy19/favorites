package com.mxy.module.skeleton;

public class AbstractCoffeeDaily implements CoffeeDaily {

    private String coffeeName;

    public AbstractCoffeeDaily(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    @Override
    public void chooseCoffee() {
        System.out.println((String.format("today is %s coffee.", coffeeName)));
    }

    @Override
    public void makeCoffee() {
        System.out.println((String.format("make %s coffee", coffeeName)));
    }

    @Override
    public void drinkCoffee() {
        System.out.println((String.format("drink %s coffee", coffeeName)));
    }

    @Override
    public final void process() {
        chooseCoffee();
        makeCoffee();
        drinkCoffee();
    }
}