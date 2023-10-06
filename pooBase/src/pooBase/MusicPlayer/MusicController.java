package pooBase.MusicPlayer;

public class MusicController implements MusicPlayer {
	public void play() {
	    System.out.println("Playing...");
	}

	public void pause() {
	    System.out.println("Pausing...");
	}

	public void selectMusic(int musicId) {
	    System.out.println("Selecting music with ID: " + musicId);
	}

}
