package PayApp;

public class DebitCard implements Amount {


    private String num;
    private double amount = 57000;

    DebitCard(String cardNum)
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

        String cardNum1 = "1452201575631478";
        return cardNum1.equals(num);

    }

    @Override
    public void display()
    {

        System.out.println("The remaining balance is: " + amount);

    }


}