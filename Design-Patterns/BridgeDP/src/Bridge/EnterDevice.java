package Bridge;

public abstract class EnterDevice {

    /**
     *
     */
    int channelState;

    private int volume = 0;

    int maxChannel;

    public abstract void fivePressed();

    public abstract void sixPressed();

    void channelFeedback()
    {
        if(channelState > maxChannel || channelState < 0) {
            channelState = 0;
        }

        System.out.println("You are on channel: " + channelState);
    }

    void sevenPressed()
    {
        volume++;

        System.out.println("Volume is: " + volume);
    }

    void eightPressed()
    {
        volume--;

        System.out.println("Volume is: " + volume);
    }




}
