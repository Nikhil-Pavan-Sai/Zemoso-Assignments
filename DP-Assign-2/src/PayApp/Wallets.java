package PayApp;

public class Wallets implements Amount {



    private String upiID;
    private double amount = 10000;

    Wallets(String upi)
    {

        upiID = upi;

    }

    @Override
    public void deductAmount(double amt) {

        if (amt < amount)
        {
            amount -= amt;
            display();
        }


        else
            System.out.println("Insufficient funds !!");

    }

    @Override
    public boolean validate() {

        String upim = "021019";
        return upim.equals(upiID);

    }

    @Override
    public void display()
    {

        System.out.println("The remaining balance is: " + amount);

    }

}
