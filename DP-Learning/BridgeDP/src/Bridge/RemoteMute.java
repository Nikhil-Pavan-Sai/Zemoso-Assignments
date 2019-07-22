package Bridge;

public class RemoteMute extends RemoteButton {


    RemoteMute(EnterDevice newDevice) {
        super(newDevice);
    }

    @Override
    public void ninePressed() {

        System.out.println("Channel muted");

    }
}
