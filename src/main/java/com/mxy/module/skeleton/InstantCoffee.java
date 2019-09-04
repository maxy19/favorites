package com.mxy.module.skeleton;

public class InstantCoffee implements CoffeeDaily {
    private static final String COFFEE_NAME = "instant";

    InstantCoffeeDelegator instantCoffeeDelegator = new InstantCoffeeDelegator(COFFEE_NAME);

    @Override
    public void chooseCoffee() {
        instantCoffeeDelegator.chooseCoffee();
    }

    @Override
    public void makeCoffee() {
        instantCoffeeDelegator.makeCoffee();
    }

    @Override
    public void drinkCoffee() {
        instantCoffeeDelegator.drinkCoffee();
    }

    @Override
    public void process() {
        instantCoffeeDelegator.process();
    }

    /**
     * delegator AbstractCoffeeDaily general function
     */
    private class InstantCoffeeDelegator extends AbstractCoffeeDaily {
        public InstantCoffeeDelegator(String coffeeName) {
            super(coffeeName);
        }

    }
}