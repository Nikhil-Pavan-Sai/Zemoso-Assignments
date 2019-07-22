import xeAssign.Currency;
import xeAssign.Observer1;
import xeAssign.Observer2;
import xeAssign.ObserverInter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        Random rand = new Random();

        List<Double> ls = new ArrayList<>();
        ls.add(69.24);
        ls.add(72.54);
        ls.add(76.21);
        ls.add(61.01);
        ls.add(64.64);


        Currency currency = new Currency();

        ObserverInter obs1 = new Observer1(currency);
        ObserverInter obs2 = new Observer2(currency);

        double randomValue = 60 + (80 - 60) * rand.nextDouble();

        currency.setUSD(randomValue);

    }

}