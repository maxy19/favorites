package com.mxy.module.skeleton;

public class LatteCoffee extends CupCleanMachine implements CoffeeDaily {

    private static final String COFFEE_NAME = "latte";

    LatteCoffeeDelegator latteCoffeeDelegator = new LatteCoffeeDelegator(COFFEE_NAME);

    @Override
    public void chooseCoffee() {
        latteCoffeeDelegator.chooseCoffee();
    }

    @Override
    public void makeCoffee() {
        latteCoffeeDelegator.makeCoffee();
    }

    @Override
    public void drinkCoffee() {
        latteCoffeeDelegator.drinkCoffee();
    }

    @Override
    public void process() {
        latteCoffeeDelegator.process();
        clean();
    }

    /**
     * delegator AbstractCoffeeDaily general function
     */
    private class LatteCoffeeDelegator extends AbstractCoffeeDaily {
        public LatteCoffeeDelegator(String coffeeName) {
            super(coffeeName);
        }
    }
}
