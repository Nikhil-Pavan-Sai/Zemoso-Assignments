package xeAssign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Currency implements Publisher{

    private List<ObserverInter> list;
    private HashMap<String, Double> hm = new HashMap<>();

    public Currency()
    {

        list = new ArrayList<>();

        hm.put("USD", 68.73);
        hm.put("GBP", 85.95);
        hm.put("Euro", 77.09);

    }


    public void setUSD(Double usd)
    {
        hm.put("USD", usd);

        notifyAllObs();

    }

    Double get(String str)
    {
        return hm.get(str);
    }


    @Override
    public void register(ObserverInter obs) {

        list.add(obs);

    }

    @Override
    public void unregister(ObserverInter obs) {

        list.remove(obs);

    }

    @Override
    public void notifyAllObs() {

        for (ObserverInter obs: list)
        {

            obs.update();

        }

    }
}