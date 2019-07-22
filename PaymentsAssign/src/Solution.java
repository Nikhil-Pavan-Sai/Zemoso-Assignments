/*
*
* Payments India is a payment service which takes care of the payment for Amazon e-commerce website. Write a program to
* handle the payment via various methods of payment.
*
*
* Type of payment : Credit Card, Debit Card, Wallets, Net Banking, COD (Cash on delivery)



* Use factory pattern.
* */


import PayApp.Amount;
import PayApp.AmountFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String cardNum, cardOption, userName, password, upi;
        char cha;


        AmountFactory amountfactory = new AmountFactory();

        System.out.println("Enter the payment option: ");
        System.out.println("1. Credit card\n 2. Debit card\n 3. Net Banking\n 4. Wallet\n 5. PayApp.CoD(Cash on Delivery)");

        int ch = Integer.parseInt(br.readLine());

        do
            {
            switch (ch) {
                case 1:


                case 2:
                    System.out.println("Enter the option either credit/debit card: ");
                    cardOption = br.readLine();

                    System.out.println("Enter the card num: ");
                    cardNum = br.readLine();

                    Amount amount1 = amountfactory.cardOption(cardOption, cardNum);

                    if (amount1.validate()) {
                        System.out.println("Enter the amount: ");

                        int amt1 = Integer.parseInt(br.readLine());

                        amount1.deductAmount(amt1);
                    } else
                        System.out.println("Enter the correct card number !!");

                    break;


                case 3:
                    System.out.println("Enter the username of net banking: ");
                    userName = br.readLine();

                    System.out.println("Enter the password: ");
                    password = br.readLine();

                    Amount amount3 = amountfactory.netBanking(userName, password);

                    if (amount3.validate()) {
                        System.out.println("Enter the amount: ");

                        int amt3 = Integer.parseInt(br.readLine());

                        amount3.deductAmount(amt3);
                    } else
                        System.out.println("Enter valid credentials !!!");

                    break;


                case 4:
                    System.out.println("Enter the UPI ID: ");
                    upi = br.readLine();

                    Amount amount4 = amountfactory.wallet(upi);

                    if (amount4.validate()) {
                        System.out.println("Enter the amount: ");

                        int amt4 = Integer.parseInt(br.readLine());

                        amount4.deductAmount(amt4);
                    } else
                        System.out.println("Enter correct UPI ID !");

                    break;


                case 5:
                    Amount amount5 = amountfactory.getCoD();

                    System.out.println("Enter the amount: ");

                    int amt5 = Integer.parseInt(br.readLine());

                    amount5.deductAmount(amt5);
                    break;


                default:
                    System.out.println("Enter the correct choice");
                    break;


            }

            System.out.println("Do you want to continue ? (y/n)");
            cha = br.readLine().charAt(0);


        }while (cha == 'y');


    }

}

