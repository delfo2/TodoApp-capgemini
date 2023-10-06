package pooBase.phone;

public class PhoneController implements Phone {
	public void call() {
	    System.out.println("Calling...");
	}

	public void pickUp() {
	    System.out.println("Picking up call...");
	}

	public void playVoiceMessage() {
	    System.out.println("Playing voice message...");
	}
}
