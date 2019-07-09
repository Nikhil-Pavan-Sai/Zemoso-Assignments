import java.util.ArrayList;


public class SongGroup extends SongComponent {

    private ArrayList<SongComponent> songComponents = new ArrayList<>();

    private String groupName;

    private String groupDescription;


    SongGroup(String newGroupName, String newGroupDescription){

        groupName = newGroupName;

        groupDescription = newGroupDescription;

    }


    private String getGroupName() { return groupName; }

    private String getGroupDescription() { return groupDescription; }


    public void add(SongComponent newSongComponent) {

        songComponents.add(newSongComponent);


    }


    /*public void remove(SongComponent newSongComponent) {

        songComponents.remove(newSongComponent);

    }


    public SongComponent getComponent(int componentIndex) {

        return (SongComponent)songComponents.get(componentIndex);


    }*/


    public void displaySongInfo(){

        System.out.println(getGroupName() + " " +
                getGroupDescription() + "\n");


        for (Object songComponent : songComponents) {

            SongComponent songInfo = (SongComponent) songComponent;

            songInfo.displaySongInfo();

        }

    }

}
