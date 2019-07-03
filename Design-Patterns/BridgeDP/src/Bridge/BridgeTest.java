package Bridge;

public class BridgeTest {

    public static void main(String[] args) {

        RemoteButton theTV = new RemoteMute(new TVDevice(3, 40));

        RemoteButton theTV1 = new RemotePause(new TVDevice(3, 40));

        System.out.println("Test with mute class");

        theTV.fivePressed();
        theTV.sixPressed();
        theTV.ninePressed();
        theTV.sevenPressed();
        theTV.eightPressed();

        theTV.channelFeedback();


        System.out.println("Test with pause class");

        theTV1.fivePressed();
        theTV1.sixPressed();
        theTV1.ninePressed();
        theTV1.sevenPressed();
        theTV1.eightPressed();

        theTV1.channelFeedback();



    }

}
