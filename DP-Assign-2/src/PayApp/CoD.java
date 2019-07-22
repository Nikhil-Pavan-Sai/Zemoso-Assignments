package PayApp;

public class CoD implements Amount {


    @Override
    public void deductAmount(double amt) {

        display();

    }

    @Override
    public boolean validate() {

        return true;

    }

    @Override
    public void display()
    {

        System.out.println("Pay the bill after receiving the product. Happy Shopping !");

    }


}

