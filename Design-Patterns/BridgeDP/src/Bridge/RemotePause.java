package Bridge;

public class RemotePause extends RemoteButton {


    RemotePause(EnterDevice newDevice) {
        super(newDevice);
    }

    @Override
    public void ninePressed() {

        System.out.println("Channel paused");

    }
}
