package Bridge;

public class TVDevice extends EnterDevice{

    TVDevice(int newState, int newMax)
    {
        channelState = newState;

        maxChannel = newMax;
    }


    @Override
    public void fivePressed() {
        System.out.println("Channel down");

        channelState--;
    }

    @Override
    public void sixPressed() {

        System.out.println("Channel up");

        channelState++;

    }



}
