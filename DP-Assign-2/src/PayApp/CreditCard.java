package PayApp;

public class CreditCard implements Amount {


    private String num;
    private double amount = 120000;

    CreditCard(String cardNum)
    {

        num = cardNum;

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
    public boolean validate()
    {

        String cardNum1 = "4250123698541452";
        return cardNum1.equals(num);


    }

    @Override
    public void display()
    {

        System.out.println("The remaining balance is: " + amount);

    }


}
