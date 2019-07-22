package xeAssign;

public class Observer1 extends ObserverInter {


    public Observer1(Currency currency)
    {

        this.currency = currency;

        this.currency.register(this);

    }

    void unregister()
    {

        this.currency.unregister(this);

    }

    @Override
    public void update() {

        System.out.format("The currency of Rupee in GBP is: %.2f\n" ,currency.get("GBP"));

    }


}
