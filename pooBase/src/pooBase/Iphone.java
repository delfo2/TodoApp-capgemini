package pooBase;

import pooBase.MusicPlayer.MusicController;
import pooBase.browser.BrowserController;
import pooBase.phone.PhoneController;

public class Iphone {

	public static void main(String[] args) {
		BrowserController bController = new BrowserController();
		MusicController mController = new MusicController();
		PhoneController pController = new PhoneController();
		
		//bController.addNewPage();
		//bController.showPage();
		//bController.refreshPage();
		
		//mController.play();
		//mController.pause();
		//mController.selectMusic(1);
		
		pController.call();
		pController.pickUp();
		pController.playVoiceMessage();
	}

}
