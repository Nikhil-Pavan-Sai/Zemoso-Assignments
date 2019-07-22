package PayApp;

public class AmountFactory {

    public Amount getCoD()
    {

        return new CoD();
    }


    public Amount cardOption(String payOption, String cardNumber){


        if(payOption == null){
            return null;
        }

        if(payOption.equalsIgnoreCase("CREDIT CARD")){
            return new CreditCard(cardNumber);

        }

        else if(payOption.equalsIgnoreCase("DEBIT CARD")){
            return new DebitCard(cardNumber);

        }

        return null;
    }


    public Amount wallet(String upi)
    {

        return new Wallets(upi);

    }

    public Amount netBanking(String user, String pass)
    {

        return new NetBanking(user, pass);

    }

}