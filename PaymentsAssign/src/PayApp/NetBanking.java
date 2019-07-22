package PayApp;

public class NetBanking implements Amount {


    private String userName;
    private String pass;
    private double amount = 1300000;


    NetBanking(String user, String password)
    {

        userName = user;
        pass = password;

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

        String user = "asdfg@gmail.com";
        String passWord = "1234567890";

        return user.equals(userName) && passWord.equals(pass);
    }

    @Override
    public void display()
    {

        System.out.println("The remaining balance is: " + amount);

    }

}
