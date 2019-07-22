package xeAssign;

public interface Publisher
{

    void register(ObserverInter obs);

    void unregister(ObserverInter obs);

    void notifyAllObs();

}
