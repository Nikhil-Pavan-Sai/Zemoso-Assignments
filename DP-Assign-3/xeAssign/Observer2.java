package xeAssign;

public class Observer2 extends ObserverInter {

    public Observer2(Currency currency)
    {

        this.currency = currency;

        this.currency.register(this);

    }


    public void unregister()
    {

        this.currency.unregister(this);

    }

    @Override
    public void update() {

        System.out.format("The currency of Rupee in USD is: %.2f\n", currency.get("USD"));

        //System.out.format("The currency of Rupee in Euro is: %.2f\n", currency.get("Euro"));


    }
}
