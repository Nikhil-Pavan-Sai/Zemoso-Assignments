package Bridge;

public abstract class RemoteButton {

    private EnterDevice device;

    RemoteButton(EnterDevice newDevice)
    {
        device = newDevice;
    }


    void fivePressed()
    {
        device.fivePressed();
    }


    void sixPressed()
    {
        device.sixPressed();
    }


    void channelFeedback()
    {
        device.channelFeedback();
    }

    void sevenPressed()
    {
        device.sevenPressed();
    }

    void eightPressed()
    {
        device.eightPressed();
    }


    public abstract void ninePressed();

}
