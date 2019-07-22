class DiscJockey{

    private SongComponent songList;

    DiscJockey(SongComponent newSongList){

        songList = newSongList;

    }


    void getSongList(){

        songList.displaySongInfo();

    }

}
